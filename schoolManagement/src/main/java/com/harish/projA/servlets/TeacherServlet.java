package com.harish.projA.servlets;

import java.util.ArrayList;
import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.harish.projA.daos.Course;
import com.harish.projA.daos.Teacher;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.TeacherExceptions;
import com.harish.projA.processors.CourseProcessor;
import com.harish.projA.processors.TeacherProcessor;
import com.harish.projA.requestResponse.ResponsePojo;

@Path("/teacher/")
public class TeacherServlet {
	@POST
	@Path("add/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertTeacher(String data, @HeaderParam("Headers") String header) throws Exception {
		ResponsePojo response = new ResponsePojo();
		try {
			String[] columnNames = header.split(",");
			String[] records = data.split(";");
			TeacherProcessor TeacherProcess = new TeacherProcessor(columnNames);
			TeacherProcess.process(records);
			return Response.status(201).entity("Successfully Updated DataBase").build();
		} catch (TeacherExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Teacher");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch(Exception e) {
			response.setError("Internal Server Error");
			response.setStatus(500);
			response.setOccuredIn("Teacher");
			return Response.status(500).entity(response).build();
		}
	}

	@POST
	@Path("add/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDATA(List<Teacher> teachers) {
		ResponsePojo response = new ResponsePojo();
		try {
			for (int i = 0; i < teachers.size(); i++) {
				Teacher.addTeacher(teachers.get(i).getTeacherReg(), teachers.get(i).getTeacherId(),
						teachers.get(i).getTeacherName());
			}
			return Response.status(201).entity("Successfully Updated DataBase").build();
		} catch (TeacherExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Teacher");
			return Response.status(e.getCode()).entity(response).build();
		} catch (Exception e) {
			response.setError("Internal server error");
			response.setStatus(500);
			response.setOccuredIn("Teacher");
			return Response.status(500).entity(response).build();
		}
	}
	
	
	@GET
	@Path("get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@PathParam ("columnName") String columnName,@PathParam("columnValue") String columnValue) throws CourseExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			TeacherProcessor processor=new TeacherProcessor();
			List<Teacher> teachers=processor.getProcess(columnName,columnValue);
			if(teachers.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(teachers).build();
		
		}
		catch(TeacherExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Teacher");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch(Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("Teacher");
			return Response.status(414).entity(response).build();
		}
		
	}
	
	@GET
	@Path("get/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@QueryParam ("name") String name,@QueryParam("id") int regNo) throws CourseExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			List<Teacher>teachers=new ArrayList<Teacher>();
			if(name!=null) {
				teachers=Teacher.getTeacherDetails(name);
			}
			else if(regNo!=0) {
				teachers=Teacher.getTeacherDetails(regNo);
			}
			if(teachers.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(teachers).build();
			
			
		}
		catch(TeacherExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Teacher");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch (Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("Teacher");
			return Response.status(414).entity(response).build();
		}
	}
}