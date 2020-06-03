package server;

import handler.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Executors;

/**
 * run main and connect via telnet or ncat -v localhost 8080
 * exit ncat via ^Z to see the disconnected message
 */
public class SingleThreadedPollingNonBlockingServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);

        Handler<SocketChannel> handler = new TransmogrifyChannelHandler();
        Collection<SocketChannel> sockets = new ArrayList<>();
        while(true) {
            SocketChannel sc = ssc.accept(); // almost always null (.. if there is no client)
            if (sc != null) {
                sockets.add(sc);
                System.out.println("Connected to: " + sc);
                sc.configureBlocking(false);
            }

            for (SocketChannel socket : sockets) {
                if (socket.isConnected()) {
                    handler.handle(socket);
                }
            }
            sockets.removeIf(socket -> !socket.isConnected());
        }
    }
}
