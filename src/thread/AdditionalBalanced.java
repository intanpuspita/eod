package thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;
import model.CustomerStorage;

public class AdditionalBalanced implements Runnable {
	int threadNo;
	List<Customer> customers;
	CustomerStorage customerStorage;
	int benefit;
	int quota;
	
	public AdditionalBalanced(int threadNo, List<Customer> customers, CustomerStorage customerStorage, int quota, int benefit) {
		this.threadNo = threadNo;
		this.customers = customers;
		this.customerStorage = customerStorage;
		this.benefit = benefit;
		this.quota = quota;
	}

	@Override
	public void run() {
		try {
			for(Customer c : customers) {
				Map<String, Integer> newValues = new HashMap<>();
				
				if(c.getSeqNo()+1 <= quota)
					newValues.put("additionalBalanced", benefit);
				
				newValues.put("thread3", threadNo);
				customerStorage.updateCustomer(c.getSeqNo(), newValues);
			}
		} catch(Exception e) {
			System.out.println("Error in AdditionalBalanced Thread No " + threadNo + " : " + e.getMessage());
			e.printStackTrace();
		}
		
	}
}
