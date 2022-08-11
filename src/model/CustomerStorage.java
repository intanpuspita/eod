package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerStorage {
	private List<Customer> customers = new ArrayList<Customer>();
	
	public CustomerStorage() {
		
	}
	
	public int numOfCustomers() {
		return customers.size();
	}
	
	public void append(Customer customer) {
		customers.add(customer);
	}
	
	public List<Customer> getAll() {
		return customers;
	}
	
	public List<Customer> getSubList(int from, int to) {
		return customers.subList(from, to);
	}
	
	public void updateCustomer(int index, Map<String, Integer> newValues) {
		
		for(Map.Entry<String, Integer> newValue : newValues.entrySet()) {
			switch(newValue.getKey()) {
				case "avgBalanced":
					customers.get(index).setAvgBalanced(newValue.getValue());
					break;
				case "thread1":
					customers.get(index).setThread1(newValue.getValue());
					break;
				case "thread2a":
					customers.get(index).setThread2a(newValue.getValue());
					break;
				case "freeTransfer":
					customers.get(index).setFreeTransfer(newValue.getValue());
					break;
				case "thread2b":
					customers.get(index).setThread2b(newValue.getValue());
					break;
				case "additionalBalanced":
					customers.get(index).addBalanced(newValue.getValue());
					break;
				case "thread3":
					customers.get(index).setThread3(newValue.getValue());
					break;
				default:
					break;
			}
		}
		
	}
}
