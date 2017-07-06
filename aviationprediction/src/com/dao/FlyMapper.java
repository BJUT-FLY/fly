package com.dao;

import java.util.List;
import java.util.Map;

public interface FlyMapper {
	
	int flyCount();
	List<Integer> flysaletotal();
	int dayCount();
	List<String> flycompanyname();
	Integer flycompanysale(Map<String,Object> params);
}
