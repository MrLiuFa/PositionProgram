package day01;

public class CountBean {
	private String name;
	private int count;
	
	
	public CountBean() {
		super();
	}
	public CountBean(String name, int count) {
		super();
		this.name = name;
		this.count = count;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "name=" + name + ", count=" + count;
	}
	

}
