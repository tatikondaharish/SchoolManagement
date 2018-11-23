package com.harish.projA.processors;

import java.util.HashMap;
import java.util.List;

import com.harish.projA.common.ErrorCodes;
import com.harish.projA.daos.Course;
import com.harish.projA.exceptions.CourseExceptions;

public class CourseProcessor {
	String[] columnNames;
	private HashMap<String, Integer> columnMap = new HashMap<String, Integer>();

	public CourseProcessor(String[] columnNames) {
		this.columnNames = columnNames;

	}

	public void courseValidator() {
			if (!(columnMap.containsKey("id") && columnMap.containsKey("regNo") && columnMap.containsKey("name")))
				throw new IllegalArgumentException();
		
	}

	public void mapColumnNames() throws Exception {
			for (int i = 0; i < columnNames.length; i++) {
				this.columnMap.put(columnNames[i], i);
			}
	}

	public void process(Course CourseObject) throws CourseExceptions {
		Course.addCourseData(CourseObject);
	}

	public CourseProcessor() {

	}

	public void process(String[] records) throws Exception {
		this.mapColumnNames();
		this.courseValidator();
		int regColumnNo = columnMap.get("regNo");
		int idColumnNo = columnMap.get("id");
		int nameColumnNo = columnMap.get("name");
		
		try {

			for (String rcrds : records) {
				String[] record = rcrds.split(",");
				Course course = new Course();
				course.setCourseId(Integer.parseInt(record[idColumnNo]));
				course.setCourseReg(Integer.parseInt(record[regColumnNo]));
				course.setCourseName(record[nameColumnNo]);
				
				Course.addCourseData(course);
			}
		}
		catch(CourseExceptions e) {
			throw e;
		}
		catch (Exception e) {
			throw new CourseExceptions(ErrorCodes.INVALID_HEADER);
		}
	}
	
	public List<Course> getProcess(String columnName,String columnValue) throws CourseExceptions {
		try{
			if(columnName.equals("name"))
				return Course.getCourseDetails(columnValue);
		
			else if(columnName.equals("id"))
				return Course.getCourseDetails(Integer.parseInt(columnValue));
		
			else
				throw new Exception();
		}
		catch(Exception e) {
			throw new CourseExceptions(ErrorCodes.SERVICE_UNAVAILABLE);
		}
		
	}
	
	

}
