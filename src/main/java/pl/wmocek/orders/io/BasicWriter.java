package pl.wmocek.orders.io;

import java.io.IOException;

class BasicWriter implements Writer {

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
