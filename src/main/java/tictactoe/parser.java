package tictactoe;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public interface parser {
    void parse(String path) throws ParserConfigurationException, SAXException, IOException;
}