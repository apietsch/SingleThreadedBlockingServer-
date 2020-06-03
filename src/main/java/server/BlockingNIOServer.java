package server;

import handler.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Executors;

/**
 * run main and connect via telnet or ncat -v localhost 8080
 * exit ncat via ^Z to see the disconnected message
 */
public class BlockingNIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));

        Handler<SocketChannel> handler = new ExecutorServiceHandler<>(
                new PrintingHandler<>(
                        new BlockingChannelHandler(
                            new TransmogrifyChannelHandler())
                ), Executors.newFixedThreadPool(10)
        );

        while(true) {
            SocketChannel sc = ssc.accept();
            handler.handle(sc);
        }
    }
}
