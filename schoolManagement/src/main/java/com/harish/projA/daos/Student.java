package com.harish.projA.daos;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.harish.projA.common.ErrorCodes;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.StudentExceptions;
import com.harish.projA.utility.PersistUtil;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "STUDENT")
public class Student {
	static ErrorCodes status;
	private int studentId;
	private String studentName;
	private int studentReg;

	@Column(name = "STUDENT_REG_NO")
	public int getStudentReg() {
		return studentReg;
	}

	public void setStudentReg(int studentReg) {
		this.studentReg = studentReg;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "STUDENT_ID")
	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Column(name = "STUDENT_NAME")
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public static void addStudent(Student studentObject) throws StudentExceptions {

		try {
			Session session = PersistUtil.getSession();
			session.beginTransaction();
			session.save(studentObject);
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
		} catch (Exception e) {
			PersistUtil.removeSession();
			throw new StudentExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB) ;
		}
	}

	public static String addStudent(int regNo, int Id, String name) throws StudentExceptions {
		try {
		
			Student student = new Student();
			student.setStudentReg(regNo);
			student.setStudentName(name);
			addStudent(student);
			PersistUtil.removeSession();
			return "Insertion Successfull";
		} catch (Exception e) {
			PersistUtil.removeSession();
			throw new StudentExceptions(ErrorCodes.SERVICE_UNAVAILABLE);
		}

	}
	
	public static List<Student> getStudentDetails(int regNo) throws CourseExceptions, StudentExceptions{
		Session session=PersistUtil.getSession();
		try {
			System.out.println("Invoking db");
	
			session.beginTransaction();
			Query sqlQuery=session.createQuery("from Student where studentReg = :reg");
			sqlQuery.setParameter("reg", regNo);
			System.out.println("query done");
			List studentList=sqlQuery.list();
			List<Student> students=new ArrayList<Student>();
			for(int i=0;i<studentList.size();i++){
				System.out.println("for loop");
				students.add((Student)studentList.get(i));
			}
			System.out.println("list of size "+students.size());
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
			return students;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("caught exception in db");
			session.close();
			PersistUtil.removeSession();
			throw new StudentExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
			
		}
	
	}
	
	public static List<Student> getStudentDetails(String name) throws StudentExceptions{
		Session session=PersistUtil.getSession();
		try {
			System.out.println("Invoking db");
			
			session.beginTransaction();
			Query sqlQuery=session.createQuery("from Student where studentName = :name");
			sqlQuery.setParameter("name", name);
			System.out.println("query done");
			List studentList=sqlQuery.list();
			List<Student> students=new ArrayList<Student>();
			for(int i=0;i<studentList.size();i++){
				System.out.println("for loop");
				students.add((Student)studentList.get(i));
			}
			System.out.println("list of size "+students.size());
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
			return students;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("caught exception in db");
			session.close();
			PersistUtil.removeSession();
			throw new StudentExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
			
		}
	
	}
	

}
