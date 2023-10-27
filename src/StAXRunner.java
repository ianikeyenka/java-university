import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;

public class StAXRunner {
    public static void main(String[] args) {
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("src/xml/currency.xml"));
            while (reader.hasNext()) {
                int event = reader.next();
                if (event == XMLStreamConstants.START_ELEMENT) {
                    if (reader.getLocalName().equalsIgnoreCase("DailyExRates")) {
                        System.out.println("Date: " + reader.getAttributeValue(null, "Date"));
                    } else if (reader.getLocalName().equalsIgnoreCase("Currency")) {
                        parseCurrency(reader);
                    }
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseCurrency(XMLStreamReader reader) throws XMLStreamException {

        Currency currency = new Currency();
        currency.setId(Integer.parseInt(reader.getAttributeValue(null, "Id")));

        while (reader.hasNext()) {
            int event = reader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                String elementName = reader.getLocalName();

                switch (elementName) {
                    case "NumCode":
                        currency.setNumCode(reader.getElementText());
                        break;
                    case "CharCode":
                        currency.setCharCode(reader.getElementText());
                        break;
                    case "Scale":
                        currency.setScale(Integer.parseInt(reader.getElementText()));
                        break;
                    case "Name":
                        currency.setName(reader.getElementText());
                        break;
                    case "Rate":
                        currency.setRate(Double.parseDouble(reader.getElementText()));
                        break;
                }
            } else if (event == XMLStreamConstants.END_ELEMENT) {
                if (reader.getLocalName().equalsIgnoreCase("Currency")) {
                    System.out.println(currency);
                    break;
                }
            }
        }
    }
}
