package pl.wmocek.orders.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

class ScreenWriterFactory {

    Writer create(){

        return new Writer() {

            private java.io.Writer out
                = new BufferedWriter(new OutputStreamWriter(System.out));

            @Override
            public int write(String[] buff) throws IOException {
                int n=0;
                for (String s : buff) {
                    out.write(s + "\n");
                    ++n;
                }
                return n;
            }
        };
    }
}
