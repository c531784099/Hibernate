package com.bjsxt.hibernatepojo3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestManyToOne {
//�������һ������	
	@Test
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		���Ӳ���
		Dept dept=new Dept();
		dept.setDname("�ܼ�");
		
		session.save(dept);
		
		
		tr.commit();
		session.close();
		
	}
//	 ����Ա��
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Emp emp=new Emp();
		emp.setEname("С��");
//		����Ĳ�����Ҫ�����ݿ���в�ѯ�����ſ���ʹ�á��²��ź�Ա������ͬʱ����
//		Dept d1=(Dept) session.get(Dept.class, 1);
		Dept d1=new Dept();
//		d1.setDname("����");
//		������Ҫ�Ѵ���
		d1.setDid(3);
		
		emp.setDept(d1);
		
		session.save(emp);
		
		tr.commit();
		session.close();
		
		
	}
	
//	��ѯԱ������Ϣ
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		
//		���һ
		Emp e1=(Emp) session.get(Emp.class, 2);
//		û��ʹ�õ�  dept�ı� ���Բ���ȥ��ѯ dept��  ������
		System.out.println(e1.getEname()+"[]"+e1.getEid()+"[]");
//		ʹ�õ����ݲŻ�ȥ��ѯ���ݿ��еı�
		System.out.println(e1.getDept().getDname()+"***"+e1.getDept().getDid());
		
		tr.commit();
		session.close();
		
		
	}
	
//	Ա�����ŵĵ���
	@Test
	public void Test4(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
		Emp e1=(Emp) session.get(Emp.class, 2);
//		Ա�����ŵĵ���
		Dept d1=new Dept();
		d1.setDid(2);
		
		e1.setDept(d1);
		
		
		
		tr.commit();
		session.close();
		
	}
	
//	ɾ��Ա��
	
	@Test
	public void Test5(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		ֻ��ͨ��   id  ��ɾ��
		Emp e1=new Emp();
		e1.setEid(2);
//		e1.setEname("С��");
		
//		ɾ��Ա��
		session.delete(e1);
		
		tr.commit();
		session.close();
		
		
	}

}
