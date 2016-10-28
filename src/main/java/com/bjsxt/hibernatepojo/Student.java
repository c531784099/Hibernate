package com.bjsxt.hibernatepojo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {
	private Integer sno1;
	private String name1;
	private Integer age1;
	private List cname1;
	private Set hobbys;
	private Map map1;
	public Map getMap1() {
		return map1;
	}
	public void setMap1(Map map1) {
		this.map1 = map1;
	}
	public Set getHobbys() {
		return hobbys;
	}
	public void setHobbys(Set hobbys) {
		this.hobbys = hobbys;
	}
	public Integer getSno1() {
		return sno1;
	}
	public void setSno1(Integer sno1) {
		this.sno1 = sno1;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public Integer getAge1() {
		return age1;
	}
	public void setAge1(Integer age1) {
		this.age1 = age1;
	}
	public List getCname1() {
		return cname1;
	}
	@Override
	public String toString() {
		return "Student [sno1=" + sno1 + ", name1=" + name1 + ", age1=" + age1 + ", cname1=" + cname1 + "]";
	}
	public void setCname1(List cname1) {
		this.cname1 = cname1;
	}


}
