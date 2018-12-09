package com.harish.projA.processors;

import java.util.HashMap;
import java.util.List;

import com.harish.projA.common.ErrorCodes;
import com.harish.projA.daos.Course;
import com.harish.projA.daos.School;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.SchoolExceptions;
import com.harish.projA.exceptions.TeacherExceptions;

public class SchoolProcessor {
	String[] columnNames;
	HashMap<String, Integer> columnMap = new HashMap<String, Integer>();

	public SchoolProcessor() {

	}

	public SchoolProcessor(String[] columnNames) {
		this.columnNames = columnNames;

	}

	public void mapColumnNames() throws SchoolExceptions {
	
			for (int i = 0; i < columnNames.length; i++) {
				this.columnMap.put(columnNames[i], i);
			}
	}

	public void process(School school) throws SchoolExceptions {
		School.addSchoolData(school);
	}

	public void process(String[] records) throws Exception {
		this.mapColumnNames();
		int nameColumnNo = columnMap.get("schoolName");
		int idColumnNo = columnMap.get("schoolId");
		int zipColumnNo = columnMap.get("zipcode");
	
		try {

			for (String rcrds : records) {
				School skl = new School();
				String[] record = rcrds.split(",");
				skl.setSchoolId(Integer.parseInt(record[idColumnNo]));
				skl.setSchoolName(record[nameColumnNo]);
				skl.setZipcode(Integer.parseInt(record[zipColumnNo]));
				School.addSchoolData(skl);
			}
		} 
		catch(SchoolExceptions e) {
			throw e;
		}
		catch (Exception e) {
				throw new SchoolExceptions(ErrorCodes.PRECONDITION_FAILED);
		}

	}
	
	
	public List<School> getProcess(String columnName,String columnValue) throws SchoolExceptions {
		try{
			if(columnName.equals("name"))
				return School.getSchoolDetails(columnName);
			else if(columnName.equals("zipcode"))
				return School.getSchoolDetails(Integer.parseInt(columnName));
		
			else
				throw new Exception();
		}
		catch(Exception e) {
			throw new SchoolExceptions(ErrorCodes.SERVICE_UNAVAILABLE);
		}
		
	}
	
}
