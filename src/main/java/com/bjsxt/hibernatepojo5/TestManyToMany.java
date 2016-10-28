package com.bjsxt.hibernatepojo5;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class TestManyToMany {

//�������  �����ű�	
	@Test
	public void Test1(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		 Session  session=sf.openSession();
		 Transaction tr=session.beginTransaction();
		
//		���Ա��
		Emp e1=new Emp();
		e1.setEname("����ʦ3");
		session.flush();
		Project1 p1=new Project1();
		p1.setPname("С��3");	
		session.save(e1);		
		session.save(p1);		
		tr.commit();
		session.close();
	
	}
//����Ա������Ŀ�Ĺ�ϵ   ǰ����  ��ϵά��  ��Ҫ�ڱ��ص�ǰ����
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
		 //����Ҫ����  
		 tr.commit();
		 session.close();
		
		
	}

//  ���  ��Ŀ��Ա�� ����֮��Ĺ�ϵ  ������Ҫ���ü�������
	
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		 Session  session=sf.openSession();
		 Transaction tr=session.beginTransaction();
		 Project1 p1=new Project1();
		 p1.setPname("΢��");
		 Emp e1=new Emp();
		 e1.setEname("�ȶ�");
		 
//		 ͨ�� project1�����ϵ
		 p1.getEmps().add(e1);
//		 ͨ�� Emp ά��֮��Ĺ�ϵ
//		����Ҫ����Ҫ�������桢����Ȩ�� Emp�� 
		 Emp e2=new Emp();
		 e2.setEname("С��");
		 Project1 p2=new Project1();
		 p2.setPname("windows");
		 
		 e2.getPros().add(p2);
		 session.save(e2);
//		 session.save(p1);
		 
		 tr.commit();
		 session.close();
		
		
	}

//����Ĺ�������
	
	@Test
	public void Test4(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		 Session  session=sf.openSession();
		 Transaction tr=session.beginTransaction();
//		ͨ�����̲�ѯ���е�����
		 List<Project1> list=session.createCriteria(Project1.class).list();
		 for (Project1 p : list) {
			 System.out.println(p.getPname());
			Set<Emp> set=p.getEmps();
//			���ÿһ�����µ�emp��Ϣ
			for (Emp emp : set) {
				System.out.println(emp.getEname()+"=="+emp.getEid());
			}		 
		}		 
		 tr.commit();
		 session.close();		
	}
//Ա���ĵ���
	
	@Test
	public void Test5(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		 ���Ա������Ϣ
		Emp e1=(Emp) session.get(Emp.class, 1);
//		���Ա�������е���Ŀ��Ϣ
		Set<Project1> set=e1.getPros();
		
//		�����Ŀ���Ϊ1
		Project1 p1=(Project1) session.get(Project1.class, 3);
		set.remove(p1);
		
//		�����Ľ���Ϊ4����Ŀ
		Project1 p2=(Project1) session.get(Project1.class, 4);
		set.add(p2);
 		 
		
		
		tr.commit();
		session.close();		
	}
	
//	ɾ����Ϣ
	
	@Test
	public void Test6(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session  session=sf.openSession();
		Transaction tr=session.beginTransaction();
//		ɾ����ʱ�����м���ɾ��  ���� �������ݻ�ȫ��������ɾ��
//		����ɾ��  ֻ��ɾ��ָ���ı��е����ݡ��� �м��Ĺ�ϵ��¼���������󲻻�ɾ����
		Emp e1=(Emp) session.get(Emp.class, 6);
		session.delete(e1);
		tr.commit();
		session.close();
		
	}
}
