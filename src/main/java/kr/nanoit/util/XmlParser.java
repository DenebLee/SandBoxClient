package kr.nanoit.util;

import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class XmlParser {
    private DocumentBuilderFactory documentBuilderFactory = null;
    private DocumentBuilder documentBuilder = null;
    private Document document = null;
    private Element serverInfo = null;
    private Node severInfoChildNodes = null;
    private NodeList List = null;
    private Map<String, String> parsedData = new HashMap<>();
    private String xmlString;
    private final EndPoint endPoint;


    /**
     * @param value 서버에서 던져준 Xml
     */

    public XmlParser(String value) throws IOException {
        endPoint = new EndPoint();
        this.xmlString = value;
    }

    public Map<String, String> parsing() throws ParserConfigurationException, IOException, SAXException {

        // 자신의 static 메서드를 가지고 객체를 생성 : 싱글톤 패턴
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        // 다른 클래스의 객체를 가지고, 객체를 생성하면 팩토리 패턴.
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(new InputSource(new StringReader(xmlString)));
        //팩 토리 메서드 패턴  공장에서 찍어줌
        List = document.getElementsByTagName("IP");


        serverInfo = document.getDocumentElement();
        severInfoChildNodes = (Node) serverInfo.getChildNodes();
        List = severInfoChildNodes.getChildNodes();

        for (int i = 0; i < List.getLength(); i++) {
            Node item = List.item(i);
            if (item.getNodeType() == Node.ELEMENT_NODE) {
                parsedData.put(item.getNodeName(), item.getTextContent());
            }
        }
        return parsedData;
    }

    public void tst(){
        try {
           Map<String,String> test =  parsing();
            endPoint.setIP(test.get("ip"));
            endPoint.setPORT(test.get("port"));
            log.info("[HTTPCLIENT] XML PARSING COMPLETE IP : {} PORT : {}", endPoint.getIP(),endPoint.getPORT() );

        } catch (Exception e) {
            System.out.println("Parsing Exception Err : " + e);
            e.printStackTrace();
        }
    }
}