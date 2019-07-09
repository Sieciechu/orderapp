package pl.wmocek.orders.io;

import java.io.IOException;

class BasicWriter implements Writer {

    private java.io.Writer out;

    BasicWriter(java.io.Writer out) {
        this.out = out;
    }

    @Override
    public int write(String[] buff) throws IOException {
        int n=0;
        for (String s : buff) {
            out.write(s + "\n");
            ++n;
        }

        out.flush();

        return n;
    }
}
