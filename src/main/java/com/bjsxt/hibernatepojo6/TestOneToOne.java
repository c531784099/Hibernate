package com.bjsxt.hibernatepojo6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
/**
 * 
 * 双向关联关系主要视同主外键的关联产生联系。
 * 
 * */
public class TestOneToOne {
//	http://yangfei520.blog.51cto.com/1041581/273249/
	@Test
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		 Person p1=new Person();
		 p1.setPname("chengxoa");
		 Address ad=new Address();
		 ad.setAname("北京");
		 p1.setAddress(ad);
//		 ad.setPerson(p1);
		
		 session.save(p1);
		 
		 
		 
		tr.commit();
		session.close();
		
		
	}
	
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		查询主表 子表的关联数据同时查询出来了
		Person p1=(Person) session.get(Person.class, 1);
//		Address a1=p1.getAddress();
		System.out.println(p1.getPname());
//		查询子表主表的数据没有立即查询。在配置文件中没有设置  级联查询。
//		Address a2=(Address) session.get(Address.class, 2);
//		System.out.println(a2.getAname());
		tr.commit();
		session.close();
		
/***
 * session中的缓存是一级缓存保存在map中、查询数据的时候首先查询map  没有匹配的话就再去数据库中去查询。同时
 * 更新到map中。
 * 
 * 
 * */	
	}
//删除数据 所有的数据都会删除（删除person主表、级联删除）
	
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		Person p1=(Person) session.get(Person.class,2);
		session.delete(p1);
		
		
		tr.commit();
		session.close();
		
		
	}

// 删除address(只会删除子表中的数据、)
	@Test
	public void Test4(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		Address a1=(Address) session.get(Address.class, 1);
		
		session.delete(a1);
		
		tr.commit();
		session.close();
		
		
	}
}
