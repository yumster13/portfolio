package sec.server;

import sec.common.*;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

public class ServerMain extends BasicServer
{
    private final UserDB userDB;
    private final byte[] salt;
    private KeyPair kp;
    private final Random rand;
    private int random;
    private final Utilities utils;
    private final Set<Integer> connectedClientsIDs;

    public ServerMain(int listeningPort, String filePath) throws Exception {
        super(listeningPort);
        userDB = new UserDB(filePath);
        registerHandlers();

        String saltFile = "salt.bin";
        if(readBytesFromFile(saltFile).length == 0)
        {
            writeBytesToFile(getSalt(),saltFile);
        }
        salt = readBytesFromFile(saltFile);

        utils = new Utilities();

        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        kp = kpg.genKeyPair();
        writeBytesToFile(kp.getPublic().getEncoded(), utils.SERVER_PUBLIC_KEY_FILE);

        rand = new Random();

        connectedClientsIDs = new HashSet<>();
    }

    @Override
    public void start() throws Exception {
        super.start();
    }

    private void registerHandlers()
    {
        //handler 1
        registerHandler(MsgType.FATHER, (message, in, out) ->
        {
            NonReplayableMessage msg = (NonReplayableMessage) message;
            String response;
            String decryptedMessage = utils.decryptMessage(msg.getText(), kp);
            String[] value = msg.getRandomValue();
            Integer clientID = msg.getClientID();

            if(isTrustedClient(value))
            {
                generateRandom();
                NonReplayableMessage nonReplayableMessage;
                updateKeys();

                if(connectedClientsIDs.contains(clientID))
                {
                    if (decryptedMessage.equalsIgnoreCase("You killed my father"))
                    {
                        response = utils.encryptMessage("No, I am your father", kp);
                    }
                    else
                    {
                        response = utils.encryptMessage("Whatever", kp);
                    }
                }
                else
                {
                    response = utils.encryptMessage("You aren't REGISTERED or CONNECTED:" +
                            " YOU have to be CONNECTED for this feature", kp);
                }

                nonReplayableMessage = new NonReplayableMessage(response,msg.getType(),
                        utils.encryptMessage(String.valueOf(Integer.parseInt(utils.decryptMessage(value[0], kp))+1),kp),
                        utils.encryptMessage(String.valueOf(random),kp));
                sendMessage(nonReplayableMessage,out);
            }
        });

        //handler 2
        registerHandler(MsgType.HELLO, (message, in, out) ->
        {
            String response;
            NonReplayableMessage msg = (NonReplayableMessage) message;
            String decryptedMessage = utils.decryptMessage(msg.getText(),kp);
            String[] value = msg.getRandomValue();

            if(isTrustedClient(value))
            {
                updateKeys();
                generateRandom();
                NonReplayableMessage nonReplayableMessage;

                if (decryptedMessage.equalsIgnoreCase("Hello there"))
                {
                    response = utils.encryptMessage("General Kenobi!", kp);
                }
                else
                {
                    response = utils.encryptMessage("Whatever", kp);
                }

                nonReplayableMessage = new NonReplayableMessage(response,msg.getType(),
                        utils.encryptMessage(String.valueOf(Integer.parseInt(utils.decryptMessage(value[0], kp))+1),kp),
                        utils.encryptMessage(String.valueOf(random),kp));
                sendMessage(nonReplayableMessage,out);
            }
        });

        //handler 3
        registerHandler(MsgType.REGISTER, (message, in, out) ->
        {
            NonReplayableMessage msg = (NonReplayableMessage) message;
            String[] value = msg.getRandomValue();
            Integer clientID = msg.getClientID();
            String response;
            String decryptedMessage = utils.decryptMessage(msg.getText(), kp);
            String[] words = decryptedMessage.split(":");
            byte[] hash = saltText(words[1]);

            if(isTrustedClient(value))
            {
                updateKeys();
                generateRandom();
                NonReplayableMessage nonReplayableMessage;

                if (!userDB.isRegistered(words[0]))
                {
                    UserDB.User user = new UserDB.User(words[0], hash);
                    userDB.add(user);
                    response = utils.encryptMessage("REGISTERED", kp);
                    connectedClientsIDs.add(clientID);
                }
                else
                {
                    response = utils.encryptMessage("Already REGISTERED", kp);
                }
                nonReplayableMessage = new NonReplayableMessage(response,msg.getType(),
                        utils.encryptMessage(String.valueOf(Integer.parseInt(utils.decryptMessage(value[0], kp))+1),kp),
                        utils.encryptMessage(String.valueOf(random),kp));
                sendMessage(nonReplayableMessage,out);
            }
        });

        registerHandler(MsgType.CONNECTION, (message, in, out) ->
        {
            NonReplayableMessage msg = (NonReplayableMessage) message;
            String response;
            String[] value = msg.getRandomValue();
            Integer clientID = msg.getClientID();
            String decryptedMessage = utils.decryptMessage(msg.getText(), kp);
            String[] words = decryptedMessage.split(":");
            byte[] hash = saltText(words[1]);

            if(isTrustedClient(value))
            {
                updateKeys();
                generateRandom();
                NonReplayableMessage nonReplayableMessage;

                if (userDB.isRegistered(words[0]))
                {
                    UserDB.User existingUser = userDB.get(words[0]);
                    byte[] password = existingUser.getField(0);

                    if (Arrays.equals(hash, password))
                    {
                        System.out.println("User: " + words[0] + " connected");
                        response = utils.encryptMessage("CONNECTED", kp);
                        connectedClientsIDs.add(clientID);
                    }
                    else
                    {
                        System.out.println("User: " + words[0] + " exists but wrong password given");
                        response = utils.encryptMessage("Wrong Password", kp);
                    }
                }
                else
                {
                    System.out.println("User: " + words[0] + " doesn't exist");
                    response = utils.encryptMessage("user doesn't exist", kp);
                }
                nonReplayableMessage = new NonReplayableMessage(response,msg.getType(),
                        utils.encryptMessage(String.valueOf(Integer.parseInt(utils.decryptMessage(value[0], kp))+1),kp),
                        utils.encryptMessage(String.valueOf(random),kp));
                sendMessage(nonReplayableMessage,out);
            }
        });

        System.out.println("Handlers registered");
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    @Override
    public void close() throws Exception
    {
        super.close();
        userDB.close();
    }

    public static void main(String[] args)
    {
        try (ServerMain server = new ServerMain(42000, "userdb.txt"))
        {
            server.start();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static byte[] readBytesFromFile(String fileName)
    {
        try (FileInputStream fis = new FileInputStream(fileName))
        {
            return fis.readAllBytes();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    private static void writeBytesToFile(byte[] bytes, String fileName)
    {
        try (FileOutputStream fos = new FileOutputStream(fileName))
        {
            fos.write(bytes);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private boolean checkMessageInteger(int newValue, int OriginalValue)
    {
        if(newValue == 0)
        {
            System.out.println(newValue);
            return true;
        }
        return (OriginalValue + 1 == newValue);
    }

    private byte[] saltText(String text)
    {
        try
        {
            PBEKeySpec spec = new PBEKeySpec(text.toCharArray(), salt, 100, 64 * 8);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e)
        {
            throw new RuntimeException(e);
        }
    }

    private void updateKeys()
    {
        try
        {
            kp = new KeyPair(utils.getPublicKey(utils.CLIENT_PUBLIC_KEY_FILE), kp.getPrivate());
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    private void sendMessage(NonReplayableMessage msg, ObjectOutputStream out)
    {
        try
        {
            SecretKey aesKey = utils.getAesKey(kp.getPrivate());
            utils.encryptAesKey(aesKey, kp.getPublic());
            out.writeObject(msg);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    private void generateRandom()
    {
        random = rand.nextInt(Integer.MAX_VALUE);
    }

    private boolean isTrustedClient(String[] value)
    {
        try
        {
            return (Integer.parseInt(utils.decryptMessage(value[1],kp)) == 0
                    || (checkMessageInteger(Integer.parseInt(utils.decryptMessage(value[1],kp)), random)));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
