package thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;
import model.CustomerStorage;

public class CalculateFreeTransferLessThan150 implements Runnable {
	int threadNo;
	List<Customer> customers;
	CustomerStorage customerStorage;
	
	public CalculateFreeTransferLessThan150(int threadNo, List<Customer> customers, CustomerStorage customerStorage) {
		this.threadNo = threadNo;
		this.customers = customers;
		this.customerStorage = customerStorage;
	}

	@Override
	public void run() {
		try {
			for(Customer c : customers) {
				Map<String, Integer> newValues = new HashMap<>();
				
				if(c.getBalanced() >= 100 && c.getBalanced() <= 150) {
					int freeTransfer = 5;
					newValues.put("freeTransfer", freeTransfer);
				}
				
				newValues.put("thread2a", threadNo);
				customerStorage.updateCustomer(c.getSeqNo(), newValues);
			}
		} catch(Exception e) {
			System.out.println("Error in CalculateFreeTransferLessThan150 Thread No " + threadNo + " : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
