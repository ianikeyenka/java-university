import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CurrencyHandler extends DefaultHandler {
    private List<Currency> currencyList;
    private Currency currency;
    private StringBuilder data;

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("DailyExRates")) {
            currencyList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("Currency")) {
            currency = new Currency();
            currency.setId(Integer.parseInt(attributes.getValue("Id")));
        }

        data = new StringBuilder();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length).trim());
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("NumCode")) {
            currency.setNumCode(data.toString());
        } else if (qName.equalsIgnoreCase("CharCode")) {
            currency.setCharCode(data.toString());
        } else if (qName.equalsIgnoreCase("Scale")) {
            currency.setScale(Integer.parseInt(data.toString()));
        } else if (qName.equalsIgnoreCase("Name")) {
            currency.setName(data.toString());
        } else if (qName.equalsIgnoreCase("Rate")) {
            currency.setRate(Double.parseDouble(data.toString()));
        } else if (qName.equalsIgnoreCase("Currency")) {
            currencyList.add(currency);
        }
    }
}
