package pl.wmocek.orders.io.writer;

import java.io.IOException;

public class BasicWriter implements Writer {

    private java.io.Writer out;

    BasicWriter(java.io.Writer out) {
        this.out = out;
    }

    @Override
    public int write(String in) throws IOException {

        out.write(in);
        out.flush();

        return in.length();
    }
}
