package com.bdi.erp.service.impl;

import java.util.Enumeration;

import org.apache.catalina.startup.UserConfig;
import org.apache.catalina.startup.UserDatabase;

import com.bdi.erp.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public boolean login(String id, String pwd) {
		if(id==null || pwd ==null) {
			return false;
		}
		if(id.equals("rlaehdgur45")) {
			if(pwd.equals("123456")) {
				return true;
			}
		}
		return false;
	}

}
