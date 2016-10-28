package com.bjsxt.hibernatepojo6;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
/**
 * 
 * ˫�������ϵ��Ҫ��ͬ������Ĺ���������ϵ��
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
		 ad.setAname("����");
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
//		��ѯ���� �ӱ�Ĺ�������ͬʱ��ѯ������
		Person p1=(Person) session.get(Person.class, 1);
//		Address a1=p1.getAddress();
		System.out.println(p1.getPname());
//		��ѯ�ӱ����������û��������ѯ���������ļ���û������  ������ѯ��
//		Address a2=(Address) session.get(Address.class, 2);
//		System.out.println(a2.getAname());
		tr.commit();
		session.close();
		
/***
 * session�еĻ�����һ�����汣����map�С���ѯ���ݵ�ʱ�����Ȳ�ѯmap  û��ƥ��Ļ�����ȥ���ݿ���ȥ��ѯ��ͬʱ
 * ���µ�map�С�
 * 
 * 
 * */	
	}
//ɾ������ ���е����ݶ���ɾ����ɾ��person��������ɾ����
	
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

// ɾ��address(ֻ��ɾ���ӱ��е����ݡ�)
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
