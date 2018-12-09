package com.harish.projA.processors;

import java.util.HashMap;
import java.util.List;

import com.harish.projA.common.ErrorCodes;
import com.harish.projA.daos.Course;
import com.harish.projA.daos.Klass;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.KlassExceptions;
import com.harish.projA.exceptions.StudentExceptions;

public class KlassProcessor {
	String[] columnNames;
	private HashMap<String, Integer> columnMap = new HashMap<String, Integer>();

	public KlassProcessor() {

	}

	public KlassProcessor(String[] columnNames) {
		this.columnNames = columnNames;

	}

	public void mapColumnNames() throws KlassExceptions {
			for (int i = 0; i < columnNames.length; i++) {
				this.columnMap.put(columnNames[i], i);
			}
	}

	public void process(Klass ClassObject) throws KlassExceptions {
		Klass.addClassData(ClassObject);
	}

	public void process(String[] records) throws Exception {
		this.mapColumnNames();
		int idColumnNo = columnMap.get("classId");
		int yearColumnNo = columnMap.get("classYear");
	
		try {

			for (String rcrds : records) {
				Klass klass = new Klass();
				String[] record = rcrds.split(",");
				klass.setClassId(Integer.parseInt(record[idColumnNo]));
				klass.setClassYear(Integer.parseInt(record[yearColumnNo]));
				
				Klass.addClassData(klass);
			}
		} 
		catch(KlassExceptions e) {
			throw e;
		}
		catch (Exception e) {
				throw new KlassExceptions(ErrorCodes.PRECONDITION_FAILED);
		}
	}
	
	
	public List<Course> getProcess(String columnName,String columnValue) throws KlassExceptions {
		try{
			if(columnName.equals("year"))
				return Course.getCourseDetails(columnValue);
			else
				throw new Exception();
		}
		catch(Exception e) {
			throw new KlassExceptions(ErrorCodes.SERVICE_UNAVAILABLE);
		}
		
	}
	

}
