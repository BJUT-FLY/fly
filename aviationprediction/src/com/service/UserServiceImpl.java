package com.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserMapper;
import com.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	public int registerUser(User user){
		int result=userMapper.registerUser(user);
		return result;
				
	}


	
	public int loginUser(User user) {
		int result=userMapper.loginUser(user);
		return result;
	}
	public User userInfo(String username){
		User reuser=userMapper.userInfo(username);
		return reuser;
	}
}
