package thread;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Customer;
import model.CustomerStorage;

public class CalculateAvgBalanced implements Runnable {
	int threadNo;
	List<Customer> customers;
	CustomerStorage customerStorage;
	
	public CalculateAvgBalanced(int threadNo, List<Customer> customers, CustomerStorage customerStorage) {
		this.threadNo = threadNo;
		this.customers = customers;
		this.customerStorage = customerStorage;
	}

	@Override
	public void run() {
		try {
			for(Customer c : customers) {
				int average = (c.getPrevBalanced() + c.getBalanced())/2;
				
				Map<String, Integer> newValues = new HashMap<>();
				newValues.put("avgBalanced", average);
				newValues.put("thread1", threadNo);
				customerStorage.updateCustomer(c.getSeqNo(), newValues);
			}
		} catch(Exception e) {
			System.out.println("Error in CalculateAvgBalancedThread Thread No " + threadNo + " : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
