package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Scanner;

public class Controller {
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private ListView listview;
    private Label myMessage;

    public void pressButtonSelect(ActionEvent event){
        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML and TXT files","*.xml","*.txt"));

        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null){
            String fileName = selectedFile.getName();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1, selectedFile.getName().length());
            if (fileExtension.equals("xml")){
                XMLOpener(selectedFile);
                //XMLParser'da sıkıntı var çözeceğiz.
            }
            else{
                txtOpener(selectedFile);//txtopener fonksiyonu buraya gelecek.
            }
            listview.getItems().add(selectedFile.getName());
        }
        else{
            System.out.println("File is not valid");
        }
    }

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
    public void txtOpener(File selectedFile){
        String fileType = null;
        int i = selectedFile.getName().lastIndexOf('.');
        if (i > 0) {
            fileType = selectedFile.getName().substring(i+1);
        }
        if(fileType.equals("txt")) {
            System.out.println("txt içinde");
            System.out.println(selectedFile.length());
            try {
                Scanner scan = new Scanner(selectedFile);
                while (scan.hasNextLine()) {

                    System.out.println(scan.nextLine());
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public void pressButtonBar(ActionEvent event){

    }
    public void pressButtonLine(ActionEvent event){

    }
    public void pressButtonStart(ActionEvent event){

    }
}
