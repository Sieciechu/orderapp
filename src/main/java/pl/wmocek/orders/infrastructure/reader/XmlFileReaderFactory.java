package pl.wmocek.orders.infrastructure.reader;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class XmlFileReaderFactory implements FileReaderFactory {

    public Reader create(String fileFullPath)
        throws FileNotFoundException, IOException, SAXException, ParserConfigurationException
    {

        return new XmlReader(new BufferedReader(new FileReader(fileFullPath)));
    }
}
