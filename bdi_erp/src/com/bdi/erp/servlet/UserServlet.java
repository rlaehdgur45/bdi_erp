package com.bdi.erp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bdi.erp.service.UserService;
import com.bdi.erp.service.impl.UserServiceImpl;


public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserService us = new UserServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		if(cmd.equals("login")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			boolean isLogin = us.login(id, pwd);
			if(isLogin) {
				out.println("로그인 성공!!");
			}else {
				out.println("로긴 실패했어 임마~");
				out.println("로긴 실패했어 임마~");
			}
		}else if(cmd.equals("list")) {
			/*String name = request.getParameter();
			System.out.println(name);
			List<Map<String,String>> userList = us.getUserList(param);*/
			String key = request.getParameter("key");
			String val = request.getParameter("val");
			List<Map<String,String>> userList = us.getUserList(key, val);
			request.setAttribute("userList", userList);
			RequestDispatcher rd = request.getRequestDispatcher("/views" + uri); 
			rd.forward(request, response);
		}else if(cmd.equals("join")) {
			
		}else {
			out.println("알수 없는 요청입니다!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
