package com.bjsxt.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.bjsxt.hibernate2.School;
import com.bjsxt.hibernate2.User;

public class TestOneToMany {
/**
 * 
 * */	
//	保存  shcool数据
	@Test
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		School s1=new School();
		s1.setName("北方大学");
		session.save(s1);
		tr.commit();
		
		session.close();

	}
//保存user数据	
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		User u1=new User();
		u1.setAge(100);
		u1.setName("小哥");
		session.save(u1);
		
		
		
		tr.commit();
		session.close();
		
		
	}
//	建立关系   school和user的关系  
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		School s1=(School) session.get(School.class, 3);
		User u1=(User) session.get(User.class, 2);
		s1.getSet().add(u1);

		tr.commit();
		session.close();
		
	}
//	遍历输出  每一个school的user
	@Test
	public void Test4(){
		
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		School s1=(School) session.get(School.class, 3);
		 Set<User> set1=s1.getSet();
		 
		 for (User user : set1) {
			System.out.println(user.getName()+"[]"+user.getAge());
		}

		tr.commit();
		session.close();
		
		
	}
	
//	删除  school
	 @Test
	 public  void  Test5(){
		 Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf=cfg.buildSessionFactory();
			Session session=sf.openSession();
			Transaction tr=session.beginTransaction();
			School s1=(School) session.get(School.class, 1);
//			子表中的相关-数据   会清空
			session.delete(s1);
			
			tr.commit();
			session.close();
		 
	 }

}
