package com.harish.projA.requestResponse;

import java.util.List;

import com.harish.projA.daos.Student;
import com.harish.projA.exceptions.StudentExceptions;
import com.harish.projA.processors.StudentProcessor;

public class ReqStudent {
	private int studentId;
	private String studentName;
	private int studentReg;

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getStudentReg() {
		return studentReg;
	}

	public void setStudentReg(int studentReg) {
		this.studentReg = studentReg;
	}
}
