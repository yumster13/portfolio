package sec.client;

public class ClientMain {

    public static void main(String[] args) throws Exception {
        new BasicTextClient("localhost", 42000, false).start();
    }
}
