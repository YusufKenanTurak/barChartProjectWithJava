package sample;


import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class parse {
    public void XMLOpener(File selectedFile) {
        {

            try {
                //Get Document Builder
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();

                //Build Document
                Document document = builder.parse(selectedFile);

                //Normalize the XML Structure; It's just too important !!
                document.getDocumentElement().normalize();

                //Here comes the root node
                Element root = document.getDocumentElement();
                System.out.println(root.getNodeName());

                //Get all employees
                NodeList nList = document.getElementsByTagName("record");
                System.out.println("============================");
                visitChildNodes(nList);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //This function is called recursively
    private static void visitChildNodes(NodeList nList)
    {
        for (int temp = 0; temp < nList.getLength(); temp++)
        {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                System.out.println("Country = " + node.getTextContent());
                //Check all attributes
                if (node.hasAttributes()) {
                    // get attributes names and values
                    NamedNodeMap nodeMap = node.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++)
                    {
                        Node tempNode = nodeMap.item(i);
                        System.out.println("Attr name : " + tempNode.getNodeName()+ "; Value = " + tempNode.getNodeValue());
                    }
                    if (node.hasChildNodes()) {
                        //We got more childs; Let's visit them as well
                        visitChildNodes(node.getChildNodes());
                    }
                }
            }
        }
    }

}
