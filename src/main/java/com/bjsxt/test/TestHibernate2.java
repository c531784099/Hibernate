package com.bjsxt.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.bjsxt.hibernatepojo1.Manager;
import com.bjsxt.hibernatepojo1.Seller;

public class TestHibernate2 {
	/**
	 * 测试  对象的继承关系啊到数据库表的操作。
	 * 
	 * */
	@Test
	public void Test1(){
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		Manager m1=new Manager();
		m1.setAge(100);
		m1.setDept("管理");
	    m1.setName("小张");	  
	    session.save(m1);
	    
	    Seller s1=new Seller();
	    s1.setAge(99);
	    s1.setField("北京");
	    s1.setName("小白");
	    session.save(s1);
	    session.save(s1);

		tr.commit();
		session.close();
		
		
		
	}

}
