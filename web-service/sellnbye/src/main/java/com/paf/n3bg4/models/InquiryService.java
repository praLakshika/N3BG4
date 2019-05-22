package com.paf.n3bg4.services;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.paf.n3bg4.dao.InquiryDao;
import com.paf.n3bg4.models.Inquiry;


@Path("/inquiry")
public class InquiryService {
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Inquiry> GetUserList() {
		InquiryDao userDao = new InquiryDao();
		try {
			ArrayList<Inquiry> userList = new ArrayList<Inquiry>();
			userList = userDao.getAllUsers();
			return userList;
		} finally {
			userDao.dispose();
		}
	}
	
	@GET
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Inquiry GetUser(@PathParam("name") String name) {
		InquiryDao userDao = new InquiryDao();
		try {
			Inquiry user = new Inquiry();
			user = userDao.getInquiry(name);
			return user;
		} finally {
			userDao.dispose();
			System.out.println("[Info][InquiryService][GetUser] - userDao disposed.");
		}
	}
	
	@DELETE
	@Path("/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean DeleteUser(@PathParam("name") String name) {
		InquiryDao userDao = new InquiryDao();
		try {
			return userDao.deleteInquiry(name);
		} finally {
			userDao.dispose();
			System.out.println("[Info][InquiryService][GetUser] - userDao disposed.");
		}
	}
	
	
	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean AddUser(Inquiry user) {
		InquiryDao userDao = new InquiryDao();
		try {
			return userDao.addInquiry(user);
		} finally {
			userDao.dispose();
		}
	}
	
	

}
