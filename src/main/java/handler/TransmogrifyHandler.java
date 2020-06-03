package handler;

import util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TransmogrifyHandler implements Handler<Socket> {
    @Override
    public void handle(Socket s) throws IOException {
        try (
                s; // Java 9
                InputStream in = s.getInputStream();
                OutputStream out = s.getOutputStream();
        ) {
            int data;
            while ((data = in.read()) != -1) {
                if (data == '%') throw new IOException("Uppsie");
                out.write(Util.transmogrify(data));
            }
        }
    }
}
