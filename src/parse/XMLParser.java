package parse;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class XMLParser {

	CustomerStatementProcessor csp;
	public ArrayList<Integer> failedReference;
	public ArrayList<String> failedDescription;	
	public XMLParser() {
		csp = new CustomerStatementProcessor();
		failedReference = new ArrayList();
		failedDescription = new ArrayList();		
		parseXML();
		this.failedReference=csp.getFailedReference();
		this.failedDescription=csp.getFailedDescription();
	}
	void parseXML() {

    try {

    File fXmlFile = new File("res/records.xml");
	
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	doc.getDocumentElement().normalize();
			
	NodeList nList = doc.getElementsByTagName("record");
			
	System.out.println("----------------------------");

	for (int temp = 0; temp < nList.getLength(); temp++) {

		Node nNode = nList.item(temp);
								
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			String s[] = new String[6];
			Element eElement = (Element) nNode;

			String reference =  eElement.getAttribute("reference");
			s[0]=reference;
            String accountNumber =  eElement.getElementsByTagName("accountNumber").item(0).getTextContent();
            s[1]=accountNumber;
            String description =  eElement.getElementsByTagName("description").item(0).getTextContent();
            s[2]=description;
            String startBalance =  eElement.getElementsByTagName("startBalance").item(0).getTextContent();
            s[3]=startBalance;
            String mutation =  eElement.getElementsByTagName("mutation").item(0).getTextContent();
            s[4]=mutation;
            String endBalance =  eElement.getElementsByTagName("endBalance").item(0).getTextContent();
            s[5]=endBalance;            
            process(s);
		}
	}
	csp.failedRecord();
    } catch (Exception e) {
	e.printStackTrace();
    }
  }
  public void process(String[] entry) {
	   	Integer transactionReference = Integer.parseInt(entry[0]);
	   	String accountNumber = entry[1];
	   	String description = entry[2];
	   	Double startBalance = Double.parseDouble(entry[3]);
	   	Double mutation = Double.parseDouble(entry[4]);    	
	   	Double endBalance = Double.parseDouble(entry[5]);
	   	
	   	csp.addTransactionReference(transactionReference);
	   	csp.addAccountNumber(accountNumber);
	   	csp.addStartBalance(startBalance);
	   	csp.addEndBalance(endBalance);
	   	csp.addMutation(mutation);
	   	csp.addDescription(description);
  }
  public static void main(String[] args) {
	   	XMLParser xp = new XMLParser();
	   }  

}
