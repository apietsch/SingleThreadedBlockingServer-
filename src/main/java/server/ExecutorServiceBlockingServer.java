package server;

import handler.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * run main and connect via telnet or ncat -v localhost 8080
 * exit ncat via ^Z to see the disconnected message
 */
public class ExecutorServiceBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Handler<Socket> handler = new ExecutorServiceHandler<>(
                new PrintingHandler<>(
                        new TransmogrifyHandler()
                ), Executors.newFixedThreadPool(10)
        );

        while(true) {
            Socket s = ss.accept();
            handler.handle(s);
        }
    }
}
