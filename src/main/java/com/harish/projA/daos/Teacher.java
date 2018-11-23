package com.harish.projA.daos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.harish.projA.common.ErrorCodes;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.TeacherExceptions;
import com.harish.projA.utility.PersistUtil;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "TEACHER")
public class Teacher {
	static ErrorCodes status;
	private int teacherReg;

	@Column(name = "TEACHER_REG_NO")
	public int getTeacherReg() {
		return teacherReg;
	}

	public void setTeacherReg(int teacherReg) {
		this.teacherReg = teacherReg;
	}

	private int teacherId;
	private String teacherName;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TEACHER_ID")
	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	@Column(name = "TEACHER_NAME")
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public static void addTeacher(Teacher studentObject) throws TeacherExceptions {
		try {
			Session session = PersistUtil.getSession();
			session.beginTransaction();
			session.save(studentObject);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new TeacherExceptions(ErrorCodes.SERVICE_UNAVAILABLE);
		}
	}

	public static String addTeacher(int regNo, int Id, String name) throws TeacherExceptions {
		try {
			Teacher Teacher = new Teacher();
			Teacher.setTeacherId(Id);
			Teacher.setTeacherName(name);
			Teacher.setTeacherReg(regNo);
			addTeacher(Teacher);
			return "Insertion Successfull";
		} catch (Exception e) {
			throw new TeacherExceptions(ErrorCodes.SERVICE_UNAVAILABLE);
		}

	}
	
	public static List<Teacher> getTeacherDetails(String name) throws TeacherExceptions{
		Session session=PersistUtil.getSession();
		try {
			System.out.println("Invoking db");
	
			session.beginTransaction();
			Query sqlQuery=session.createQuery("from Teacher where teacherName = :name");
			sqlQuery.setParameter("name", name);
			System.out.println("query done");
			List teacherList=sqlQuery.list();
			List<Teacher> teachers=new ArrayList<Teacher>();
			for(int i=0;i<teacherList.size();i++){
				System.out.println("for loop");
				teachers.add((Teacher)teacherList.get(i));
			}
			System.out.println("list of size "+teachers.size());
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
			return teachers;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("caught exception in db");
			session.close();
			PersistUtil.removeSession();
			throw new TeacherExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
			
		}
	
	}
	
	public static List<Teacher> getTeacherDetails(int regNo) throws TeacherExceptions{
		Session session=PersistUtil.getSession();
		try {
			System.out.println("Invoking db");
			
			session.beginTransaction();
			Query sqlQuery=session.createQuery("from Teacher where teacherReg = :reg");
			sqlQuery.setParameter("reg",regNo );
			System.out.println("query done");
			List teacherList=sqlQuery.list();
			List<Teacher> teachers=new ArrayList<Teacher>();
			for(int i=0;i<teacherList.size();i++){
				System.out.println("for loop");
				teachers.add((Teacher)teacherList.get(i));
			}
			System.out.println("list of size "+teacherList.size());
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
			return teachers;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("caught exception in db");
			session.close();
			PersistUtil.removeSession();
			throw new TeacherExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
			
		}
	
	}
	
	
	
}
