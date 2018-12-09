package com.harish.projA.daos;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.harish.projA.common.ErrorCodes;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.SchoolExceptions;
import com.harish.projA.utility.PersistUtil;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;

@Entity
@Table(name = "SCHOOL")
public class School {
	static ErrorCodes status;
	private String schoolName;
	private int schoolId;
	private int zipcode;

	@Column(name = "SCHOOL_NAME")
	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SCHOOL_ID", unique = true, nullable = false)
	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "SCHOOL_ZIPCODE")
	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public static void addSchoolData(School schoolObject) throws SchoolExceptions {
		try {
			Session session = PersistUtil.getSession();
			session.beginTransaction();
			session.save(schoolObject);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			throw new SchoolExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
		}
	}

	public static String addSchoolColumns(int zip, int Id, String Name) throws SchoolExceptions {
		try {
			School schoolObj = new School();
			schoolObj.setSchoolId(Id);
			schoolObj.setSchoolName(Name);
			schoolObj.setZipcode(zip);
			School.addSchoolData(schoolObj);
			return "Inserted Successfully";
		} catch (Exception e) {
			throw new SchoolExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
		}
	}
	
	public static List<School> getSchoolDetails(int zipcode) throws SchoolExceptions{
		Session session=PersistUtil.getSession();
		try {
			System.out.println("Invoking db");
	
			session.beginTransaction();
			Query sqlQuery=session.createQuery("from School where zipcode = :reg");
			sqlQuery.setParameter("reg", zipcode);
			System.out.println("query done");
			List schoolList=sqlQuery.list();
			List<School> schools=new ArrayList<School>();
			for(int i=0;i<schoolList.size();i++){
				System.out.println("for loop");
				schools.add((School)schoolList.get(i));
			}
			System.out.println("list of size "+schools.size());
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
			return schools;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("caught exception in db");
			session.close();
			PersistUtil.removeSession();
			throw new SchoolExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
			
		}
	
	}
	
	public static List<School> getSchoolDetails(String name) throws CourseExceptions, SchoolExceptions{
		Session session=PersistUtil.getSession();
		try {
			System.out.println("Invoking db");
			
			session.beginTransaction();
			Query sqlQuery=session.createQuery("from School where schoolName = :name");
			sqlQuery.setParameter("name", name);
			System.out.println("query done");
			List schoolList=sqlQuery.list();
			List<School> schools=new ArrayList<School>();
			for(int i=0;i<schoolList.size();i++){
				System.out.println("for loop");
				schools.add((School)schoolList.get(i));
			}
			System.out.println("list of size "+schools.size());
			session.getTransaction().commit();
			session.close();
			PersistUtil.removeSession();
			return schools;
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("caught exception in db");
			session.close();
			PersistUtil.removeSession();
			throw new SchoolExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
			
		}
	
	}
	

}
