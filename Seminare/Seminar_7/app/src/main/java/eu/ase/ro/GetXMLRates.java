package eu.ase.ro;

import android.content.Intent;
import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class GetXMLRates extends AsyncTask<String, Void, List<Rate>> {


    @Override
    protected List<Rate> doInBackground(String... strings) {

        List<Rate> result = new ArrayList<>();
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();

            InputStream inputStream = http.getInputStream();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            NodeList lista = document.getElementsByTagName("Rate");
            for(int i = 0; i < lista.getLength(); i++) {
                Node node = lista.item(i);
                if(node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    Rate rate = null;
                    if(element.hasAttribute("multiplier")) {
                        rate = new Rate(element.getAttribute("currency"),Float.parseFloat(element.getTextContent()), Integer.parseInt(element.getAttribute("multiplier")));

                    } else {
                        rate = new Rate(element.getAttribute("currency"),Float.parseFloat(element.getTextContent()));
                    }
                    result.add(rate);
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return result;
    }
}
