package gui;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import java.util.Vector;
import java.io.*;

/**
 * @author Murashov Kirill
 */

public class RegExpDataList
{
    private Vector<RegExpData> regExps = new Vector<RegExpData>();
    private String fileName = "./261studentprojects\\automata\\KirRen\\gui\\regexp.xml";

    public RegExpDataList() throws IOException
    {
        InputStream is = new FileInputStream(fileName);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try
        {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(is);
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            e.printStackTrace();
        }

        Element regE = doc.getDocumentElement();
        NodeList regExpNL = regE.getElementsByTagName("regexp");

        for (int j = 0; j < regExpNL.getLength(); j++)
        {
            Element regExpE = (Element)regExpNL.item(j);

            RegExpData tmpRegData;

            try
            {
                String value = regExpE.getElementsByTagName("value").item(0).getFirstChild().getNodeValue();
                tmpRegData = new RegExpData(value);

                Node test1Node = regExpE.getElementsByTagName("test1").item(0).getFirstChild();
                if ( test1Node != null )
                {
                    String word = test1Node.getNodeValue();
                    tmpRegData.setTest1Word(word);
                }

                Node test2Node = regExpE.getElementsByTagName("test2").item(0).getFirstChild();
                if ( test2Node != null )
                {
                    String word = test2Node.getNodeValue();
                    tmpRegData.setTest2Word(word);
                }

                Node test3Node = regExpE.getElementsByTagName("test3").item(0).getFirstChild();
                if ( test3Node != null )
                {
                    String word = test3Node.getNodeValue();
                    tmpRegData.setTest3Word(word);
                }

                Element test4E = (Element) regExpE.getElementsByTagName("test4").item(0);
                int len = Integer.parseInt(test4E.getElementsByTagName("len").item(0).getFirstChild().getNodeValue());
                int num = Integer.parseInt(test4E.getElementsByTagName("num").item(0).getFirstChild().getNodeValue());
                tmpRegData.setTest4Word(len,num);

                regExps.add(tmpRegData);

            }
            catch (Exception e)
            {
                e.printStackTrace(System.err);
            }
        }
    }

    public Vector<RegExpData> getVecRegExpData()
    {
        return regExps;
    }

    public void saved()
    {
        Document doc = null;
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            doc = db.newDocument();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }

        Element regE = doc.createElement("reg");
        doc.appendChild(regE);

        for (RegExpData exp: regExps)
        {
            Element regExpE = doc.createElement("regexp");

            Element valueE = doc.createElement("value");
            valueE.appendChild(doc.createTextNode(exp.getExp()));
            regExpE.appendChild(valueE);

            Element test1E = doc.createElement("test1");
            test1E.appendChild( doc.createTextNode(exp.getTest1Word()));
            regExpE.appendChild(test1E);

            Element test2E = doc.createElement("test2");
            test2E.appendChild( doc.createTextNode(exp.getTest2Word()));
            regExpE.appendChild(test2E);

            Element test3E = doc.createElement("test3");
            test3E.appendChild( doc.createTextNode(exp.getTest3Word()));
            regExpE.appendChild(test3E);

            Element test4E = doc.createElement("test4");

            Element lenE = doc.createElement("len");
            lenE.appendChild( doc.createTextNode(""+exp.getLengthTest4Word()));
            test4E.appendChild(lenE);

            Element numE = doc.createElement("num");
            numE.appendChild( doc.createTextNode(""+exp.getNumberTest4Word()));
            test4E.appendChild(numE);

            regExpE.appendChild(test4E);

            regE.appendChild(regExpE);
        }

        try
        {
            OutputStream fout = new FileOutputStream(new File(fileName));
            OutputFormat of = new OutputFormat(doc, "Windows-1251", true);
            XMLSerializer xs = new XMLSerializer(fout, of);
            xs.serialize(doc);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}

