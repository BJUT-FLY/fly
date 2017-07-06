package com.dao;

import java.util.List;
import java.util.Map;

public interface AgentMapper {
	int agentCount();
	List<Integer> agentsaleCount();
	List<Integer> agentbuyCount();
	
	int dayCount();
	List<String> agentName();
	
	Integer agentSale(Map<String,Object> params);
	Integer agentBuy(Map<String,Object> params);
	
	Float agentsaleMony(Map<String,Object> params);
	Float agentbuyMony(Map<String,Object> params);
	
	List<Integer> difDay(Map<String,Object> params);
	List<Map<String, Object>> agentsaleMonys(Map<String,Object> params);
	List<Map<String, Object>> agentbuyMonys(Map<String,Object> params);
	
	
	Integer agentsaleCountByDay(Map<String,Object> params);
	Integer agentbuyCountByDay(Map<String,Object> params);
	
	List<Map<String, Object>> agentsaleCounts(Map<String,Object> params);
	List<Map<String, Object>> agentbuyCounts(Map<String,Object> params);
}
