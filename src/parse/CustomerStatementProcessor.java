package parse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CustomerStatementProcessor {
	ArrayList<Integer> transactionReference;
	ArrayList<String> accountNumber;
	ArrayList<Double> startBalance;
	ArrayList<Double> mutation;
	ArrayList<String> description;
	ArrayList<Double> endBalance;
	ArrayList<Integer> failedReference;
	ArrayList<String> failedDescription;
	
	
	public CustomerStatementProcessor() {
		transactionReference = new ArrayList();
		accountNumber = new ArrayList();
		startBalance = new ArrayList();
		mutation = new ArrayList();
		description = new ArrayList();
		endBalance = new ArrayList();	
		failedReference = new ArrayList();
		failedDescription = new ArrayList();
		
	}
	void addTransactionReference(int transactionReference) {
		this.transactionReference.add(transactionReference);
	}
	ArrayList<Integer> getTransactionReference() {
		return this.transactionReference;
	}
	void addAccountNumber(String accountNumber) {
		this.accountNumber.add(accountNumber);
	}
	ArrayList<String> getAccountNumber() {
		return this.accountNumber;
	}
	void addStartBalance(double startBalance) {
		this.startBalance.add(startBalance);
	}
	ArrayList<Double> getStartBalance() {
		return this.startBalance;
	}
	void addMutation(double mutation) {
		this.mutation.add(mutation);
	}
	ArrayList<Double> getMutation() {
		return this.mutation;
	}
	void addDescription(String description) {
		this.description.add(description);
	}
	ArrayList<String> getDescription() {
		return this.description;
	}	
	void addEndBalance(double endBalance) {
		this.endBalance.add(endBalance);
	}
	ArrayList<Double> getEndBalance() {
		return this.endBalance;
	}	
	ArrayList<Integer> findDuplicateTranscationReference() {
		ArrayList<Integer> dupReference= new ArrayList();
		Set temp = new HashSet<Integer>();
		for(Integer i:this.transactionReference) {
			if(!temp.add(i)) {
				dupReference.add(i);
			}
		}
		return dupReference;
	}
	ArrayList<Integer> mismatchReference() {
		ArrayList<Integer> mismatchReference= new ArrayList();
		for(int i=0; i<this.startBalance.size(); i++) {
			double temp= this.startBalance.get(i) + this.mutation.get(i);
			DecimalFormat df = new DecimalFormat("#.##");      
			temp = Double.valueOf(df.format(temp));
			if(temp!=this.endBalance.get(i)) {
				mismatchReference.add(this.transactionReference.get(i));
			}
		}
		return mismatchReference;
	}
	HashSet<Integer> failedIndex() {
		HashSet<Integer> failedIndex= new HashSet();
		for(Integer i:this.findDuplicateTranscationReference()) {
			for(int m=0;m<this.transactionReference.size();m++) {
				if(i.equals(this.transactionReference.get(m))) {
					failedIndex.add(m);
				}
			}
		}
		for(Integer i:this.mismatchReference()) {			
			failedIndex.add(this.transactionReference.indexOf(i));
		}	
		Iterator k = failedIndex.iterator();
		while(k.hasNext()) {
			int m=(int)k.next();
		}
		return failedIndex;
	}
	int failedRecord() {
		for(Integer i:this.failedIndex()) {	
			this.failedReference.add(this.transactionReference.get(i));
			this.failedDescription.add(this.description.get(i));
		}
		return this.failedReference.size();
	}	
	ArrayList<Integer> getFailedReference() {
		return this.failedReference;
	}
	ArrayList<String> getFailedDescription() {
		return this.failedDescription;
	}	
} 