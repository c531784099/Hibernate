package com.bjsxt.hibernatepojo4;

import java.util.HashSet;
import java.util.Set;

public class Classes {
	private Integer cno;
	private String cname;
	private Set<Student> set1=new HashSet<Student>();
	public Integer getCno() {
		return cno;
	}
	public void setCno(Integer cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Set<Student> getSet1() {
		return set1;
	}
	public void setSet1(Set<Student> set1) {
		this.set1 = set1;
	}
	

}
