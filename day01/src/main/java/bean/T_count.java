package bean;

public class T_count {
	/*
	 * CREATE TABLE `t_count` (
  `uname` varchar(12) NOT NULL,
  `countnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`uname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

	 */
	private String name;
	private int count;
	public T_count(String name, int count) {
		super();
		this.name = name;
		this.count = count;
	}
	public T_count() {
		super();
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
	

}
