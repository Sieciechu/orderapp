package pl.wmocek.orders.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;

class BasicWriterTest {

    @Test
    void write() throws IOException {
        // given
        StringWriter stringWriter = new StringWriter();
        BasicWriter w = new BasicWriter(stringWriter);

        String[] inputBuff = new String[]{"Hello,", "my name is", "John Smith"};

        // when
        int n = w.write(inputBuff);

        // then
        Assertions.assertEquals(3, n);
        String expectedText = "Hello,\n" +
            "my name is\n" +
            "John Smith";

        Assertions.assertEquals(expectedText, stringWriter.toString());
    }
}