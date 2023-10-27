import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class SAXRunner {
    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            CurrencyHandler handler = new CurrencyHandler();
            parser.parse(new File("src/xml/currency.xml"), handler);
            List<Currency> currencyList = handler.getCurrencyList();
            for (Currency currency : currencyList) {
                System.out.println(currency);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
