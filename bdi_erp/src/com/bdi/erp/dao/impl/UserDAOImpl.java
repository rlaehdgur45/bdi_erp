package com.bdi.erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.bdi.erp.common.DBConnection;
import com.bdi.erp.dao.UserDAO;


public class UserDAOImpl implements UserDAO{

	@Override
	public boolean login(String id, String pwd) {
		Connection con = DBConnection.getCon();
		String sql ="select count(*) from user_info";
		sql += " where uiId =? and uiPwd=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pwd);
			ResultSet rs= ps.executeQuery();
			rs.next();
			int cnt = rs.getInt(1);
			
			if(cnt==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Map<String,String>> getUserList(){
		List<Map<String,String>> userList = 
				new ArrayList<Map<String,String>>();
		Connection con = null;
		try {
			con = DBConnection.getCon();
			String sql = "select * from user_info";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Map<String,String> user;
			while(rs.next()) {
				user = new HashMap<String,String>();
				user.put("uiNum",rs.getString("uiNum"));
				user.put("uiName",rs.getString("uiName"));
				user.put("uiId",rs.getString("uiId"));
				user.put("uiPwd",rs.getString("uiPwd"));
				user.put("uiEmail",rs.getString("uiEmail"));
				user.put("uiEtc",rs.getString("uiEtc"));
				user.put("credat",rs.getString("credat"));
				user.put("cretim",rs.getString("cretim"));
				user.put("creusr",rs.getString("creusr"));
				user.put("moddat",rs.getString("moddat"));
				user.put("modtim",rs.getString("modtim"));
				user.put("modusr",rs.getString("modusr"));
				user.put("diNum",rs.getString("diNum"));
				userList.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) {				
				DBConnection.closeCon();
			}
		}
		return userList;
	}
}
	