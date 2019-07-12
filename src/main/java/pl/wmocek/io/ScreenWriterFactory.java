package pl.wmocek.io;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

/**
 * Factory to create Writer to standard output
 */
public class ScreenWriterFactory {

    public Writer create(){
        return new BasicWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    }
}
