package com.harish.projA.requestResponse;

import java.util.List;

import com.harish.projA.daos.Klass;
import com.harish.projA.exceptions.KlassExceptions;
import com.harish.projA.processors.KlassProcessor;

public class ReqKlass {
	private int classId;
	private int classYear;

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public int getClassYear() {
		return classYear;
	}

	public void setClassYear(int classYear) {
		this.classYear = classYear;
	}
}
