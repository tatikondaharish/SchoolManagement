package com.harish.projA.daos;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.harish.projA.common.ErrorCodes;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.utility.PersistUtil;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "COURSE")
public class Course {
	private int courseReg;
	private int courseId;
	private String courseName;

	@Column(name = "COURSE_REG_NO")
	public int getCourseReg() {
		return courseReg;
	}

	public void setCourseReg(int courseReg) {
		this.courseReg = courseReg;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "COURSE_ID")
	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@Column(name = "COURSE_NAME")
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public static void addCourseData(Course courseObject) throws CourseExceptions {
		try {
			Session session = PersistUtil.getSession();
			session.beginTransaction();
			session.save(courseObject);
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
		} catch (Exception e) {
			System.out.println("caught after seesion");
			PersistUtil.removeSession();
			e.printStackTrace();
			throw new CourseExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
		}
	}

	public static void addCourseColumns(int RegNo, int Id, String Name) throws CourseExceptions {
		try {
			Course courseObj = new Course();
			courseObj.setCourseId(Id);
			courseObj.setCourseName(Name);
			courseObj.setCourseReg(RegNo);
			Course.addCourseData(courseObj);
		} catch (Exception e) {
			System.out.println("caught before seesion");
			throw new CourseExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
		}
	}
	
	public static List<Course> getCourseDetails(int regNo) throws CourseExceptions{
		Session session=PersistUtil.getSession();
		try {
			System.out.println("Invoking db");
	
			session.beginTransaction();
			Query sqlQuery=session.createQuery("from Course where courseReg = :reg");
			sqlQuery.setParameter("reg", regNo);
			System.out.println("query done");
			List courseList=sqlQuery.list();
			List<Course> courses=new ArrayList<Course>();
			for(int i=0;i<courseList.size();i++){
				System.out.println("for loop");
				courses.add((Course)courseList.get(i));
			}
			System.out.println("list of size "+courses.size());
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
			return courses;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("caught exception in db");
			session.close();
			PersistUtil.removeSession();
			throw new CourseExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
			
		}
	
	}
	
	public static List<Course> getCourseDetails(String name) throws CourseExceptions{
		Session session=PersistUtil.getSession();
		try {
			System.out.println("Invoking db");
			
			session.beginTransaction();
			Query sqlQuery=session.createQuery("from Course where courseName = :name");
			sqlQuery.setParameter("name", name);
			System.out.println("query done");
			List courseList=sqlQuery.list();
			List<Course> courses=new ArrayList<Course>();
			for(int i=0;i<courseList.size();i++){
				System.out.println("for loop");
				courses.add((Course)courseList.get(i));
			}
			System.out.println("list of size "+courses.size());
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
			return courses;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("caught exception in db");
			session.close();
			PersistUtil.removeSession();
			throw new CourseExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
			
		}
	
	}
	
}
