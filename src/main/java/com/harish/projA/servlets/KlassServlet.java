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
import com.harish.projA.daos.Klass;
import com.harish.projA.exceptions.CourseExceptions;
import com.harish.projA.exceptions.KlassExceptions;
import com.harish.projA.processors.CourseProcessor;
import com.harish.projA.processors.KlassProcessor;
import com.harish.projA.requestResponse.ReqKlass;
import com.harish.projA.requestResponse.ResponsePojo;

@Path("/class/")
public class KlassServlet {
	@POST
	@Path("add/")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response insertClass(String data, @HeaderParam("Headers") String header) throws Exception {
		ResponsePojo response = new ResponsePojo();
		try {
			String[] columnNames = header.split(",");
			String[] records = data.split(";");
			KlassProcessor KlassProcessor = new KlassProcessor(columnNames);
			KlassProcessor.process(records);
			response.setError("Successfully addded");
			response.setStatus(201);
			response.setOccuredIn("Class");
			return Response.status(201).entity(response).build();
		} 
		catch (KlassExceptions e) {
			e.printStackTrace();
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Class");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch(Exception e) {
			response.setError("Internal Server Error");
			response.setStatus(500);
			response.setOccuredIn("Class");
			return Response.status(500).entity(response).build();
		}
	}

	@POST
	@Path("add/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertDATA(List<ReqKlass> klasses) {
		ResponsePojo response = new ResponsePojo();
		try {
			for (int i = 0; i < klasses.size(); i++) {
				Klass.addClassColumns(klasses.get(i).getClassId(), klasses.get(i).getClassYear());
			}
			return Response.status(201).entity("Successfully Updated DataBase").build();
		} catch (KlassExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Class");
			return Response.status(e.getCode()).entity(response).build();
		} catch (Exception e) {
			response.setError("Invalid Data");
			response.setStatus(500);
			response.setOccuredIn("Class");
			return Response.status(500).entity(response).build();
		}
	}
	
	@GET
	@Path("get/{columnName}/{columnValue}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@PathParam ("columnName") String columnName,@PathParam("columnValue") String columnValue)  {
		ResponsePojo response = new ResponsePojo();
		try {
			KlassProcessor processor=new KlassProcessor();
			List<Course> klasses=processor.getProcess(columnName,columnValue);
			if(klasses.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(klasses).build();
		
		}
		catch(KlassExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Class");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch(Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("Class");
			return Response.status(414).entity(response).build();
		}
		
	}
	
	
	
	@GET
	@Path("get/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getData(@QueryParam ("year") String year) throws CourseExceptions {
		ResponsePojo response = new ResponsePojo();
		try {
			List<Course>klasses=new ArrayList<Course>();
			klasses=Klass.getKlassDetails(year);
			if(klasses.size()<1) {
				return Response.status(200).entity("No records found in db").build();
			}
			return Response.status(200).entity(klasses).build();
			
			
		}
		catch(KlassExceptions e) {
			response.setError(e.getError());
			response.setStatus(e.getCode());
			response.setOccuredIn("Class");
			return Response.status(e.getCode()).entity(response).build();
		}
		catch (Exception e) {
			response.setError("Invalid Data");
			response.setStatus(414);
			response.setOccuredIn("Class");
			return Response.status(414).entity(response).build();
		}
	}
}
