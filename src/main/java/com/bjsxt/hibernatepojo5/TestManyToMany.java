package com.bjsxt.hibernatepojo5;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestManyToMany {

//添加数据  对两张表	
	@Test
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		 Session  session=sf.openSession();
		 Transaction tr=session.beginTransaction();
		
//		添加员工
		Emp e1=new Emp();
		e1.setEname("工程师3");
		session.flush();
		Project1 p1=new Project1();
		p1.setPname("小米3");	
		session.save(e1);		
		session.save(p1);		
		tr.commit();
		session.close();
	
	}
//建立员工和项目的关系   前提是  关系维护  需要在本地当前环境
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		 Session  session=sf.openSession();
		 Transaction tr=session.beginTransaction();
		 Project1 p1=(Project1) session.get(Project1.class, 3);
		 Emp e1=new Emp();
		 e1.setEid(1);
		 p1.getEmps().add(e1);		 
		 //不需要保存  
		 tr.commit();
		 session.close();
		
		
	}

//  添加  项目、员工 还有之间的关系  这里需要设置级联保存
	
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		 Session  session=sf.openSession();
		 Transaction tr=session.beginTransaction();
		 Project1 p1=new Project1();
		 p1.setPname("微软");
		 Emp e1=new Emp();
		 e1.setEname("比尔");
		 
//		 通过 project1保存关系
		 p1.getEmps().add(e1);
//		 通过 Emp 维护之间的关系
//		这里要求需要级联保存、控制权在 Emp中 
		 Emp e2=new Emp();
		 e2.setEname("小张");
		 Project1 p2=new Project1();
		 p2.setPname("windows");
		 
		 e2.getPros().add(p2);
		 session.save(e2);
//		 session.save(p1);
		 
		 tr.commit();
		 session.close();
		
		
	}

//对象的关联加载
	
	@Test
	public void Test4(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		 Session  session=sf.openSession();
		 Transaction tr=session.beginTransaction();
//		通过工程查询所有的数据
		 List<Project1> list=session.createCriteria(Project1.class).list();
		 for (Project1 p : list) {
			 System.out.println(p.getPname());
			Set<Emp> set=p.getEmps();
//			输出每一工程下的emp信息
			for (Emp emp : set) {
				System.out.println(emp.getEname()+"=="+emp.getEid());
			}		 
		}		 
		 tr.commit();
		 session.close();		
	}
//员工的调动
	
	@Test
	public void Test5(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		 获得员工的信息
		Emp e1=(Emp) session.get(Emp.class, 1);
//		获得员工的所有的项目信息
		Set<Project1> set=e1.getPros();
		
//		获得项目标号为1
		Project1 p1=(Project1) session.get(Project1.class, 3);
		set.remove(p1);
		
//		获得羡慕编号为4的项目
		Project1 p2=(Project1) session.get(Project1.class, 4);
		set.add(p2);
 		 
		
		
		tr.commit();
		session.close();		
	}
	
//	删除信息
	
	@Test
	public void Test6(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		删除的时候不能有级联删除  属性 否则数据会全部级联的删除
//		级联删除  只会删除指定的表中的数据、和 中间表的关系记录、级联对象不会删除。
		Emp e1=(Emp) session.get(Emp.class, 6);
		session.delete(e1);
		tr.commit();
		session.close();
		
	}
}
