package com.bjsxt.hibernatepojo3;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.bjsxt.hibernatepojo4.Classes;
import com.bjsxt.hibernatepojo4.Student;

public class TestOneToMany {
	// ���Ӱ༶
	@Test
	public void Test1() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		// ���Ӱ༶

		Classes cla = new Classes();

		cla.setCname("��3");

		session.save(cla);

		tr.commit();
		session.close();

	}

	// ����ѧ��

	@Test
	public void Test2() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		// ͨ��ѧ������
		Student s1 = new Student();
		s1.setSname("��2");

		 Classes cla=new Classes();
//		 cla.setCname("����");
		 cla.setCno(2);

		 s1.setClss(cla);

		session.save(s1);

		tr.commit();
		session.close();

	}

	// �༶����
	@Test
	public void Test5() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		// ͨ��ѧ������
		Student s1 = (Student) session.get(Student.class, 1);

		Classes cla = new Classes();
		cla.setCno(2);

		s1.setClss(cla);

		tr.commit();
		session.close();

	}

	    // ͨ���༶����ѧ��
	// http://bbs.csdn.net/topics/390503568 �ο���ַ
	@Test
	public void Test3() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();

		// ͨ���༶����ѧ��

		Classes cls = (Classes) session.load(Classes.class,1);
		System.out.println(cls.getCname());
		Set<Student> set = cls.getSet1();
		System.out.println(set.size());

		for (Student stu : set) {
			System.out.println(stu.getSno() + "--" + stu.getSname() + "--" + stu.getClss().getCname());
		}

		tr.commit();
		session.close();

	}

	// ͨ��ѧ����ð༶

	@Test
	public void Test6() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();

		Student stu = (Student) session.get(Student.class, 1);

		Classes cls = stu.getClss();

		System.out.println(cls.getCno() + "==" + cls.getCname());

		tr.commit();
		session.close();

	}

	// ��������    ����ǰ༶ά����ϵ  ���ϵ���Ա���  ����  �Ͳ��ܱ������֮��Ĺ�ϵ

	@Test
	public void Test7() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();

		Classes cls = new Classes();

		cls.setCname("��ѧ");

		session.save(cls);

		tr.commit();
		session.close();

	}

	// ɾ������

	@Test
	public void Test8() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();

		// Student stu=(Student) session.get(Student.class, 1);
		 Classes cls1=(Classes) session.get(Classes.class,2);
//		Classes cls = new Classes();
//		cls.setCno(2);

		// Classes cls=stu.getClss();
		// System.out.println(cls.getCno());

		session.delete(cls1);

		tr.commit();
		session.close();
	}

	// ������������

	@Test
	public void Test9() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Classes c1 = new Classes();

		c1.setCname("һ��");

		Student st1 = new Student();
		Student st2 = new Student();

		st1.setSname("��");
		st2.setSname("��");

		c1.getSet1().add(st1);
		c1.getSet1().add(st2);

		session.save(c1);

		tr.commit();
		session.close();
	}

	//    ���Ʒ�ת�Ĳ���  ������ͨ��  many�� ���������� ά����ϵ
	@Test
	public void Test10(){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Classes cl1=new Classes();
		cl1.setCname("xioaxue");
		session.save(cl1);
//		���ݸ��µ����ݿ�
		session.flush();
//		ͨ���෽��ά����ϵ
		Student s1=new Student();
		s1.setSname("chegnixngfeng");
		
		
		s1.setClss(cl1);
		
		session.save(s1);
		
		tr.commit();
		session.close();
		
		
		
	}

}
