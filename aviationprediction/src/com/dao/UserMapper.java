package com.dao;

import java.util.List;

import com.pojo.User;

public interface UserMapper {
	
	int registerUser(User user);
	int loginUser(User user);
	User userInfo(String username);
}
