import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class XmlFormatStrategy implements FileFormatStrategy {
    
    @Override
    public ArrayList<Question> loadQuestions(String filePath) {
        ArrayList<Question> questions = new ArrayList<>();
        
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            
            NodeList questionNodes = doc.getElementsByTagName("question");
            
            for (int i = 0; i < questionNodes.getLength(); i++) {
                Node node = questionNodes.item(i);
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    
                    String category = getTagValue("category", element);
                    int value = Integer.parseInt(getTagValue("value", element));
                    String content = getTagValue("content", element);
                    String correctAnswer = getTagValue("correctAnswer", element);
                    
                    Question question = new Question(category, value, content, null, correctAnswer);
                    
                    // Parse options if present
                    NodeList optionNodes = element.getElementsByTagName("option");
                    for (int j = 0; j < optionNodes.getLength(); j++) {
                        String option = optionNodes.item(j).getTextContent();
                        question.addOption(option);
                    }
                    
                    questions.add(question);
                }
            }
            
        } catch (Exception e) {
            System.err.println("Error loading XML file: " + e.getMessage());
        }
        
        return questions;
    }
    
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag);
        if (nodeList.getLength() > 0) {
            Node node = nodeList.item(0);
            if (node != null) {
                return node.getTextContent();
            }
        }
        return "";
    }
}