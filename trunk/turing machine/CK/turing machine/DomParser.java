package turingmachine;

/**
 *
 * @author Кирилл
 */
import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class DomParser {

    private static Document doc = null;
    private static String currentState = "";
    private static String currentSymbol = "";
    private static String txt = "";
    private static HashMap<InitialCondition, Action> rules = new HashMap<InitialCondition, Action>();
    private static InitialCondition currentInit = null;
    private static boolean DEAL_WITH_INITIAL = true;

    public static Document parseXML(File file) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder DOMbuilder = factory.newDocumentBuilder();
        Document tree = DOMbuilder.parse(file);
        return tree;

    }

    public static void getRules(Node node, int level, Machine tm) {
        NodeList nl = node.getChildNodes();
        String parent = "";
        for (int i = 0, cnt = nl.getLength(); i < cnt; i++) {
            if (nl.item(i).getNodeType() == Node.TEXT_NODE) {
                parent = nl.item(i).getParentNode().getNodeName();
                txt = nl.item(i).getNodeValue();
                if (parent.equals("state")) {
                    currentState = txt;
                } else if (parent.equals("symbol")) {
                    currentSymbol = txt;
                    if (DEAL_WITH_INITIAL) {
                        currentInit = new InitialCondition(currentState, currentSymbol);
                        tm.rules.put(currentInit, new Action("", "", ""));
                        DEAL_WITH_INITIAL = false;
                    }
                } else if (parent.equals("move")) {
                    tm.rules.get(currentInit).setState(currentState);
                    tm.rules.get(currentInit).setSymbol(currentSymbol);
                    tm.rules.get(currentInit).setDirection(txt);
                    DEAL_WITH_INITIAL = true;
                } else if (parent.equals("first")) {
                    tm.first = txt;
                } else if (parent.equals("fin")) {
                    tm.fin = txt;
                }
//            } else {
//                if(parent.equals("rule")){
//                if (nl.item(i).getNodeName().equals("initcondition")) {
//                    rules.put(new InitialCondition("",""), new Action("","",""));
//                }
//                }
            }
            getRules(nl.item(i), level + 1, tm);
        }

    }

    public static Machine getMachine(String fName) { //throws SAXException, IOException, ParserConfigurationException  {
        try {
            doc = parseXML(new File(fName));
            Machine tm = new Machine();
            getRules(doc, 0, tm);
            rules = tm.rules;
            printRules();
            return tm;
        } catch (SAXException se) {
        } catch (IOException ie) {
        } catch (ParserConfigurationException pe) {
        }
        return null;
    }

    public static void printRules() {
        for (InitialCondition init : rules.keySet()) {
            System.out.println(init.state + " " + init.symbol + "->" + rules.get(init).state + " " + rules.get(init).symbol + " " + rules.get(init).direction);
        }
    }
}
