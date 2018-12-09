package com.harish.projA.processors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.harish.projA.common.ErrorCodes;
import com.harish.projA.daos.Course;
import com.harish.projA.daos.Student;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.StudentExceptions;

public class StudentProcessor {
	static ErrorCodes status;
	private HashMap<String, Integer> columnMap = new HashMap<String, Integer>();
	private String[] columnNames;

	public StudentProcessor(String[] columnNames) {
		this.columnNames = columnNames;

	}

	public StudentProcessor() {

	}

	public void mapColumnNames() throws StudentExceptions {
	
			for (int i = 0; i < this.columnNames.length; i++) {
				this.columnMap.put(columnNames[i], i);
			}

	}

	public void process(Student StudentObject) throws StudentExceptions {
		try {
			Student.addStudent(StudentObject);
		} catch (Exception e) {
			throw new StudentExceptions(ErrorCodes.PRECONDITION_FAILED);
		}
	}

	public void process(String[] records) throws Exception {
		this.mapColumnNames();
		int regColumnNo = columnMap.get("regNo");
		int idColumnNo = columnMap.get("id");
		int nameColumnNo = columnMap.get("name");
		
		try {
			for (String rcrds : records) {
				Student student = new Student();
				String[] record = rcrds.split(",");
				student.setStudentReg(Integer.parseInt(record[regColumnNo]));
				student.setStudentId(Integer.parseInt(record[idColumnNo]));
				student.setStudentName(record[nameColumnNo]);
				Student.addStudent(student);
			}
		} catch (StudentExceptions e) {
			throw e;
		}
		catch(Exception e) {
				throw new StudentExceptions(ErrorCodes.PRECONDITION_FAILED);
		}
	}
	public List<Student> getProcess(String columnName,String columnValue) throws StudentExceptions {
		try{
			if(columnName.equals("name"))
				return Student.getStudentDetails(columnName);
		
			else if(columnName.equals("id"))
				return Student.getStudentDetails(Integer.parseInt(columnValue));
		
			else
				throw new Exception();
		}
		catch(Exception e) {
			throw new StudentExceptions(ErrorCodes.SERVICE_UNAVAILABLE);
		}
		
	}
	

}
