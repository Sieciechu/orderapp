package pl.wmocek.orders.io.writer;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class ScreenWriterFactory {

    public Writer create(){
        return new BasicWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }
}
