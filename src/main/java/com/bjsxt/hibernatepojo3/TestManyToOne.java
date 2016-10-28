package com.bjsxt.hibernatepojo3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestManyToOne {
//首先添加一个部门	
	@Test
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		增加部门
		Dept dept=new Dept();
		dept.setDname("总监");
		
		session.save(dept);
		
		
		tr.commit();
		session.close();
		
	}
//	 增加员工
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Emp emp=new Emp();
		emp.setEname("小五");
//		这里的部门需要从数据库表中查询出来才可以使用、新部门和员工不能同时插入
//		Dept d1=(Dept) session.get(Dept.class, 1);
		Dept d1=new Dept();
//		d1.setDname("美工");
//		部门需要已存在
		d1.setDid(3);
		
		emp.setDept(d1);
		
		session.save(emp);
		
		tr.commit();
		session.close();
		
		
	}
	
//	查询员工的信息
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		多对一
		Emp e1=(Emp) session.get(Emp.class, 2);
//		没有使用到  dept的表 所以不会去查询 dept表  懒加载
		System.out.println(e1.getEname()+"[]"+e1.getEid()+"[]");
//		使用到数据才会去查询数据库中的表
		System.out.println(e1.getDept().getDname()+"***"+e1.getDept().getDid());
		
		tr.commit();
		session.close();
		
		
	}
	
//	员工部门的调动
	@Test
	public void Test4(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Emp e1=(Emp) session.get(Emp.class, 2);
//		员工部门的调动
		Dept d1=new Dept();
		d1.setDid(2);
		
		e1.setDept(d1);
		
		
		
		tr.commit();
		session.close();
		
	}
	
//	删除员工
	
	@Test
	public void Test5(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		只能通过   id  来删除
		Emp e1=new Emp();
		e1.setEid(2);
//		e1.setEname("小四");
		
//		删除员工
		session.delete(e1);
		
		tr.commit();
		session.close();
		
		
	}

}
