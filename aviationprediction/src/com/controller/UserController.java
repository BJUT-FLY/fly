package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;





import com.pojo.JsonInfo;
import com.pojo.User;
import com.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/register.action",method=RequestMethod.POST)
	public @ResponseBody JsonInfo register(@RequestBody User user){
		JsonInfo jsoninfo=new JsonInfo();
		int result=userService.registerUser(user);
		if(result>0){
			jsoninfo.setState("success");
		}else{
			jsoninfo.setState("error");
		}
		jsoninfo.setUrl("login.html");
		return jsoninfo;
	}
	@RequestMapping(value="/login.action")
	public @ResponseBody JsonInfo login(@RequestBody User user,HttpServletRequest request){
		JsonInfo jsoninfo=new JsonInfo();
		int result=userService.loginUser(user);
		if(result>0){
			jsoninfo.setState("success");
			jsoninfo.setUrl("index.html");
			HttpSession session=request.getSession();
			session.setAttribute("username",user.getUsername());
			
		}else{
			jsoninfo.setState("error");
			jsoninfo.setUrl("login.html");
		}
		
		
		return jsoninfo;
	}
	@RequestMapping(value="/userinfo.action")
	public @ResponseBody JsonInfo userInfo(HttpServletRequest request){
		JsonInfo jsoninfo=new JsonInfo();
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		User reuser=userService.userInfo(username);
		JSONObject jsonUser = JSONObject.fromObject(reuser);
		jsoninfo.setState("success");
		jsoninfo.setData(jsonUser.toString());
		return jsoninfo;
	}
}
