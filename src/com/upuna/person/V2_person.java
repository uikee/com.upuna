package com.upuna.person;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.upuna.dao.SchemaUpuna;

@Path("/v2/person")
public class V2_person {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnAddress(
				@QueryParam("address") String address)
				throws Exception {
		
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			if(address == null) {
				return Response.status(400).entity("Error: please specify address for this search").build();
			}
			
			SchemaUpuna dao = new SchemaUpuna();
			
			json = dao.queryReturnAddress(address);
			returnString = json.toString();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	@Path("/{address}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(
				@PathParam("address") String address) 
				throws Exception {
		
		String returnString = null;
		
		JSONArray json = new JSONArray();
		
		try {
			
			SchemaUpuna dao = new SchemaUpuna();
			
			json = dao.queryReturnAddress(address);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
	
	@Path("/{address}/{zip}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(
				@PathParam("address") String address,
				@PathParam("zip") int zip)
				throws Exception {
		
		String returnString = null;
		
		JSONArray json = new JSONArray();
		
		try {
			
			SchemaUpuna dao = new SchemaUpuna();
			
			json = dao.queryReturnAddresszip(address, zip);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}

}
