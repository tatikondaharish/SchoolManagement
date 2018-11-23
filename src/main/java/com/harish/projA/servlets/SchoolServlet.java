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
import com.harish.projA.daos.School;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.SchoolExceptions;
import com.harish.projA.processors.CourseProcessor;
import com.harish.projA.processors.SchoolProcessor;
import com.harish.projA.requestResponse.ReqSchool;
import com.harish.projA.requestResponse.ResponsePojo;

@Path("/school/")
public class SchoolServlet {
	@POST
	@Path("add/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertSchoolData(String data, @HeaderParam("Headers") String header) throws Exception {
		ResponsePojo response = new ResponsePojo();
		try {
			String[] columnNames = header.split(",");
			String[] records = data.split(";");
			SchoolProcessor Sklprocess = new SchoolProcessor(columnNames);
			Sklprocess.process(records);
			return Response.status(201).entity("Successfully Updated DataBase").build();
		} 
		catch (SchoolExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("School");
			return Response.status(e.getErrCode().getHttpStatusCode()).entity(response).build();
		}
		catch (Exception e) {
			response.setError("Internal Server Error");
			response.setStatus(500);
			response.setOccuredIn("School");
			return Response.status(500).entity(response).build();
		}
	}

	@POST
	@Path("add/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDATA(List<ReqSchool> schools) {
		ResponsePojo response = new ResponsePojo();
		try {
			for (int i = 0; i < schools.size(); i++) {
				School.addSchoolColumns(schools.get(i).getZipcode(), schools.get(i).getSchoolId(),
						schools.get(i).getSchoolName());
			}
			return Response.status(201).entity("Successfully Updated DataBase").build();
		} catch (SchoolExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("School");
			return Response.status(e.getCode()).entity(response).build();
		} catch (Exception e) {
			response.setError("Internal Server Error");
			response.setStatus(500);
			response.setOccuredIn("School");
			return Response.status(500).entity(response).build();
		}
	}
	
	
	@GET
	@Path("get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@PathParam ("columnName") String columnName,@PathParam("columnValue") String columnValue) throws CourseExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			SchoolProcessor processor=new SchoolProcessor();
			List<School> schools=processor.getProcess(columnName,columnValue);
			if(schools.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(schools).build();
		
		}
		catch(SchoolExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("School");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch(Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("School");
			return Response.status(414).entity(response).build();
		}
		
	}
	
	@GET
	@Path("get/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@QueryParam ("name") String name,@QueryParam("zipcode") int zipcode) throws CourseExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			List<School>schools=new ArrayList<School>();
			if(name!=null) {
				schools=School.getSchoolDetails(name);
			}
			else if(zipcode!=0) {
				schools=School.getSchoolDetails(zipcode);
			}
			if(schools.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(schools).build();
			
			
		}
		catch(SchoolExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("School");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch (Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("School");
			return Response.status(414).entity(response).build();
		}
	}
}
