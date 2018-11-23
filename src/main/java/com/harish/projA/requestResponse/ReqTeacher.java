package com.harish.projA.requestResponse;

import java.util.List;

import com.harish.projA.daos.Teacher;
import com.harish.projA.exceptions.TeacherExceptions;
import com.harish.projA.processors.TeacherProcessor;

public class ReqTeacher {
	private int teacherReg;
	private int teacherId;
	private String teacherName;

	public int getTeacherReg() {
		return teacherReg;
	}

	public void setTeacherReg(int teacherReg) {
		this.teacherReg = teacherReg;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}
