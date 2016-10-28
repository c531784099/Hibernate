package com.bjsxt.hibernate2;

import java.util.Set;

public class School {
	private Integer no;
	private String name;
	private Set<User> set;
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<User> getSet() {
		return set;
	}
	public void setSet(Set<User> set) {
		this.set = set;
	}
	

}
