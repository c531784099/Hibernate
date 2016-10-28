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
		
//		定义hql语句 这里的Person  是类名
		
		String hql="from person";
		Query query=session.createQuery(hql);
		
		List<Person> list=query.list();
		for (Person person : list) {
			System.out.println(person.getPname());
		}
		
		tr.commit();
		session.close();
		
		
	}
	
//	测试查询   类中的属性  Hql  select
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		定义hql
		String  hql="select pid,pname from Person";
		
		Query query=session.createQuery(hql);
//		这里返回的数据结构是 对象数组
		List<Object[]> list=query.list();
		for (Object[] obj : list) {
			System.out.println(obj[0]+"==="+obj[1]);
		}
		
		
		tr.commit();
		session.close();

	}

	
//	测试  where子句
	
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		定义hql  
		String hql="select pid,pname from Person where pid>=:pid";
		Query query=session.createQuery(hql);
		
//		占位符设置条件
		query.setInteger("pid", 3);
		
		List<Object[]> list=query.list();
		
		for (Object[] obj : list) {
			System.out.println(obj[0]+"==="+obj[1]);
		}
		
		tr.commit();
		session.close();
		
		
	}
//	inner连接
	
	@Test
	public void Test4(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		定义hql语句
//		这里的p.address 代表的是  Person类关联的对象。
		String  hql="select p.pid,p.pname,p.address.aname from Person p inner join p.address where p.address.aname=:aname and p.pname=:pname";
		
		Query query=session.createQuery(hql);
		query.setString("aname", "北京");
		query.setString("pname", "chengxoa");
//		执行hql语句
		List<Object[]> list=query.list();
		for (Object[] obj : list) {
			System.out.println(obj[0]+"=="+obj[1]);
		}
		
		tr.commit();
		session.close();
		
		
	}
	
//	测试左外连接 left join
	
	@Test
	public void Test5(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		hql语句  左外连接  left join
		
		String hql="select p.pid,p.pname,p.address.aname from Person p left join p.address";
		
		Query query=session.createQuery(hql);
		
		List<Object[]> list=query.list();
		for (Object[] obj : list) {
			System.out.println(obj[0]+"=="+obj[1]+"=="+obj[2]);
		}
		
		
		
		
		
		tr.commit();
		session.close();
		
		
		
	}
	
	
//	测试  select封装为对象
	
	@Test
	public void Test6(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		在pojo类中需要有对应的构造器  同时必须有无参构造方法
		String hql="select new Person(pid,pname) from Person";
		
		Query query=session.createQuery(hql);
				
		List<Person> list=query.list();
		
		for (Person person : list) {
			System.out.println(person.getPid()+"=="+person.getPname());
		}
		
		
		tr.commit();
		session.close();
		
		
	}
	
	
//	同样可以进行  group  by{ 以什么分组} having{ 对分组的数据过滤} order by{以 什么排序、正序、倒序}
	@Test
	public void Test7(){
		
//		hql
		String hql="select count(p.pid) from Person p group by p.pname" ;
		String hql1="select count(p.pid) from Person p order by p.pid desc";
		
	}
	
//	标准的对象查询 criteria
	
	@Test
	public void Test8(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		以对象方式查询数据
		Criteria cri=session.createCriteria(Person.class);
		List<Person> list=cri.list();
		for (Person person : list) {
			System.out.println(person.getPname()+"=="+person.getPid());
		}
		
		
		tr.commit();
		session.close();
		
		
		
	}
	
	
//	Criteria 条件查询
	
	@Test
	public void Test9(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		Criteria cri=session.createCriteria(Person.class);
//		在对象方式中  添加 条件查询   
		cri.add(Restrictions.like("pname", "cheng%"));
		
		List<Person> list=cri.list();
		for (Person person : list) {
			System.out.println(person.getPname()+"=="+person.getPid());
		}
		
		tr.commit();
		session.close();
		
		
		
	}
	
	
//	多表的关联查询
	
	@Test
	public void Test10(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		多表的关联查询每一次调用方法都会  关联一个表进行联表查询  add 是添加条件
		Criteria cri=session.createCriteria(Person.class,"p").createCriteria("p.address","a").add(Restrictions.eq("a.aid",3));
		
//		cri.createCriteria("p.address","a");
//		也可以进行列表的指定返回。
		List<Person > list=cri.list();
		
		for (Person person : list) {
			System.out.println(person.getPname()+"=="+person.getPid());
		}
		
		tr.commit();
		session.close();
		
		
	}
	
//	数据的懒加载
	
	@Test
	public void Test11(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		属于立即加载
		Person p1=(Person) session.get(Person.class, 1);
//		属于延迟加载、查询缓存
		Person p2=(Person) session.load(Person.class, 1);
//		这里的两个对象是相同的
		System.out.println(p1);
		System.out.println(p2);
		
		
		tr.commit();
		session.close();
		
		
		
	}
	
 }
