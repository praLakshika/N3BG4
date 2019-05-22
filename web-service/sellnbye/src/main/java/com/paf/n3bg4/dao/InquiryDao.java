package com.paf.n3bg4.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.paf.n3bg4.database.DbConnection;

import com.paf.n3bg4.models.Inquiry;
import com.paf.n3bg4.models.InquiryUpdateModel;




public class InquiryDao {
	
	private Connection _dbConnection;

	public InquiryDao() {
		this._dbConnection = new DbConnection().getConnection();
	}
	

	public ArrayList<Inquiry> getAllUsers() {
	ArrayList<Inquiry> userList = new ArrayList<Inquiry>();

	try {
		Statement stmt = this._dbConnection.createStatement();
		String sql;
		sql = "SELECT * FROM inquiry";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			Inquiry usr = new Inquiry();

			usr.setName(rs.getString("name"));
			usr.setContactNo(rs.getString("contactNo"));
			usr.setInquiry(rs.getString("inquiry"));
			

			userList.add(usr);
		}

	} catch (Exception ex) {
		System.out.println("[Error][InquiryDao][getAllUsers] - " + ex.getMessage());
	}
	return userList;
}

	public Inquiry getInquiry(String name) {
		Inquiry usr = new Inquiry();

		try {

			String sql;
			sql = "SELECT * FROM inquiry WHERE name = ?";

			PreparedStatement stmt = this._dbConnection.prepareStatement(sql);

			stmt.setString(1, name);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {

				usr.setName(rs.getString("name"));
				usr.setContactNo(rs.getString("contactNo"));
				usr.setInquiry(rs.getString("inquiry"));

			}

		} catch (Exception ex) {
			System.out.println("[Error][InquiryDao][getInquiry] - " + ex.getMessage());
		}

		return usr;
	}
	
	public boolean addInquiry(Inquiry user) {
		boolean isSuccess;
		try {

			String sql;
			sql = "INSERT INTO inquiry (name,contactNo,inquiry) VALUES (?,?,?)";

			PreparedStatement stmt = this._dbConnection.prepareStatement(sql);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getContactNo());
			stmt.setString(3, user.getInquiry());

			isSuccess = stmt.executeUpdate() > 0;

		} catch (Exception ex) {
			System.out.println("[Error][InquiryDao][addInquiry] - " + ex.toString());
			isSuccess = false;
		}
		return isSuccess;
	}
	
	
	public boolean updateInquiry(InquiryUpdateModel user) {
		boolean isSuccess;
		try {

			String sql;
			sql = "UPDATE inquiry SET contactNo = ? ,inquiry = ? WHERE name = ?";

			PreparedStatement stmt = this._dbConnection.prepareStatement(sql);

			stmt.setString(1, user.getName());
			stmt.setString(2, user.getContactNo());
			stmt.setString(3, user.getInquiry());

			isSuccess = stmt.executeUpdate() > 0;

		} catch (Exception ex) {
			System.out.println("[Error][InquiryDao][updateInquiry] - " + ex.toString());
			isSuccess = false;
		}
		return isSuccess;
	}
	
	public boolean deleteInquiry(String name) {
		boolean isSuccess = false;
		try {

			String sql;
			sql = "DELETE FROM inquiry WHERE name = ?";

			PreparedStatement stmt = this._dbConnection.prepareStatement(sql);

			stmt.setString(1, name);

			isSuccess = stmt.executeUpdate() > 0;

		} catch (Exception ex) {
			System.out.println("[Error][InquiryDao][addInquiry] - " + ex.toString());
			isSuccess = false;
		}
		return isSuccess;
	}
	

	public void dispose() {
		try {
			if (!this._dbConnection.isClosed()) {
				System.out.println("[Info][InquiryDao][dispose] - Closing DB connection");
				this._dbConnection.close();
			}
		} catch (Exception e) {
			System.out.println("[Error][InquiryDao][dispose] - " + e.getMessage());
		}
	};
}
