package pl.wmocek.orders.io.writer;

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

        String inputBuff = "Hello,\n" +
            "my name is\n" +
            "John Smith";

        // when
        int n = w.write(inputBuff);

        // then
        Assertions.assertEquals(inputBuff.length(), n);
        String expectedText = "Hello,\n" +
            "my name is\n" +
            "John Smith";

        Assertions.assertEquals(expectedText, stringWriter.toString());
    }
}