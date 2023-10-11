package sec.client;

import sec.common.*;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.Random;
import java.util.Scanner;
import java.net.Socket;

public class BasicTextClient
{
    String ip;
    int port;
    boolean connected;
    private final Random rand;
    private int random;
    private int serverRandom;
    private KeyPair kp;
    private final Utilities utils;
    private final Integer ID = new SecureRandom().nextInt();

    public BasicTextClient(String ip, int port, boolean connected) throws Exception {
        this.ip = ip;
        this.port = port;
        this.connected = connected;
        utils = new Utilities();
        rand = new Random();
        serverRandom = -1;
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        kp = kpg.genKeyPair();
        writeBytesToFile(kp.getPublic().getEncoded(), utils.CLIENT_PUBLIC_KEY_FILE);
        kp = new KeyPair(utils.getPublicKey(utils.SERVER_PUBLIC_KEY_FILE), kp.getPrivate());

        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(256);
        SecretKey aesKey = kg.generateKey();
        utils.encryptAesKey(aesKey, utils.getPublicKey(utils.CLIENT_PUBLIC_KEY_FILE));

        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);

        Files.write(Paths.get("aes_IV.bin"),iv);
    }

    public void start()
    {
        try (Socket socket = new Socket(ip, port);
             ObjectOutputStream out =
                     new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in =
                     new ObjectInputStream(socket.getInputStream()))
        {

            Scanner scanner = new Scanner(System.in);
            String line;
            System.out.print("> ");
            while (scanner.hasNextLine() &&
                   !(line = scanner.nextLine()).equalsIgnoreCase("exit"))
            {
                try
                {
                    int sepIdx = line.indexOf(' ');
                    MsgType msgType = MsgType.valueOf(line.substring(0, sepIdx).toUpperCase());
                    String textData = line.substring(sepIdx + 1);
                    resetKeys();

                    NonReplayableMessage msg = new NonReplayableMessage(utils.encryptMessage(textData, kp),
                            msgType,ID, utils.encryptMessage(String.valueOf(random),kp),
                            utils.encryptMessage(String.valueOf(serverRandom+1),kp));
                    sendMessage(msg,out);
                    out.flush();

                    NonReplayableMessage message = (NonReplayableMessage)in.readObject();
                    //decrypting server response
                    updateKeys();
                    String returnValue = utils.decryptMessage(message.getText(), kp);
                    //checking if user value is equal to the expected one
                    serverRandom = Integer.parseInt(utils.decryptMessage(message.getRandomValue()[1],kp));
                    if (checkMessageInteger(Integer.parseInt(utils.decryptMessage(message.getRandomValue()[0],kp)),random))
                    {
                        System.out.println(returnValue);
                        random = rand.nextInt(Integer.MAX_VALUE);
                    }
                    else
                    {
                        System.out.println("Wrong Value returned");
                    }
                }
                catch (IllegalArgumentException | IndexOutOfBoundsException ex)
                {
                    System.out.println("Unknown command");
                }
                System.out.print("> ");
            }
            System.out.println("Graceful exit");
            out.writeObject(BasicMessage.EXIT_MSG);
        }
        catch (EOFException ex)
        {
            System.err.println("The server unexpectedly closed the connection");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static void writeBytesToFile(byte[] bytes, String fileName) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(bytes);
        }
    }

    private boolean checkMessageInteger(int newValue, int OriginalValue){
        return (OriginalValue + 1 == newValue);
    }


    private void updateKeys() throws Exception {
        kp = new KeyPair(utils.getPublicKey(utils.CLIENT_PUBLIC_KEY_FILE), kp.getPrivate());
    }

    private void resetKeys() throws Exception {
        kp = new KeyPair(utils.getPublicKey(utils.SERVER_PUBLIC_KEY_FILE), kp.getPrivate());
    }

    private void sendMessage(NonReplayableMessage msg, ObjectOutputStream out) throws Exception {
        SecretKey aesKey = utils.getAesKey(kp.getPrivate());
        utils.encryptAesKey(aesKey, kp.getPublic());
        out.writeObject(msg);
    }

}