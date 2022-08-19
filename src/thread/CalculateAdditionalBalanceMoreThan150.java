package thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;
import model.CustomerStorage;

public class CalculateAdditionalBalanceMoreThan150 implements Runnable {
	int threadNo;
	List<Customer> customers;
	CustomerStorage customerStorage;
	
	public CalculateAdditionalBalanceMoreThan150(int threadNo, List<Customer> customers, CustomerStorage customerStorage) {
		this.threadNo = threadNo;
		this.customers = customers;
		this.customerStorage = customerStorage;
	}

	@Override
	public void run() {
		try {
			for(Customer c : customers) {
				Map<String, Integer> newValues = new HashMap<>();
				
				if(c.getBalanced() >= 150) {
					newValues.put("additionalBalanced", 25);
				}
				
				newValues.put("thread2b", threadNo);
				customerStorage.updateCustomer(c.getSeqNo(), newValues);
			}
		} catch(Exception e) {
			System.out.println("Error in CalculateFreeTransferMoreThan150 Thread No " + threadNo + " : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
}
