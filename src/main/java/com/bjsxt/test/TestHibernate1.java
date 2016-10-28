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
//	集合映射
	@Test
	public void Test1(){
	Configuration cfg=new Configuration();
	cfg.configure("hibernate.cfg.xml");
	SessionFactory sf=cfg.buildSessionFactory();
	Session session=sf.openSession();
    Transaction tr=session.beginTransaction();
		
    Student st=new Student();
//    st.setSno1(5);//不能设置  编号、配置文件已经指明了 是本地生成的。
    
    st.setName1("zhong");
    List list=new ArrayList();
    list.add("英语");
    list.add("数学");
    st.setCname1(list);
    session.save(st);
    tr.commit();
    session.close();
	}
	
//	查询数据
	@Test
	public void Test2(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
//	    查询数据
	    Student stu=(Student) session.get(Student.class,2);
	    System.out.println(stu);
	    
	    tr.commit();
	    session.close();
		
		
	}
//	通过面向对象的方式删除数据
	@Test
	public void Test3(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
//	    查询数据
	    Student stu=(Student) session.get(Student.class,2);
	    System.out.println(stu);
//	    删除数据  主表和子表的数据同时删除
	    session.delete(stu);
	    
	    
	    tr.commit();
	    session.close();
		
	}
//数据的list更新
	@Test
	public void Test4(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
//	    查询数据
	    Student stu=(Student) session.get(Student.class,3);
         
	    stu.setName1("美国");
	    
	    List list=stu.getCname1();
	    list.add("java");
//	    执行数据的更新
	    session.update(stu);
	    
	    tr.commit();
	    session.close();
		
	}
	
//添加set集合数据	
	@Test
	public void Test5(){
		Configuration cfg=new Configuration();
		cfg.configure("hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session=sf.openSession();
	    Transaction tr=session.beginTransaction();
        Student stu=new Student();
        stu.setName1("日本");
        List list=new ArrayList();
        list.add("地理");
        Set set=new HashSet();
        set.add("跑步");
        set.add("打球");
        stu.setCname1(list);
        stu.setHobbys(set);
        
        session.save(stu);
        
	    
	    tr.commit();
	    session.close();
		
	}
//	删除数据 所有的数据删除  可以直接删除子表的数据 、
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
//测试map数据的crud
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
	    
//	    关联数据
	    stu.setCname1(list);
	    stu.setHobbys(set);
	    stu.setMap1(map);
//	    保存数据
	    session.save(stu);
	    
	    
	    tr.commit();
	    session.close();
	
}


}
