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
//		�Ự��������{�̳߳�=���ݿ����ӳ�}
		SessionFactory sf=cfg.buildSessionFactory();
//		���һ���Ự����
		Session session=sf.openSession();
//		���һ��������󲢿���  
		Transaction tr=session.beginTransaction();
		User u1=new User();
		u1.setAge1(11);
		u1.setDept1("����");
		u1.setName1("�����");
//		��������  ���ض����Ψһ��ʶ ����������
		Serializable id=session.save(u1);
		System.out.println(id);
		tr.commit();
//		�رջỰ���Ż����ӳ�
		session.close();
		
	}
//	���������ѯ
	@Test
	public void Test2(){
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");
//	�����Ự����
	SessionFactory sf=cfg.buildSessionFactory();
//	��ûỰ
	Session session=sf.openSession();
//	��������
	Transaction tr=session.beginTransaction();
	
	User u1=(User) session.get(User.class, 1);
	System.out.println(u1.getAge1()+"[]"+u1.getDept1()+"[]"+u1.getName1()+"[]"+u1.getId1());
	
	tr.commit();
	session.close();
}
//������query�ļ��ϲ�ѯ
	@Test
    public void Test3(){
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory sf=cfg.buildSessionFactory();
	Session session=sf.openSession();
    Transaction tr=session.beginTransaction();
//    �����from  User  �� User.hbm.xml���ļ���class name���Ӧ��
      List<User> list=(List<User>) session.createQuery("from User").list();
      for (int i = 0; i < list.size(); i++) {
		System.out.println(list.get(i).getAge1());
	}
    
       tr.commit();
       session.close();
}

//ʹ��criteria��������ѯ	
	@Test
	public void Test4(){
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory  sf=cfg.buildSessionFactory();
	Session session=sf.openSession();
	Transaction tr=session.beginTransaction();
//	ֱ��ʹ������ ��ѯ����
	Criteria ct=session.createCriteria(User.class);
	List<User> list=ct.list();
	for (User user : list) {
		System.out.println(user.getDept1());
	}
	
	tr.commit();
	session.close();
	
	}
//	���Է�ҳ��ѯ
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
//��������
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

//ɾ������
	
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
