package turingmachine;

/**
 *
 * @author Кирилл & Lapin Sergey 261group
 */
import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class RulesParser {
    public RulesParser(String fName) {
        try {
            Document doc = parseXML(new File(fName));
            getRules(doc);
            
        } catch (SAXException se) {
        } catch (IOException ie) {
        } catch (ParserConfigurationException pe) {
        }
    }
    public String fin = null;
    public String first = null;
    public RulesTable rules = new RulesTable();
    
    public static Document parseXML(File file) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder DOMbuilder = factory.newDocumentBuilder();
        Document tree = DOMbuilder.parse(file);
        return tree;
    }

    public void getRules(Node node) {
        String nodename = node.getNodeName();
        if(nodename.equals("#text"))
            return;
        if(nodename.equals("program") || nodename.equals("#document"))
        {
            NodeList nodelist = node.getChildNodes();
            for(int i = 0; i < nodelist.getLength(); i++)
                getRules(nodelist.item(i));
            return;
        }
        if(nodename.equals("first"))
        {
            first = node.getChildNodes().item(0).getNodeValue();
            return;
        }
        if(nodename.equals("fin")){
            fin = node.getChildNodes().item(0).getNodeValue();
            return;
        }
        if(nodename.equals("rule"))
        {
            rules.rules.add(getRule(node));
        }
    }
    
    public InitialCondition getInitCondition(Node node) {
        InitialCondition res = new InitialCondition();
        NodeList nodelist = node.getChildNodes();
        for(int i = 0; i < nodelist.getLength(); i++)
        {
            Node tmp = nodelist.item(i);
            String nodename = tmp.getNodeName();            
            if(nodename.equals("state")){
                res.setState(tmp.getChildNodes().item(0).getNodeValue());
            }
            if(nodename.equals("symbol")){
                if(tmp.getChildNodes().item(0).getNodeValue() == null){
                     res.setSymbol(null);
                     break;
                }

                res.setSymbol(tmp.getChildNodes().item(0).getNodeValue().charAt(0));
            }            
        }
        return res;
    }
    
    public Action getAction(Node node) {
        Action res = new Action();
        res.setDirection("");
        NodeList nodelist = node.getChildNodes();
        for(int i = 0; i < nodelist.getLength(); i++)
        {
            Node tmp = nodelist.item(i);
            String nodename = tmp.getNodeName();            
            if(nodename.equals("state")){
                res.setState(tmp.getChildNodes().item(0).getNodeValue());
            }
            if(nodename.equals("symbol")){
                if(tmp.getChildNodes().item(0) == null){
                     res.setSymbol(null);
                     break;
                }

                res.setSymbol(tmp.getChildNodes().item(0).getNodeValue().charAt(0));
            }
            if(nodename.equals("move")){
                res.setDirection(tmp.getChildNodes().item(0).getNodeValue());
            }  
        }
        return res;
    }

    public Rule getRule(Node node) {
        Rule res = new Rule();
        NodeList nodelist = node.getChildNodes();        
        for(int i = 0; i < nodelist.getLength(); i++)
        {
            Node tmp = nodelist.item(i);
            String nodename = tmp.getNodeName();
            if(nodename.equals("initcondition")){
                res.setInitCondition(getInitCondition(tmp));
            }
            if(nodename.equals("action")){
                res.setAct(getAction(tmp));
            }
        }
        return res;
    }       
}
