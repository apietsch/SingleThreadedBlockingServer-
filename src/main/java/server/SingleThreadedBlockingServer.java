package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import handler.Handler;
import handler.PrintingHandler;
import handler.TransmogrifyHandler;
import util.*;

/**
 * run main and connect via telnet or ncat -v localhost 8080
 * exit ncat via ^Z to see the disconnected message
 */
public class SingleThreadedBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Handler<Socket> handler = new PrintingHandler<>(
                new TransmogrifyHandler()
        );

        while(true) {
            Socket s = ss.accept();
            handler.handle(s);
        }
    }

}
