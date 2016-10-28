package com.bjsxt.hibernatepojo6;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

public class TestHQL {
	
	@Test 
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		����hql��� �����Person  ������
		
		String hql="from person";
		Query query=session.createQuery(hql);
		
		List<Person> list=query.list();
		for (Person person : list) {
			System.out.println(person.getPname());
		}
		
		tr.commit();
		session.close();
		
		
	}
	
//	���Բ�ѯ   ���е�����  Hql  select
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		����hql
		String  hql="select pid,pname from Person";
		
		Query query=session.createQuery(hql);
//		���ﷵ�ص����ݽṹ�� ��������
		List<Object[]> list=query.list();
		for (Object[] obj : list) {
			System.out.println(obj[0]+"==="+obj[1]);
		}
		
		
		tr.commit();
		session.close();

	}

	
//	����  where�Ӿ�
	
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		����hql  
		String hql="select pid,pname from Person where pid>=:pid";
		Query query=session.createQuery(hql);
		
//		ռλ����������
		query.setInteger("pid", 3);
		
		List<Object[]> list=query.list();
		
		for (Object[] obj : list) {
			System.out.println(obj[0]+"==="+obj[1]);
		}
		
		tr.commit();
		session.close();
		
		
	}
//	inner����
	
	@Test
	public void Test4(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		����hql���
//		�����p.address �������  Person������Ķ���
		String  hql="select p.pid,p.pname,p.address.aname from Person p inner join p.address where p.address.aname=:aname and p.pname=:pname";
		
		Query query=session.createQuery(hql);
		query.setString("aname", "����");
		query.setString("pname", "chengxoa");
//		ִ��hql���
		List<Object[]> list=query.list();
		for (Object[] obj : list) {
			System.out.println(obj[0]+"=="+obj[1]);
		}
		
		tr.commit();
		session.close();
		
		
	}
	
//	������������ left join
	
	@Test
	public void Test5(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		hql���  ��������  left join
		
		String hql="select p.pid,p.pname,p.address.aname from Person p left join p.address";
		
		Query query=session.createQuery(hql);
		
		List<Object[]> list=query.list();
		for (Object[] obj : list) {
			System.out.println(obj[0]+"=="+obj[1]+"=="+obj[2]);
		}
		
		
		
		
		
		tr.commit();
		session.close();
		
		
		
	}
	
	
//	����  select��װΪ����
	
	@Test
	public void Test6(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		��pojo������Ҫ�ж�Ӧ�Ĺ�����  ͬʱ�������޲ι��췽��
		String hql="select new Person(pid,pname) from Person";
		
		Query query=session.createQuery(hql);
				
		List<Person> list=query.list();
		
		for (Person person : list) {
			System.out.println(person.getPid()+"=="+person.getPname());
		}
		
		
		tr.commit();
		session.close();
		
		
	}
	
	
//	ͬ�����Խ���  group  by{ ��ʲô����} having{ �Է�������ݹ���} order by{�� ʲô�������򡢵���}
	@Test
	public void Test7(){
		
//		hql
		String hql="select count(p.pid) from Person p group by p.pname" ;
		String hql1="select count(p.pid) from Person p order by p.pid desc";
		
	}
	
//	��׼�Ķ����ѯ criteria
	
	@Test
	public void Test8(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		�Զ���ʽ��ѯ����
		Criteria cri=session.createCriteria(Person.class);
		List<Person> list=cri.list();
		for (Person person : list) {
			System.out.println(person.getPname()+"=="+person.getPid());
		}
		
		
		tr.commit();
		session.close();
		
		
		
	}
	
	
//	Criteria ������ѯ
	
	@Test
	public void Test9(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		Criteria cri=session.createCriteria(Person.class);
//		�ڶ���ʽ��  ��� ������ѯ   
		cri.add(Restrictions.like("pname", "cheng%"));
		
		List<Person> list=cri.list();
		for (Person person : list) {
			System.out.println(person.getPname()+"=="+person.getPid());
		}
		
		tr.commit();
		session.close();
		
		
		
	}
	
	
//	���Ĺ�����ѯ
	
	@Test
	public void Test10(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		���Ĺ�����ѯÿһ�ε��÷�������  ����һ������������ѯ  add ���������
		Criteria cri=session.createCriteria(Person.class,"p").createCriteria("p.address","a").add(Restrictions.eq("a.aid",3));
		
//		cri.createCriteria("p.address","a");
//		Ҳ���Խ����б��ָ�����ء�
		List<Person > list=cri.list();
		
		for (Person person : list) {
			System.out.println(person.getPname()+"=="+person.getPid());
		}
		
		tr.commit();
		session.close();
		
		
	}
	
//	���ݵ�������
	
	@Test
	public void Test11(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		������������
		Person p1=(Person) session.get(Person.class, 1);
//		�����ӳټ��ء���ѯ����
		Person p2=(Person) session.load(Person.class, 1);
//		�����������������ͬ��
		System.out.println(p1);
		System.out.println(p2);
		
		
		tr.commit();
		session.close();
		
		
		
	}
	
 }
