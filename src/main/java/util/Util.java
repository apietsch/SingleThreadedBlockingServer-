package util;

import java.nio.ByteBuffer;

public class Util {
    public static int transmogrify(int data) {
        return Character.isLetter(data) ? data ^ ' ' : data;
    }

    public static void transmogrify(ByteBuffer buf) {
        // pos=0 limit=80 cap=80
        // "hello\n" pos=6 limit=80 cap=80
        // call flip method to set limit=6 and pos=0
        //
        buf.flip();
        for (int i = 0; i < buf.limit(); i++) {
            buf.put(i, (byte) transmogrify(buf.get(i)));
        }

    }
}
