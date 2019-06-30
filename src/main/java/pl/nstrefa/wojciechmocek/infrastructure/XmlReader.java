package pl.nstrefa.wojciechmocek.infrastructure;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pl.nstrefa.wojciechmocek.domain.Order;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.function.Function;

class XmlReader implements Reader {


    private final NodeList requests;
    private int currentPosition;
    private int requestsCount;

    XmlReader(java.io.Reader reader) throws ParserConfigurationException, IOException, SAXException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(reader));
        this.requests = document.getElementsByTagName("request");
        currentPosition = 0;
        requestsCount = requests.getLength();
    }

    public Order read() throws ReaderException {

        if (currentPosition == requestsCount) {
            return null;
        }

        Node request = requests.item(currentPosition);

        Order o;
        try {
            o = parseNode(request);
        } catch (NullPointerException e) {
            throw new ReaderException("Could not create order", e);
        } finally {
            ++currentPosition;
        }

        return o;
    }

    @Override
    public boolean hasNext() {
        return currentPosition < requestsCount;
    }

    private Order parseNode(Node request) {
        if (request.getNodeType() != Node.ELEMENT_NODE) {
            return null;
        }

        var requestElement = (Element) request;

        return new Order(
            getInnerElementValue(requestElement, "clientId", String::valueOf),
            getInnerElementValue(requestElement, "requestId", Long::valueOf),
            getInnerElementValue(requestElement, "name", String::valueOf),
            getInnerElementValue(requestElement, "quantity", Integer::valueOf),
            getInnerElementValue(requestElement, "price", Double::valueOf)
        );

    }

    private <T> T getInnerElementValue(Element element, String innerElementName, Function<String, T> castFunction) {

        var property = element.getElementsByTagName(innerElementName).item(0);
        if (null == property) {
            return null;
        }
        return (T) castFunction.apply(property.getTextContent());
    }
}
