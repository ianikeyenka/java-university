import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMRunner {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src/xml/currency.xml"));
            Element root = document.getDocumentElement();
            System.out.println("Date: " + root.getAttribute("Date"));
            NodeList currencyList = root.getElementsByTagName("Currency");
            for (int i = 0; i < currencyList.getLength(); i++) {
                Element element = (Element) currencyList.item(i);
                Currency currency = new Currency(
                        Integer.parseInt(element.getAttribute("Id")),
                        element.getElementsByTagName("NumCode").item(0).getTextContent(),
                        element.getElementsByTagName("CharCode").item(0).getTextContent(),
                        Integer.parseInt(element.getElementsByTagName("Scale").item(0).getTextContent()),
                        element.getElementsByTagName("Name").item(0).getTextContent(),
                        Double.parseDouble(element.getElementsByTagName("Rate").item(0).getTextContent())
                );
                System.out.println(currency);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
