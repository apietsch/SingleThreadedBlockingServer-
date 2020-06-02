package server;

import handler.*;
import util.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * run main and connect via telnet or ncat -v localhost 8080
 * exit ncat via ^Z to see the disconnected message
 */
public class MultiThreadedBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8080);
        Handler<Socket> handler = new ThreadedHandler<>(
                new PrintingHandler<>(
                        new TransmogrifyHandler()
                )
        );

        while(true) {
            Socket s = ss.accept();
            handler.handle(s);
        }
    }
}
