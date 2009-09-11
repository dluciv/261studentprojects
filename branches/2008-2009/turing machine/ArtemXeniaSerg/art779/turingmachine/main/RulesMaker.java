/*
 * @author art779j
 * Licensed under the GNU General Public License v2 
 * @copyrights 261studentprojects
 *  
 */

package art779.turingmachine.main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RulesMaker {
    private enum XML_TAGS {
        program, start, end, passage, initstate, endstate, state, symbol, move
    }

    public static Rules parseXMLFileProgram(String filename) {
    	File xmlFileContent = new File(filename);
        Rules rules = null; 
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFileContent);
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            
            if (!root.getNodeName().equals(String.valueOf(XML_TAGS.program))) {
                return null;
                //@todo throw exception if file have wrong root element
            }
            NodeList node = doc.getElementsByTagName(String.valueOf(XML_TAGS.end));
            if (node.getLength() == 0) {
                return null;
            }
            String fState = node.item(0).getTextContent();
            node = doc.getElementsByTagName(String.valueOf(XML_TAGS.start));
            if (node.getLength() == 0) {
                return null;
            }
            String sState = node.item(0).getTextContent();
            rules = new Rules(sState,fState);

            node = doc.getElementsByTagName(String.valueOf(XML_TAGS.passage));
            for (int i = 0; i < node.getLength(); i++) {
                Element current = (Element) node.item(i);
                
                Element start = (Element) current.getElementsByTagName(String.valueOf(XML_TAGS.initstate)).item(0);
                String startState = ((Element) start.getElementsByTagName(String.valueOf(XML_TAGS.state)).item(0)).getTextContent();
                String startSymbol = ((Element) start.getElementsByTagName(String.valueOf(XML_TAGS.symbol)).item(0)).getTextContent();
                
                
                Element end = (Element) current.getElementsByTagName(String.valueOf(XML_TAGS.endstate)).item(0);
                String endState = ((Element) end.getElementsByTagName(String.valueOf(XML_TAGS.state)).item(0)).getTextContent();
                String endSymbol = null;
            	endSymbol = ((Element) end.getElementsByTagName(String.valueOf(XML_TAGS.symbol)).item(0)).getTextContent();
                String endMove = ((Element) end.getElementsByTagName(String.valueOf(XML_TAGS.move)).item(0)).getTextContent();
                
                rules.setRule(startState,startSymbol,endState,
            		endMove.equals(String.valueOf(Action.H)) ? Action.H
            				: (endMove.equals(String.valueOf(Action.R)) ? Action.R : Action.L)
            		, endSymbol);                
            }
        } catch (SAXException ex) {
        	System.out.println(ex.toString());
        } catch (IOException ex) {
        	System.out.println(ex.toString());
        } catch (ParserConfigurationException ex) {
        	System.out.println(ex.toString());
        }
        return rules;
    }
    
	public static void main(String[] args){

		String str = "";
		
		Rules rules = parseXMLFileProgram("UMT.xml");
			str += rules.toString();

		System.out.println(str);

	}
}
