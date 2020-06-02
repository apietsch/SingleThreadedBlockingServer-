import java.io.IOException;
import java.net.Socket;

public class NastyChump {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket[] sockets = new Socket[3000];
        for (int i = 0; i < sockets.length; i++) {
            System.out.println("creating socket no: " + i);
            try {
                sockets[i] = new Socket("localhost", 8080);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Thread.sleep(100_000);
    }
}
