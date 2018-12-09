package com.harish.projA.requestResponse;

import java.util.List;

import com.harish.projA.daos.Course;
import com.harish.projA.daos.School;
import com.harish.projA.exceptions.SchoolExceptions;
import com.harish.projA.processors.CourseProcessor;
import com.harish.projA.processors.SchoolProcessor;

public class ReqSchool {
	private String schoolName;
	private int schoolId;
	private int zipcode;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
}
