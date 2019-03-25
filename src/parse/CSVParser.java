package parse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class CSVParser {
	CustomerStatementProcessor csp;
	public ArrayList<Integer> failedReference;
	public ArrayList<String> failedDescription;	
	public CSVParser() {
		csp = new CustomerStatementProcessor();
		failedReference = new ArrayList();
		failedDescription = new ArrayList();		
		parseCSV();
		this.failedReference=csp.getFailedReference();
		this.failedDescription=csp.getFailedDescription();
	}
	void parseCSV() {
		String csvFile = "res/records.csv";
		File myFile= new File(csvFile);
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(myFile));
            int count=0;
            while ((line = br.readLine()) != null) {
                String[] entries = line.split(cvsSplitBy); 
                if(count>0)
                process(entries);
                count++;
            }
            csp.failedRecord();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
	}
    public static void main(String[] args) {

    	CSVParser cp = new CSVParser();
    	
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
    
    
    

}