package com.harish.projA.requestResponse;

import java.util.Date;
import java.util.List;

import com.harish.projA.daos.Course;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.processors.CourseProcessor;

public class ReqCourse {

	private int courseReg;
	private int courseId;
	private String courseName;

	public int getCourseReg() {
		return courseReg;
	}

	public void setCourseReg(int courseReg) {
		this.courseReg = courseReg;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

}
