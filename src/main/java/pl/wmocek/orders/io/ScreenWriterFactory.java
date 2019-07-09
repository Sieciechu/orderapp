package pl.wmocek.orders.io;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

class ScreenWriterFactory {

    Writer create(){
        return new ScreenWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }
}
