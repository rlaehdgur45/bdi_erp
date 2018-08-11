package com.bdi.erp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	public List<Map<String,String>> getUserList(Map<String,String[]> param){
		List<Map<String, String>> userList = new ArrayList<Map<String,String>>();
		
		Connection con = null;
		try {
			con = DBConnection.getCon();
			String sql = "select * from user_info where 1=1 ";
			if(param!=null) {
				Iterator<String> it = param.keySet().iterator();
				while(it.hasNext()) {
					String col = it.next();
					String value = param.get(col)[0];
					if(!value.equals("")) {
						sql += " and " + col + " like ? ";
					}
				}
			}
			PreparedStatement ps = con.prepareStatement(sql);
			int cnt = 1;
			if(param!=null) {
				Iterator<String> it = param.keySet().iterator();
				while(it.hasNext()) {
					String col = it.next();
					String value = param.get(col)[0];
					if(!value.equals("")) {
						ps.setString(cnt++, "%" + value + "%");
					}
				}
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmt = rs.getMetaData();
			
			Map<String,String> user;
			while(rs.next()) {
				user = new HashMap<String,String>();
				for(int i=1;i<=rsmt.getColumnCount();i++) {
					String colNm = rsmt.getColumnLabel(i);
					user.put(colNm, rs.getString(colNm));
				}
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
	@Override
	public List<Map<String, String>> getUserList(String key, String val) {
		List<Map<String, String>> userList = new ArrayList<Map<String,String>>();
		
		Connection con = null;
		try {
			con = DBConnection.getCon();
			String sql = "select * from user_info where 1=1 ";
			if(val!=null && !val.equals("")) {
				sql += " and " + key + " like ?";
			}
			PreparedStatement ps = con.prepareStatement(sql);

			if(val!=null && !val.equals("")) {
				ps.setString(1, "%" + val + "%");
			}
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData rsmt = rs.getMetaData();
			
			Map<String,String> user;
			while(rs.next()) {
				user = new HashMap<String,String>();
				for(int i=1;i<=rsmt.getColumnCount();i++) {
					String colNm = rsmt.getColumnLabel(i);
					user.put(colNm, rs.getString(colNm));
				}
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
	