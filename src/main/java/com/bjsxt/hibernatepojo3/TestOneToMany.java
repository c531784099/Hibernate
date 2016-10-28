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
	// 增加班级
	@Test
	public void Test1() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		// 增加班级

		Classes cla = new Classes();

		cla.setCname("大3");

		session.save(cla);

		tr.commit();
		session.close();

	}

	// 增加学生

	@Test
	public void Test2() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		// 通过学生保存
		Student s1 = new Student();
		s1.setSname("李2");

		 Classes cla=new Classes();
//		 cla.setCname("大三");
		 cla.setCno(2);

		 s1.setClss(cla);

		session.save(s1);

		tr.commit();
		session.close();

	}

	// 班级调动
	@Test
	public void Test5() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		// 通过学生保存
		Student s1 = (Student) session.get(Student.class, 1);

		Classes cla = new Classes();
		cla.setCno(2);

		s1.setClss(cla);

		tr.commit();
		session.close();

	}

	    // 通过班级查找学生
	// http://bbs.csdn.net/topics/390503568 参考网址
	@Test
	public void Test3() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();

		// 通过班级查找学生

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

	// 通过学生获得班级

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

	// 级联保存    如果是班级维护关系  则关系可以保存  否则  就不能保存对象之间的关系

	@Test
	public void Test7() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();

		Classes cls = new Classes();

		cls.setCname("中学");

		session.save(cls);

		tr.commit();
		session.close();

	}

	// 删除数据

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

	// 级联保存数据

	@Test
	public void Test9() {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Classes c1 = new Classes();

		c1.setCname("一班");

		Student st1 = new Student();
		Student st2 = new Student();

		st1.setSname("程");
		st2.setSname("王");

		c1.getSet1().add(st1);
		c1.getSet1().add(st2);

		session.save(c1);

		tr.commit();
		session.close();
	}

	//    控制反转的测试  这里是通过  many方 来保持数据 维护关系
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
//		数据跟新到数据库
		session.flush();
//		通过多方来维护关系
		Student s1=new Student();
		s1.setSname("chegnixngfeng");
		
		
		s1.setClss(cl1);
		
		session.save(s1);
		
		tr.commit();
		session.close();
		
		
		
	}

}
