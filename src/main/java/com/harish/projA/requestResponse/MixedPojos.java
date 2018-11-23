package com.harish.projA.requestResponse;

import java.util.List;

import com.harish.projA.daos.Klass;
import com.harish.projA.daos.Course;
import com.harish.projA.daos.School;
import com.harish.projA.daos.Student;
import com.harish.projA.daos.Teacher;

public class MixedPojos {
	List<ReqStudent> Students;
	List<ReqKlass> Klasses;
	List<ReqTeacher> Teachers;
	List<ReqCourse> Courses;
	List<ReqSchool> Schools;

	public List<ReqStudent> getStudents() {
		return Students;
	}

	public void setStudents(List<ReqStudent> students) {
		Students = students;
	}

	public List<ReqKlass> getKlasses() {
		return Klasses;
	}

	public void setKlasses(List<ReqKlass> klasses) {
		Klasses = klasses;
	}

	public List<ReqTeacher> getTeachers() {
		return Teachers;
	}

	public void setTeachers(List<ReqTeacher> teachers) {
		Teachers = teachers;
	}

	public List<ReqCourse> getCourses() {
		return Courses;
	}

	public void setCourses(List<ReqCourse> courses) {
		Courses = courses;
	}

	public List<ReqSchool> getSchools() {
		return Schools;
	}

	public void setSchools(List<ReqSchool> schools) {
		Schools = schools;
	}

}
