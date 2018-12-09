package com.harish.projA.servlets;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.harish.projA.daos.Course;
import com.harish.projA.daos.Student;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.SchoolExceptions;
import com.harish.projA.exceptions.StudentExceptions;
import com.harish.projA.processors.CourseProcessor;
import com.harish.projA.processors.StudentProcessor;
import com.harish.projA.requestResponse.ReqStudent;
import com.harish.projA.requestResponse.ResponsePojo;

@Path("/students/")
public class StudentServlet {
	@POST
	@Path("add/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertDATA(String data, @HeaderParam("Headers") String header) throws StudentExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			String[] columnNames = header.split(",");
			String[] records = data.split(";");
			StudentProcessor student = new StudentProcessor(columnNames);
			student.process(records);
			return Response.status(201).entity("Successfully Updated DataBase").build();
		} catch (StudentExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Student");
			return Response.status(e.getCode()).entity(response).build();
		}
		 catch (Exception e) {
				response.setError("internal server error");
				response.setStatus(500);
				response.setOccuredIn("Student");
				return Response.status(500).entity(response).build();
			}


	}

	@POST
	@Path("add/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDATA(List<ReqStudent> students) {
		ResponsePojo response = new ResponsePojo();
		try {
			for (int i = 0; i < students.size(); i++) {
				Student.addStudent(students.get(i).getStudentReg(), students.get(i).getStudentId(),
						students.get(i).getStudentName());
			}
			return Response.status(201).entity("Successfully updated").build();
		} catch (StudentExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Student");
			return Response.status(e.getCode()).entity(response).build();
		} catch (Exception e) {
			e.printStackTrace();
			response.setError("Internal Server Error");
			response.setStatus(500);
			response.setOccuredIn("Student");
			return Response.status(500).entity(response).build();
		}
	}

	
	@GET
	@Path("get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@PathParam ("columnName") String columnName,@PathParam("columnValue") String columnValue) throws StudentExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			StudentProcessor processor=new StudentProcessor();
			List<Student> students=processor.getProcess(columnName,columnValue);
			if(students.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(students).build();
		
		}
		catch(StudentExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Student");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch(Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("Student");
			return Response.status(414).entity(response).build();
		}
		
	}
	
	@GET
	@Path("get/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@QueryParam ("name") String name,@QueryParam("id") int regNo) throws StudentExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			List<Student>students=new ArrayList<Student>();
			if(name!=null) {
				students=Student.getStudentDetails(name);
			}
			else if(regNo!=0) {
				students=Student.getStudentDetails(regNo);
			}
			if(students.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(students).build();
			
			
		}
		catch(StudentExceptions e) {
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