package com.bjsxt.hibernatepojo6;

public class Person {
	private Integer pid;
	private String pname;
	private Address address;
	
	
	public Person() {
		super();
	}
	public Person(Integer pid, String pname) {
		super();
		this.pid = pid;
		this.pname = pname;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}
