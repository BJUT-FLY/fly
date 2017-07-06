package com.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.AgentMapper;
import com.dao.BuyMapper;
import com.dao.FlyMapper;
import com.dao.UserMapper;
import com.pojo.User;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	private FlyMapper flyMapper;
	@Autowired
	private AgentMapper agentMapper;
	@Autowired
	private BuyMapper buyMapper;
	
	@Override
	public int flyCount() {
		
		return flyMapper.flyCount();
	}
	@Override
	public int agentCount() {
		
		return agentMapper.agentCount();
	}
	@Override
	public List<Integer> flysaletotal() {
		return flyMapper.flysaletotal();
	}
	@Override
	public List<Integer> buytotal() {
		
		return buyMapper.buytotal();
	}
	@Override
	public List<Integer> agentsaleCount() {
		
		return agentMapper.agentsaleCount();
	}
	@Override
	public List<Integer> agentbuyCount() {
		// TODO Auto-generated method stub
		return agentMapper.agentbuyCount();
	}
	@Override
	public int flyDayCount() {
		// TODO Auto-generated method stub
		return flyMapper.dayCount();
	}
	@Override
	public List<String> flycompanyname() {
		// TODO Auto-generated method stub
		return flyMapper.flycompanyname();
	}
	@Override
	public int agentDayCount() {
		// TODO Auto-generated method stub
		return agentMapper.dayCount();
	}
	@Override
	public List<String> agentName() {
		// TODO Auto-generated method stub
		return agentMapper.agentName();
	}
	@Override
	public int flycompanysale(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Integer object=flyMapper.flycompanysale(params);
		int count;
		if(object==null){
			count=0;
		}else{
			count=(int) object;
		}
		return count;
	}
	@Override
	public int agentSale(Map<String, Object> params) {
		Integer object=agentMapper.agentSale(params);
		int count;
		if(object==null){
			count=0;
		}else{
			count=(int) object;
		}
		return count;
	}
	@Override
	public int agentBuy(Map<String, Object> params) {
		Integer object=agentMapper.agentBuy(params);
		int count;
		if(object==null){
			count=0;
		}else{
			count=(int) object;
		}
		return count;
	}
	@Override
	public float agentsaleMony(Map<String, Object> params) {
		Float object=agentMapper.agentsaleMony(params);
		float count;
		if(object==null){
			count=0;
		}else{
			count=(float) object;
		}
		return count;
	}
	@Override
	public float agentbuyMony(Map<String, Object> params) {
		Float object=agentMapper.agentbuyMony(params);
		float count;
		if(object==null){
			count=0;
		}else{
			count=(float) object;
		}
		return count;
	}
	@Override
	public List<Map<String, Object>> agentsaleMonys(Map<String, Object> params) {
		List<Map<String, Object>> count=agentMapper.agentsaleMonys(params);
		return count;
	}
	@Override
	public List<Map<String, Object>> agentbuyMonys(Map<String, Object> params) {
		List<Map<String, Object>> count=agentMapper.agentbuyMonys(params);

		return count;
	}
	public List<Integer> difDay(Map<String,Object> params){
		return agentMapper.difDay(params);
	}
	@Override
	public List<Map<String, Object>> agentsaleCounts(Map<String, Object> params) {
		List<Map<String, Object>> count=agentMapper.agentsaleCounts(params);

		return count;
	}
	@Override
	public List<Map<String, Object>> agentbuyCounts(Map<String, Object> params) {
		List<Map<String, Object>> count=agentMapper.agentbuyCounts(params);

		return count;
	}
	@Override
	public Integer agentsaleCountByDay(Map<String, Object> params) {
		Integer object=agentMapper.agentsaleCountByDay(params);
		int count;
		if(object==null){
			count=0;
		}else{
			count=(int) object;
		}
		return count;
	}
	@Override
	public Integer agentbuyCountByDay(Map<String, Object> params) {
		Integer object=agentMapper.agentbuyCountByDay(params);
		int count;
		if(object==null){
			count=0;
		}else{
			count=(int) object;
		}
		return count;
	}
	
	
}
