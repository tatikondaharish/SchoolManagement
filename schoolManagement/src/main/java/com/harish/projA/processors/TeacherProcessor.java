package com.harish.projA.processors;

import java.util.HashMap;
import java.util.List;

import com.harish.projA.common.ErrorCodes;
import com.harish.projA.daos.Course;
import com.harish.projA.daos.Teacher;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.TeacherExceptions;

public class TeacherProcessor {
	static ErrorCodes status;
	String[] columnNames;
	private HashMap<String, Integer> columnMap = new HashMap<String, Integer>();

	public TeacherProcessor(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public TeacherProcessor() {

	}

	public void mapColumnNames() throws TeacherExceptions {
		
			for (int i = 0; i < columnNames.length; i++) {
				this.columnMap.put(columnNames[i], i);
			}
	
	}

	public void process(Teacher TeacherObject) throws TeacherExceptions {
		try {
			Teacher.addTeacher(TeacherObject);
		} catch (Exception e) {
			throw new TeacherExceptions(ErrorCodes.PRECONDITION_FAILED);
		}
	}

	public void process(String[] records) throws Exception {
		
		this.mapColumnNames();
		int regColumnNo = columnMap.get("regNo");
		int idColumnNo = columnMap.get("id");
		int nameColumnNo = columnMap.get("name");
		try {

			for (String rcrds : records) {
				Teacher teacher = new Teacher();
				String[] record = rcrds.split(",");
				teacher.setTeacherId(Integer.parseInt(record[idColumnNo]));
				teacher.setTeacherReg(Integer.parseInt(record[regColumnNo]));
				teacher.setTeacherName(record[nameColumnNo]);
				Teacher.addTeacher(teacher);
			}
		} 
		catch(TeacherExceptions e) {
			throw e;
		}
		catch (Exception e) {
				throw new TeacherExceptions(ErrorCodes.PRECONDITION_FAILED);
		}
	}
	
	
	public List<Teacher> getProcess(String columnName,String columnValue) throws TeacherExceptions {
		try{
			if(columnName.equals("name"))
				return Teacher.getTeacherDetails(columnValue);
		
			else if(columnName.equals("id"))
				return Teacher.getTeacherDetails(Integer.parseInt(columnValue));
		
			else
				throw new Exception();
		}
		catch(Exception e) {
			throw new TeacherExceptions(ErrorCodes.SERVICE_UNAVAILABLE);
		}
		
	}
	
}
