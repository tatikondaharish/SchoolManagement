package com.harish.projA.daos;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.harish.projA.common.ErrorCodes;

import com.harish.projA.exceptions.KlassExceptions;
import com.harish.projA.utility.PersistUtil;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "CLASS")
public class Klass {
	static ErrorCodes status;
	private int classId;
	private int classYear;

	@Column(name = "CLASS_YEAR")
	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CLASS_ID")
	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public static void addClassData(Klass klassObject) throws KlassExceptions {
		try {
			Session session = PersistUtil.getSession();
			session.beginTransaction();
			session.save(klassObject);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new KlassExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
		}
	}

	public static void addClassColumns(int id, int year) throws KlassExceptions {
		try {
			Klass Klass = new Klass();
			Klass.setClassId(id);
			Klass.setClassYear(year);
			addClassData(Klass);
		} catch (Exception e) {
			throw new KlassExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
		}

	}
	
	public static List<Course> getKlassDetails(String year) throws KlassExceptions{
		Session session=PersistUtil.getSession();
		try {
			System.out.println("Invoking db");
			
			session.beginTransaction();
			Query sqlQuery=session.createQuery("from Klass where classYear = :year");
			sqlQuery.setParameter("year", year);
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
			throw new KlassExceptions(ErrorCodes.COLUMN_NOT_FOUND_IN_DB);
			
		}
	
	}

}
