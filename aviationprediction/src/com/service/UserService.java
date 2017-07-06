package com.service;

import java.util.List;

import com.pojo.User;

public interface UserService {
	
	public int registerUser(User user);
	public int loginUser(User user);
	public User userInfo(String username);
}
