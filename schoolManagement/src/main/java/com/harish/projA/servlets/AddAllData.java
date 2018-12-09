package com.harish.projA.servlets;

import java.util.List;

import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.harish.projA.daos.Klass;
import com.harish.projA.daos.Course;
import com.harish.projA.daos.School;
import com.harish.projA.daos.Student;
import com.harish.projA.daos.Teacher;
import com.harish.projA.exceptions.KlassExceptions;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.SchoolExceptions;
import com.harish.projA.exceptions.StudentExceptions;
import com.harish.projA.exceptions.TeacherExceptions;
import com.harish.projA.processors.CourseProcessor;
import com.harish.projA.processors.KlassProcessor;
import com.harish.projA.processors.SchoolProcessor;
import com.harish.projA.processors.TeacherProcessor;
import com.harish.projA.processors.StudentProcessor;
import com.harish.projA.requestResponse.MixedPojos;
import com.harish.projA.requestResponse.ReqCourse;
import com.harish.projA.requestResponse.ReqKlass;
import com.harish.projA.requestResponse.ReqSchool;
import com.harish.projA.requestResponse.ReqStudent;
import com.harish.projA.requestResponse.ReqTeacher;

@Path("alldata")
public class AddAllData {
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addAllData(MixedPojos data) {
		List<ReqSchool> schools = data.getSchools();
		List<ReqCourse> courses = data.getCourses();
		List<ReqKlass> klasses = data.getKlasses();
		List<ReqStudent> students = data.getStudents();
		List<ReqTeacher> teachers = data.getTeachers();
		try {
			for (int i = 0; i < schools.size(); i++) {
				School.addSchoolColumns(schools.get(i).getZipcode(), schools.get(i).getSchoolId(),
						schools.get(i).getSchoolName());
			}
		} catch (SchoolExceptions e) {
			 Response.status(e.getCode()).entity(e.getError()).build();
		}
		try {
			for (int i = 0; i < students.size(); i++) {
				Student.addStudent(students.get(i).getStudentReg(), students.get(i).getStudentId(),
						students.get(i).getStudentName());
			}
		} catch (StudentExceptions e) {
			 Response.status(e.getCode()).entity(e.getError()).build();
		}
		try {
			for (int i = 0; i < teachers.size(); i++) {
				Teacher.addTeacher(teachers.get(i).getTeacherReg(), teachers.get(i).getTeacherId(),
						teachers.get(i).getTeacherName());
			}

		} catch (TeacherExceptions e) {
			return Response.status(e.getCode()).entity(e.getError()).build();
		}
		try {
			for (int i = 0; i < courses.size(); i++)
				Course.addCourseColumns(courses.get(i).getCourseReg(), courses.get(i).getCourseId(),
						courses.get(i).getCourseName());
		} catch (CourseExceptions e) {
			Response.status(e.getCode()).entity(e.getError()).build();
		}
		try {
			for (int i = 0; i < klasses.size(); i++) {
				Klass.addClassColumns(klasses.get(i).getClassId(), klasses.get(i).getClassYear());
			}
		} catch (KlassExceptions e) {
			Response.status(e.getCode()).entity(e.getError()).build();
		}
			return Response.status(201).entity("Successfully inserted in DB").build();

	}
}
