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
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.processors.CourseProcessor;
import com.harish.projA.requestResponse.ReqCourse;
import com.harish.projA.requestResponse.ResponsePojo;

@Path("/course/")
public class CourseServlet {
	@POST
	@Path("add/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertCourse(String data, @HeaderParam("Headers") String header) throws Exception {
		ResponsePojo response = new ResponsePojo();
		try {
			String[] columnNames = header.split(",");
			String[] records = data.split(";");
			CourseProcessor CourseProcessor = new CourseProcessor(columnNames);
			CourseProcessor.process(records);
			return Response.status(201).entity("Successfully Updated DataBase").build();
		}
		
		catch(CourseExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Course");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch(Exception e) {
			response.setError("Internal Server Error");
			response.setStatus(500);
			response.setOccuredIn("Course");
			return Response.status(500).entity(response).build();
		}

	}

	@POST
	@Path("add/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDATA(List<ReqCourse> courses) {
		ResponsePojo response = new ResponsePojo();
		try {
			for (int i = 0; i < courses.size(); i++)
				Course.addCourseColumns(courses.get(i).getCourseReg(), courses.get(i).getCourseId(),
						courses.get(i).getCourseName());
			return Response.status(201).entity("Successfully Updated DataBase").build();
		} catch (CourseExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Course");
			return Response.status(e.getCode()).entity(response).build();
		} catch (Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("Course");
			return Response.status(414).entity(response).build();
		}
	}
	
	@GET
	@Path("get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@PathParam ("columnName") String columnName,@PathParam("columnValue") String columnValue) throws CourseExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			CourseProcessor processor=new CourseProcessor();
			List<Course> courses=processor.getProcess(columnName,columnValue);
			if(courses.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(courses).build();
		
		}
		catch(CourseExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Course");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch(Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("Course");
			return Response.status(414).entity(response).build();
		}
		
	}
	
	@GET
	@Path("get/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@QueryParam ("name") String name,@QueryParam("id") int regNo) throws CourseExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			List<Course>courses=new ArrayList<Course>();
			if(name!=null) {
				courses=Course.getCourseDetails(name);
			}
			else if(regNo!=0) {
				courses=Course.getCourseDetails(regNo);
			}
			if(courses.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(courses).build();
			
			
		}
		catch(CourseExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Course");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch (Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("Course");
			return Response.status(414).entity(response).build();
		}
	}

}
