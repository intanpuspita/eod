package model;


public class Customer {
	private int seqNo;
	private int id;
	private String name;
	private int age;
	private int balanced;
	private int prevBalanced;
	private int avgBalanced;
	private int freeTransfer;
	private int thread1;
	private int thread2a;
	private int thread2b;
	private int thread3;
	
	public Customer() {
		super();
	}
	
	public Customer(int seqNo, int id, String name, int age, int balanced, int prevBalanced, int avgBalanced,
			int freeTransfer, int thread1, int thread2a, int thread2b, int thread3) {
		super();
		this.seqNo = seqNo;
		this.id = id;
		this.name = name;
		this.age = age;
		this.balanced = balanced;
		this.prevBalanced = prevBalanced;
		this.avgBalanced = avgBalanced;
		this.freeTransfer = freeTransfer;
		this.thread1 = thread1;
		this.thread2a = thread2a;
		this.thread2b = thread2b;
		this.thread3 = thread3;
	}
	
	public String getHeader() {
		return "id;Nama;Age;Balanced;No 2b Thread-No;No 3 Thread-No;Previous Balanced;Average Balanced;No 1 Thread-No;Free Transfer;No 2a Thread-No";
	}
	
	public String values(String separator) {
		return this.id + separator
				+ this.name + separator
				+ this.age + separator
				+ this.balanced + separator
				+ this.thread2b + separator
				+ this.thread3 + separator
				+ this.prevBalanced + separator
				+ this.avgBalanced + separator
				+ this.thread1 + separator
				+ this.freeTransfer + separator
				+ this.thread2a;
	}
	
	public void addBalanced(int amount) {
		this.balanced = this.balanced + amount;
	}

	public int getSeqNo() {
		return seqNo;
	}
	
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public int getBalanced() {
		return balanced;
	}
	
	public void setBalanced(int balanced) {
		this.balanced = balanced;
	}
	
	public int getPrevBalanced() {
		return prevBalanced;
	}
	
	public void setPrevBalanced(int prevBalanced) {
		this.prevBalanced = prevBalanced;
	}
	
	public int getAvgBalanced() {
		return avgBalanced;
	}
	
	public void setAvgBalanced(int avgBalanced) {
		this.avgBalanced = avgBalanced;
	}
	
	public int getFreeTransfer() {
		return freeTransfer;
	}
	
	public void setFreeTransfer(int freeTransfer) {
		this.freeTransfer = freeTransfer;
	}
	
	public int getThread1() {
		return thread1;
	}
	
	public void setThread1(int thread1) {
		this.thread1 = thread1;
	}
	
	public int getThread2a() {
		return thread2a;
	}
	
	public void setThread2a(int thread2a) {
		this.thread2a = thread2a;
	}
	
	public int getThread2b() {
		return thread2b;
	}
	
	public void setThread2b(int thread2b) {
		this.thread2b = thread2b;
	}
	
	public int getThread3() {
		return thread3;
	}
	
	public void setThread3(int thread3) {
		this.thread3 = thread3;
	}
	
}
