package com.bjsxt.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.bjsxt.hibernatepojo.Student;

public class TestHibernate1 {
//	����ӳ��
	@Test
	public void Test1(){
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory sf=cfg.buildSessionFactory();
	Session session=sf.openSession();
    Transaction tr=session.beginTransaction();
		
    Student st=new Student();
//    st.setSno1(5);//��������  ��š������ļ��Ѿ�ָ���� �Ǳ������ɵġ�
    
    st.setName1("zhong");
    List list=new ArrayList();
    list.add("Ӣ��");
    list.add("��ѧ");
    st.setCname1(list);
    session.save(st);
    tr.commit();
    session.close();
	}
	
//	��ѯ����
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
//	    ��ѯ����
	    Student stu=(Student) session.get(Student.class,2);
	    System.out.println(stu);
	    
	    tr.commit();
	    session.close();
		
		
	}
//	ͨ���������ķ�ʽɾ������
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
//	    ��ѯ����
	    Student stu=(Student) session.get(Student.class,2);
	    System.out.println(stu);
//	    ɾ������  ������ӱ������ͬʱɾ��
	    session.delete(stu);
	    
	    
	    tr.commit();
	    session.close();
		
	}
//���ݵ�list����
	@Test
	public void Test4(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
//	    ��ѯ����
	    Student stu=(Student) session.get(Student.class,3);
         
	    stu.setName1("����");
	    
	    List list=stu.getCname1();
	    list.add("java");
//	    ִ�����ݵĸ���
	    session.update(stu);
	    
	    tr.commit();
	    session.close();
		
	}
	
//���set��������	
	@Test
	public void Test5(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
        Student stu=new Student();
        stu.setName1("�ձ�");
        List list=new ArrayList();
        list.add("����");
        Set set=new HashSet();
        set.add("�ܲ�");
        set.add("����");
        stu.setCname1(list);
        stu.setHobbys(set);
        
        session.save(stu);
        
	    
	    tr.commit();
	    session.close();
		
	}
//	ɾ������ ���е�����ɾ��  ����ֱ��ɾ���ӱ������ ��
	@Test
	public  void Test6(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
	    
	    Student stu=new Student();
	    stu.setSno1(5);
	    
	    session.delete(stu);
	    
	    tr.commit();
	    session.close();
		
	}
//����map���ݵ�crud
    @Test
	public void Test7(){
    	Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
	    Student stu=new Student();
	    
	    stu.setName1("map");
	    
	    List list=new ArrayList();
	    list.add("list add");
	    Set set=new HashSet();
	    set.add("set add");
	    Map map=new HashMap();
	    map.put("m1", "map add");
	    
//	    ��������
	    stu.setCname1(list);
	    stu.setHobbys(set);
	    stu.setMap1(map);
//	    ��������
	    session.save(stu);
	    
	    
	    tr.commit();
	    session.close();
	
}


}
