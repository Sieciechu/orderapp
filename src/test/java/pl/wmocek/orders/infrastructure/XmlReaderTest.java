package pl.wmocek.orders.infrastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import pl.wmocek.orders.domain.Order;
import pl.wmocek.orders.domain.Product;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

class XmlReaderTest {

    @Test
    void read() throws ParserConfigurationException, IOException, SAXException, ReaderException {

        // given
        String someXml = "<requests>\n" +
            "    <request>\n" +
            "        <customerId>1</customerId>\n" +
            "        <requestId>2</requestId>\n" +
            "        <name>Bułka</name>\n" +
            "        <quantity>3</quantity>\n" +
            "        <price>10.00</price>\n" +
            "    </request>\n" +
            "</requests>\n";


        XmlReader r = new XmlReader(new StringReader(someXml));

        // when
        Order o = r.read();

        // then
        Order expectedOrder = Order.create("1", 2, new Product("Bułka", 3, 10.0));
        Assertions.assertEquals(expectedOrder, o);
    }
}