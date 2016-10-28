package com.bjsxt.test;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.bjsxt.hibernatepojo.User;

public class TestHibernate {
	
	@Test
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
//		会话工厂对象{线程池=数据库连接池}
		SessionFactory sf=cfg.buildSessionFactory();
//		获得一个会话对象
		Session session=sf.openSession();
//		获得一个事物对象并开启  
		Transaction tr=session.beginTransaction();
		User u1=new User();
		u1.setAge1(11);
		u1.setDept1("经理");
		u1.setName1("程相峰");
//		保存数据  返回对象的唯一标识 基本是主键
		Serializable id=session.save(u1);
		System.out.println(id);
		tr.commit();
//		关闭会话、放回连接池
		session.close();
		
	}
//	单个对象查询
	@Test
	public void Test2(){
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");
//	创建会话工厂
	SessionFactory sf=cfg.buildSessionFactory();
//	获得会话
	Session session=sf.openSession();
//	开启事物
	Transaction tr=session.beginTransaction();
	
	User u1=(User) session.get(User.class, 1);
	System.out.println(u1.getAge1()+"[]"+u1.getDept1()+"[]"+u1.getName1()+"[]"+u1.getId1());
	
	tr.commit();
	session.close();
}
//这里是query的集合查询
	@Test
    public void Test3(){
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory sf=cfg.buildSessionFactory();
	Session session=sf.openSession();
    Transaction tr=session.beginTransaction();
//    这里的from  User  和 User.hbm.xml的文件中class name相对应。
      List<User> list=(List<User>) session.createQuery("from User").list();
      for (int i = 0; i < list.size(); i++) {
		System.out.println(list.get(i).getAge1());
	}
    
       tr.commit();
       session.close();
}

//使用criteria集合来查询	
	@Test
	public void Test4(){
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory  sf=cfg.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tr=session.beginTransaction();
//	直接使用类来 查询数据
	Criteria ct=session.createCriteria(User.class);
	List<User> list=ct.list();
	for (User user : list) {
		System.out.println(user.getDept1());
	}
	
	tr.commit();
	session.close();
	
	}
//	测试分页查询
	@Test
	public void Test5(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory  sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		Criteria ct=session.createCriteria(User.class);
		ct.setFirstResult(0);
		ct.setMaxResults(3);
		
		List<User> list=ct.list();
		
		for (User user : list) {
			System.out.println(user.getAge1());
		}
		
		tr.commit();
		session.close();
		
		
		
	}
//更新数据
	@Test	
	public void Test6(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
		User u1=new User();
		u1.setName1("cheng");
		u1.setId1(2);
		u1.setAge1(43);
		u1.setDept1("CEO");
		session.update(u1);
		
		tr.commit();
		session.close();
		
	}

//删除数据
	
	@Test
	public void Test7(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		User u1=new User();
		u1.setId1(3);
		session.delete(u1);
		
		tr.commit();
		session.close();
		
		
	}


}
