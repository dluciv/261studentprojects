package TM;

import java.util.*;
import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

public class XMLParser {
	public static Machine m = new Machine();
	
	public static Document getNodes(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder DOMbuilder = factory.newDocumentBuilder();
        Document tree = DOMbuilder.parse(file);
        return tree;
	}
	
	static Integer state=0, next=0;
	static String read="", write="";
	static char move=' ';
	static Boolean stateok=false, nextok=false, readok=false, writeok=false, moveok=false;
	
	public static void parseNode(Node node) throws ParserConfigurationException, SAXException, IOException, MachineException {
			NodeList nl = node.getChildNodes();
			String name, value;
			for (int i=0; i<nl.getLength(); i++) {
				name = nl.item(i).getParentNode().getNodeName();
				
				if (nl.item(i).getNodeType() == Node.TEXT_NODE) {
					value = nl.item(i).getNodeValue();
					//System.out.println(name+" > "+value);
					if (name == "first") m.setFirst(Integer.parseInt(value));
					if (name == "fin") m.setFin(Integer.parseInt(value));
					if (name == "state") {
						state = Integer.parseInt(value);
						stateok = true;
					}
					if (name == "next") {
						next = Integer.parseInt(value);
						nextok = true;
					}
					if (name == "read") {
						read = value;
						readok = true;
					}
					if (name == "write") {
						write = value;
						writeok = true;
					}
					if (name == "move") {
						move = value.charAt(0);
						moveok = true;
					}
					//System.out.print(name+" ");
					if (stateok && nextok && readok && writeok && moveok) {
						//System.out.println(state+" "+read+" "+next+" "+write+" "+move);
						m.addRule(state, read, next, write, move);
						stateok = nextok = readok = writeok = moveok = false;
					}
				}
				//XMLParser.parseHelper
				parseNode(nl.item(i));
			}
	}
	
	public static Machine getMachine(String filename) {
		try {
			Document tree = getNodes(new File(filename));
			XMLParser.parseNode(tree);
			return m;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}