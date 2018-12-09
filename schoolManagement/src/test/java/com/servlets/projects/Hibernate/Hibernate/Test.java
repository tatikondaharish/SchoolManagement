package com.servlets.projects.Hibernate.Hibernate;

import java.util.ArrayList;


import java.util.List;

import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.harish.projA.daos.Course;
import com.harish.projA.daos.Student;
import com.harish.projA.utility.PersistUtil;

import java.util.Iterator;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		Session session=PersistUtil.getSession();
		System.out.println("Obtained session object : "+session);
		Transaction transaction = session.getTransaction();
		transaction.begin();
		/*try {
			Query sqlQuery=session.createNativeQuery("select * from Course where COURSE_NAME = :name").addEntity(Course.class);
			sqlQuery.setParameter("name", "math");
			System.out.println("query done");
			if(null==sqlQuery.list()) {
				System.out.println("size is "+sqlQuery.list().size());
				List courseList=sqlQuery.list();
				List<Course> courses=new ArrayList<Course>();
				for(int i=0;i<courseList.size();i++){
					System.out.println("for loop");
					courses.add((Course)courseList.get(i));
				}
			
				System.out.println("list of size "+courses.size());
			}
		*/
		try {
			Query sqlQuery=session.createNativeQuery("select * from Course inner join Student on Course.courseId = Student.studentId");
			System.out.println("query done");
			if(null!=sqlQuery.list()) {
				System.out.println("size is "+sqlQuery.list().size());
				List courseList=sqlQuery.list();
				List<Course> courses=new ArrayList<Course>();
				for(int i=0;i<courseList.size();i++){
					System.out.println("for loop");
					courses.add((Course)courseList.get(i));
				}
			
				System.out.println("list of size "+courses.size());
			
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		/*	Query query=session.createQuery("update Student set studentName = :studentName"+" where studentReg = :studentNa");
			query.setParameter("studentName", "aryan");
			query.setParameter("studentNa", 123);
			int result = query.executeUpdate();
			System.out.println("Update resulted in : "+result);
			
			Query query1=session.createQuery("from Student where studentReg = :studentReg");
			query1.setParameter("studentReg",123 );
			Student student=(Student)query1.list().get(0);
			System.out.println("query update is "+student.getStudentId()+" reg no: "+student.getStudentReg()+" name is "+student.getStudentName());

			Query query2=session.createQuery("delete Student  where studentName = :studentName");
			query2.setParameter("studentName", "babbu");
			int result1=query.executeUpdate();
			System.out.println("Deleted in "+result1);
			
			
			Query query3=session.createQuery("from Student where studentName = :studentName");
			query3.setParameter("studentName","babbu");
			List list3=query3.list();
			if(!list3.isEmpty())
				System.out.println("query found");
			
			Query queryNative1=session.createNativeQuery("select * from Student where STUDENT_NAME = :studentName").addEntity(Student.class).setParameter("studentName", "babbu");
			Student listn=(Student) queryNative1.list().get(0);
			System.out.println("from db using native sql is "+listn.getStudentName());
			
			Query queryNative2=session.createNativeQuery("select * from Student where STUDENT_REG_NO= :studentReg").addEntity(Student.class).setParameter("studentReg", 123);
			List listn1=queryNative2.list();
			for(Iterator iterator=listn1.iterator();iterator.hasNext();) {
				Student student1=(Student) iterator.next();
				System.out.println("from db sql "+student1.getStudentName()+" id is "+student1.getStudentId());
			}
	*/
		transaction.commit();
		session.close();
		PersistUtil.removeSession();
		
	}
}
