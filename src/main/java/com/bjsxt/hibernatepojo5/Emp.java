package com.bjsxt.hibernatepojo5;

import java.util.HashSet;
import java.util.Set;

public class Emp {
	private Integer eid;
	private String ename;
	private Set<Project1> pros=new HashSet<Project1>();
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public Set<Project1> getPros() {
		return pros;
	}
	public void setPros(Set<Project1> pros) {
		this.pros = pros;
	}
	

}
