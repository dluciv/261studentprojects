package eda.tm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Programm {

    private HashMap<StateSymbol, StateSymbolMove> rules;
    private String beginState;
    private String endState;

    public Programm(String beginState, String endState) {
        this.beginState = beginState;
        this.endState = endState;
        this.rules = new HashMap<StateSymbol, StateSymbolMove>();
    }

    public HashMap<StateSymbol, StateSymbolMove> getRules() {
        return rules;
    }

    public void setRules(HashMap<StateSymbol, StateSymbolMove> rules) {
        this.rules = rules;
    }

    public String getBeginState() {
        return beginState;
    }

    public void setBeginState(String beginState) {
        this.beginState = beginState;
    }

    public String getEndState() {
        return endState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

    public void addRule(StateSymbol stateSymbol, StateSymbolMove stateSymbolMove) {
        rules.put(stateSymbol, stateSymbolMove);
    }

    public Tape performMove(StateSymbol tmp, Tape tape) {
        StateSymbolMove nextMove = rules.get(tmp);
        tmp.setState(nextMove.getState());
        tape.write(nextMove.getSymbol());
        tape.changePosition(nextMove.getMove());
        tmp.setSymbol(tape.returnCurrentSymbol());
        if (nextMove.getState() != endState) {
            return performMove(tmp, tape);
        } else {
            return tape;
        }
    }

    public Tape execute(Tape tape) {
        StateSymbol start = new StateSymbol(beginState, tape.returnCurrentSymbol());
        return performMove(start, tape);
    }

    private enum XML_TAGS {

        program, start, end, passage, initstate, endstate, state, symbol, move
    }

    public void writeToXML(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder parser = factory.newDocumentBuilder();
            Document doc = parser.newDocument();

            Element root = doc.createElement(String.valueOf(XML_TAGS.program));
            doc.appendChild(root);

            Element start = doc.createElement(String.valueOf(XML_TAGS.start));
            start.setTextContent(beginState);
            root.appendChild(start);
            System.out.println(start);

            Element end = doc.createElement(String.valueOf(XML_TAGS.end));
            end.setTextContent(endState);
            root.appendChild(end);
            System.out.println(end);

            System.out.println(root);

            Iterator<Entry<StateSymbol, StateSymbolMove>> i = rules.entrySet().iterator();

            while (i.hasNext()) {
                Element par = doc.createElement(String.valueOf(XML_TAGS.passage));
                Entry<StateSymbol, StateSymbolMove> e = i.next();
                Element initstate = doc.createElement(String.valueOf(XML_TAGS.initstate));
                root.appendChild(initstate);
                Element initStateState = doc.createElement(String.valueOf(XML_TAGS.state));
                initStateState.setTextContent(e.getKey().getState());
                initstate.appendChild(initStateState);
                Element initStateSymbol = doc.createElement(String.valueOf(XML_TAGS.symbol));
                initStateSymbol.setTextContent(String.valueOf(e.getKey().getSymbol()));
                initstate.appendChild(initStateSymbol);

                Element endSt = doc.createElement(String.valueOf(XML_TAGS.endstate));
                root.appendChild(endSt);
                Element endStateState = doc.createElement(String.valueOf(XML_TAGS.state));
                endStateState.setTextContent(e.getValue().getState());
                endSt.appendChild(endStateState);
                Element endStateSymbol = doc.createElement(String.valueOf(XML_TAGS.symbol));
                endStateSymbol.setTextContent(String.valueOf(e.getValue().getSymbol()));
                endSt.appendChild(endStateSymbol);
                Element endStateMove = doc.createElement(String.valueOf(XML_TAGS.move));
                endStateMove.setTextContent(String.valueOf(e.getValue().getMove()));
                endSt.appendChild(endStateMove);
                root.appendChild(par);
                par.appendChild(initstate);
                par.appendChild(endSt);
            }


            XMLSerializer serializer = new XMLSerializer();
            serializer.setOutputCharStream(new FileWriter(file));
            serializer.serialize(doc);
//FileOutputStream writer = new FileOutputStream(file);
//DOMSource domSource = new DOMSource(doc);
//StreamResult streamResult = new StreamResult(writer);
//TransformerFactory tf = TransformerFactory.newInstance();
//Transformer serializer = tf.newTransformer();
//serializer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
//// serializer.setOutputProperty(OutputKeys.DOCTYPE_SY STEM,"users.dtd");
////serializer.setOutputProperty(OutputKeys.INDENT,"ye s");
////serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
//serializer.transform(domSource, streamResult);
//String result = writer.getBuffer().toString();
        } catch (IOException ex) {
            Logger.getLogger(Programm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Programm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//    public static void main(String[] args) {
//        Programm p = new Programm("q0","q1");
//        p.addRule(new StateSymbol("q0", '1'), new StateSymbolMove(Moving.RIGHT, "q1", '0'));
//        p.addRule(new StateSymbol("q0", '0'), new StateSymbolMove(Moving.LEFT, "q1", '0'));
//        p.addRule(new StateSymbol("q1", '1'), new StateSymbolMove(Moving.STEND, "q0", '1'));
//        
//        p.writeToXML();
//    }
    
    public static Programm parseProgram(File filename){
            Programm program = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(filename);
            doc.getDocumentElement().normalize();
            Element root = doc.getDocumentElement();
            if(!root.getNodeName().equals(String.valueOf(XML_TAGS.program))) return null;
            //@todo throw exception if file have wrong root element
            NodeList node = doc.getElementsByTagName(String.valueOf(XML_TAGS.start));
            if(node.getLength() == 0) return null;
            String endStateProgram = node.item(0).getTextContent();
            node = doc.getElementsByTagName(String.valueOf(XML_TAGS.end));
            if(node.getLength() == 0) return null;
            String startStateProgram = node.item(0).getTextContent();
            program = new Programm(startStateProgram, endStateProgram);
            node = doc.getElementsByTagName(String.valueOf(XML_TAGS.passage));
            for (int i = 0; i < node.getLength(); i++) {
                Element current = (Element) node.item(i);
                Element start = (Element) current.getElementsByTagName(String.valueOf(XML_TAGS.initstate)).item(0);
                String startState = ((Element) start.getElementsByTagName(String.valueOf(XML_TAGS.state)).item(0)).getTextContent();
                String startSymbol = ((Element) start.getElementsByTagName(String.valueOf(XML_TAGS.symbol)).item(0)).getTextContent();
                Element end = (Element) current.getElementsByTagName(String.valueOf(XML_TAGS.endstate)).item(0);
                String endState = ((Element) end.getElementsByTagName(String.valueOf(XML_TAGS.state)).item(0)).getTextContent();
                String endSymbol = ((Element) end.getElementsByTagName(String.valueOf(XML_TAGS.symbol)).item(0)).getTextContent();
                String endMove = ((Element) end.getElementsByTagName(String.valueOf(XML_TAGS.move)).item(0)).getTextContent();
                StateSymbol ss = new StateSymbol(startState, startSymbol.charAt(0));
                StateSymbolMove ssm = new StateSymbolMove(
                        endMove.equals(String.valueOf(Moving.STEND)) ? Moving.STEND : 
                            (endMove.equals(String.valueOf(Moving.RIGHT)) ? Moving.RIGHT : Moving.LEFT),
                            endState, endSymbol.charAt(0));
                program.addRule(ss, ssm);
            }
        } catch (SAXException ex) {
            Logger.getLogger(Programm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Programm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Programm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return program;
    }
    
    public static void main(String[] args) {
        Programm p = parseProgram(new File("/home/nastya/dddd/Program.xml"));
        System.out.println(p);
    }
    
    @Override
    public String toString(){
        String s = "PROGRAM: \n";
        s += "  start: " + beginState + "\n"; 
        s += "  end: " + endState + "\n\n"; 
        Iterator<Entry<StateSymbol, StateSymbolMove>> i = rules.entrySet().iterator();
        while(i.hasNext()){
            Entry<StateSymbol, StateSymbolMove> e = i.next();
            s += "  " + e.getKey() + " -> " + e.getValue() + "\n";
        }
        return s;
    }
            
}
