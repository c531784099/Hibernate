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
//	����  shcool����
	@Test
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		School s1=new School();
		s1.setName("������ѧ");
		session.save(s1);
		tr.commit();
		
		session.close();

	}
//����user����	
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		User u1=new User();
		u1.setAge(100);
		u1.setName("С��");
		session.save(u1);
		
		
		
		tr.commit();
		session.close();
		
		
	}
//	������ϵ   school��user�Ĺ�ϵ  
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
//	�������  ÿһ��school��user
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
	
//	ɾ��  school
	 @Test
	 public  void  Test5(){
		 Configuration cfg=new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf=cfg.buildSessionFactory();
			Session session=sf.openSession();
			Transaction tr=session.beginTransaction();
			School s1=(School) session.get(School.class, 1);
//			�ӱ��е����-����   �����
			session.delete(s1);
			
			tr.commit();
			session.close();
		 
	 }

}
