package com.bjsxt.hibernatepojo6;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestCache {
	
//	≤‚ ‘cache
	@Test
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
//		Transaction tr=session.beginTransaction();
		
		Query query=session.createQuery("from Person");
		query.setCacheable(true);
         List<Person> list=query.list();
        for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getPname());
		}
         session.close();
         
         Session s1=sf.openSession();
         Query query1=s1.createQuery("from Person ");
//         Person p2=(Person) s1.get(Person.class, 1);
//         System.out.println(p2.getPname());
         query1.setCacheable(true);
         
         List<Person> list1=query1.list();
//         for (Person person : list1) {
//			System.out.println(person.getPname());
//		}
         for (int i = 0; i < list1.size(); i++) {
			System.out.println(list1.get(i).getPname());
		}
         
		
		s1.close();
		
	}

}
