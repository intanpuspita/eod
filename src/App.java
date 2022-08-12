import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.Customer;
import model.CustomerStorage;
import thread.AdditionalBalanced;
import thread.CalculateAvgBalanced;
import thread.CalculateFreeTransferLessThan150;
import thread.CalculateFreeTransferMoreThan150;

public class App {
	public static void main(String[] args) {
		try {
			CustomerStorage customerStorage = new CustomerStorage();

			BufferedReader br = new BufferedReader(new FileReader("upload/Before Eod.csv"));
			String line = br.readLine();
			
			line = br.readLine();
			int i = 0;
			while (line != null) {
				String[] data = line.split(";");
				
				Customer customer = new Customer();
				customer.setSeqNo(i);
				customer.setId(Integer.parseInt(data[0]));
				customer.setName(data[1]);
				customer.setAge(Integer.parseInt(data[2]));
				customer.setBalanced(Integer.parseInt(data[3]));
				customer.setPrevBalanced(Integer.parseInt(data[4]));
				customer.setAvgBalanced(Integer.parseInt(data[5]));
				customer.setFreeTransfer(Integer.parseInt(data[6]));
				customerStorage.append(customer);
				
				line = br.readLine();
				i++;
			}
			br.close();
			
			processData(customerStorage);
			
        }
        catch(Exception e){
        	System.out.println("Error in main: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
	public static void processData(CustomerStorage customerStorage) {
		try {
			int numOfThreads = 8;
			int numData = customerStorage.numOfCustomers();
			int batchSize = numData/8;
			int fromIndex = 0;
			int benefitQuota = 100;
			int benefitForEachUser = 10;
			
			ExecutorService executor = Executors.newFixedThreadPool(32); // 8 worker threads for each process
			
			for(int i=1; i<=numOfThreads; i++) {
				int toIndex = fromIndex + batchSize;
				
				toIndex = toIndex > numData ? numData : toIndex;
				
				List<Customer> batch = customerStorage.getSubList(fromIndex, toIndex);
				
				executor.execute(new CalculateAvgBalanced(i, batch, customerStorage));
				executor.execute(new CalculateFreeTransferLessThan150(i, batch, customerStorage));
				executor.execute(new CalculateFreeTransferMoreThan150(i, batch, customerStorage));
				executor.execute(new AdditionalBalanced(i, batch, customerStorage, benefitQuota, benefitForEachUser));
				
				fromIndex = toIndex;
			}
			
			executor.shutdown();
			
			if(executor.awaitTermination(30, TimeUnit.SECONDS))
				writeCsvResult(customerStorage);
			
		} catch(Exception e){
        	System.out.println("Error in processData: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
	public static void writeCsvResult(CustomerStorage customerStorage) {
		try {
			String path = "output/After Eod.csv";
			File file = new File(path);
			if(!file.exists())
				file.createNewFile();
			
			FileWriter writer = new FileWriter(file);
			
			List<Customer> customers = customerStorage.getAll();
			writer.write(customers.get(0).getHeader() + "\n");
			for(Customer customer : customers) {
				writer.write(customer.values(";") + "\n");
			}
			
			writer.close();
		} catch(Exception e) {
			System.out.println("Error when writing CSV: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
