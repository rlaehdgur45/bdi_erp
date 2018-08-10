package com.bdi.erp.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.bdi.erp.common.DBConnection;




public class DBTest {

	public static void main(String[] args) {
		ArrayList<HashMap<String,String>> userList = 
				new ArrayList<HashMap<String,String>>();
		
		
		
		Connection con = null;
		int cnt =0;
		Scanner s= new Scanner(System.in);
		try {
			con = DBConnection.getCon();
			String sql ="select * from user_info";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs= ps.executeQuery();
			
			HashMap<String,String> user=new HashMap<String,String>();
			while(rs.next()) {		
				user = new HashMap<String,String>();
				String result = rs.getString("uiNum");
				result += "," + rs.getString("uiName");
				result += "," + rs.getString("uiID");
				result += "," + rs.getString("uiPwd");
				result += "," + rs.getString("uiEmail");
				result += "," + rs.getString("uiEtc");
				result += "," + rs.getString("credat");
				result += "," + rs.getString("cretim");
				result += "," + rs.getString("creusr");
				result += "," + rs.getString("moddat");
				result += "," + rs.getString("modtim");
				result += "," + rs.getString("modusr");
				result += "," + rs.getString("diNum");
				System.out.println(result);			
				}
			
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(con!=null) {
					DBConnection.closeCon();
				}
			}System.out.println(userList);
	}
}
