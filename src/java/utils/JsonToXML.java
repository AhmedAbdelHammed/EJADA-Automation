package utils;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

import java.io.File;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class JsonToXML {

    /**
     * Function that transform xml file to a String
     * @param doc is the document you want to transform
     * @return the xml in string type
     */
    private static String documentToString(Document doc) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    /**
     * Function that convert xml file to JSON String
     * @param filePath is the file path of the xml file
     * @return the JSON string
     */
    public static String convertXmlFileToJson(String filePath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            String xmlString = documentToString(document);
            JSONObject jsonObject = XML.toJSONObject(xmlString);
            return jsonObject.toString(4);
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    /**
     * Function that get the Integer value of an attribute in a JSON String
     * @param keyJsonPath is the json path of the attribute (key)
     * @param JSON is the JSON string
     * @return the Integer value of the attribute
     */
    public static Integer getIntegerJsonAttributeValue(String keyJsonPath, String JSON){
        Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
        return JsonPath.using(conf).parse(JSON).read(keyJsonPath, Integer.class);
    }

    /**
     * Function that extract any list from the JSON String
     * @param keyJsonPath is the json path of the attribute (key)
     * @param JSON is the JSON string
     * @return the required list
     */
    public static List<String> getList(String keyJsonPath, String JSON){
        Configuration conf = Configuration.builder().options(Option.DEFAULT_PATH_LEAF_TO_NULL).build();
        return JsonPath.using(conf).parse(JSON).read(keyJsonPath, List.class);
    }

}
