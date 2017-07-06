package com.service;

import java.util.List;
import java.util.Map;

import com.pojo.User;

public interface SaleService {
	
	int flyCount();
	int agentCount();
	List<Integer> flysaletotal();
	List<Integer> buytotal();
	List<Integer> agentsaleCount();
	List<Integer> agentbuyCount();
	
	int flyDayCount();
	List<String> flycompanyname();
	
	int agentDayCount();
	List<String> agentName();
	
	
	int flycompanysale(Map<String,Object> params);
	int agentSale(Map<String,Object> params);
	int agentBuy(Map<String,Object> params);
	
	float agentsaleMony(Map<String,Object> params);
	float agentbuyMony(Map<String,Object> params);
	
	List<Integer> difDay(Map<String,Object> params);
	List<Map<String, Object>> agentsaleMonys(Map<String,Object> params);
	List<Map<String, Object>> agentbuyMonys(Map<String,Object> params);
	
	Integer agentsaleCountByDay(Map<String,Object> params);
	Integer agentbuyCountByDay(Map<String,Object> params);
	
	List<Map<String, Object>> agentsaleCounts(Map<String,Object> params);
	List<Map<String, Object>> agentbuyCounts(Map<String,Object> params);
}
