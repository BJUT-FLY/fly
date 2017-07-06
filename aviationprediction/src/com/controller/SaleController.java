package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
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
import com.service.SaleService;
import com.service.UserService;

@Controller
public class SaleController {
	@Autowired
	private SaleService saleService;
	
	@RequestMapping(value="/index.action",method=RequestMethod.POST)
	public @ResponseBody JsonInfo indexinfo(){
		int flycount=saleService.flyCount();
		int agentcount=saleService.agentCount();
		
		
		List<Integer> flysaletotal=saleService.flysaletotal();
		JSONArray flysaleArray=JSONArray.fromObject(flysaletotal);
		
		List<Integer> buytotal=saleService.buytotal();
		JSONArray buytotalArray=JSONArray.fromObject(buytotal);
		
		List<Integer> agentsalecount=saleService.agentsaleCount();
		JSONArray agentsalecountArray=JSONArray.fromObject(agentsalecount);
		
		List<Integer> agentbuycount=saleService.agentbuyCount();
		JSONArray agentbuycountArray=JSONArray.fromObject(agentbuycount);
		
		String data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"flysaletotal\":"+flysaleArray+",\"buytotal\":"+buytotalArray+",\"agentsalecount\":"+agentsalecountArray+",\"agentbuycount\":"+agentbuycountArray+"}";
		JsonInfo jsoninfo=new JsonInfo();
		jsoninfo.setState("success");
		jsoninfo.setData(data);
		return jsoninfo;
	}
	
	@RequestMapping(value="/flysale.action",method=RequestMethod.POST)
	public @ResponseBody JsonInfo flysale(@RequestBody String content){
		ArrayList flycompany=new ArrayList();
		JSONArray flycompanyArray=null;
		if(!content.equals("")){
				JSONObject object = JSONObject.fromObject(content);
				int startweek=Integer.parseInt(object.getString("startweek"));
				int endweek=Integer.parseInt(object.getString("endweek"));
				JSONArray flyList=object.getJSONArray("fly");
				ArrayList<String> arrayList = new ArrayList<String>();
				for (int i = 0; i < flyList.size(); i++) {  
		            arrayList.add(flyList.getString(i));  
		        }  
				for(int i=startweek;i<=endweek;i++){
					Map<String,Object> params=new HashMap<String, Object>();
					List<Integer> dataarrayIntegers = null;
					int startday=(i-1)*7;
					int endday=i*7;	
					params.put("startday", startday);
					params.put("endweek", endday);
					dataarrayIntegers=new ArrayList<Integer>();
					for(String string:arrayList){
						params.put("flyList",string);
						dataarrayIntegers.add(saleService.flycompanysale(params));
					}
					
					
					flycompany.add(dataarrayIntegers);
				}
				flycompanyArray=JSONArray.fromObject(flycompany);
			
		}
		
		
		int flycount=saleService.flyCount();
		int agentcount=saleService.agentCount();
		
		int week=(int) Math.ceil(saleService.flyDayCount()/7);
		

		List<String> flycompanyname=saleService.flycompanyname();
		JSONArray flycompanynameArray=JSONArray.fromObject(flycompanyname);
		
		
		
		
		String data="";
		if(!content.equals("")){
			data+="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"week\":"+week+",\"flycompanyname\":"+flycompanynameArray+",\"flycompany\":"+flycompanyArray+"}";
		}else{
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"week\":"+week+",\"flycompanyname\":"+flycompanynameArray+"}";
		}
		JsonInfo jsoninfo=new JsonInfo();
		jsoninfo.setState("success");
		jsoninfo.setData(data);
		return jsoninfo;
	}
	
	@RequestMapping(value="/agentsale.action",method=RequestMethod.POST)
	public @ResponseBody JsonInfo agentsale(@RequestBody String content){
		ArrayList agentsaleandbuy=new ArrayList();
		JSONArray agentArray=null;
		if(!content.equals("")){
			JSONObject object = JSONObject.fromObject(content);
			int startweek=Integer.parseInt(object.getString("startweek"));
			int endweek=Integer.parseInt(object.getString("endweek"));
			String agent=object.getString("agent");
			for(int i=startweek;i<=endweek;i++){
				Map<String,Object> params=new HashMap<String, Object>();
				List<Integer> saleandbuy = new ArrayList<Integer>();
				int startday=(i-1)*7;
				int endday=i*7;	
				params.put("startday", startday);
				params.put("endweek", endday);
				params.put("agent",agent);
				int buy=saleService.agentBuy(params);
				int sale=saleService.agentSale(params);
				saleandbuy.add(sale);
				saleandbuy.add(buy);
				agentsaleandbuy.add(saleandbuy);
			}
			agentArray=JSONArray.fromObject(agentsaleandbuy);
		}
		
		int flycount=saleService.flyCount();
		int agentcount=saleService.agentCount();
		
		int week=(int) Math.ceil(saleService.agentDayCount()/7);
		

		List<String> agentname=saleService.agentName();
		JSONArray agentnameArray=JSONArray.fromObject(agentname);
		
		String data="";
		if(!content.equals("")){
			data+="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"week\":"+week+",\"agentname\":"+agentnameArray+",\"agentarray\":"+agentArray+"}";
			System.out.println("");
		}else{
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"week\":"+week+",\"agentname\":"+agentnameArray+"}";
		}
		
		JsonInfo jsoninfo=new JsonInfo();
		jsoninfo.setState("success");
		jsoninfo.setData(data);
		return jsoninfo;
	}
	@RequestMapping(value="/zhouzhuan.action",method=RequestMethod.POST)
	public @ResponseBody JsonInfo zhouzhuan(@RequestBody String content){
		int flycount=saleService.flyCount();
		int agentcount=saleService.agentCount();
		
		int day=(int) saleService.agentDayCount();
		

		List<String> agentname=saleService.agentName();
		JSONArray agentnameArray=JSONArray.fromObject(agentname);
		
		ArrayList agentsaleandbuy=new ArrayList();
		JSONArray agentArray=null;
		if(!content.equals("")){
			JSONObject object = JSONObject.fromObject(content);
			int curday=Integer.parseInt(object.getString("day"));
			
			JSONArray agentList=object.getJSONArray("agent");
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int i = 0; i < agentList.size(); i++) {  
	            arrayList.add(agentList.getString(i));  
	        }  
			for(String string:arrayList){
				Map<String,Object> params=new HashMap<String, Object>();
				List<Float> saleandbuy = new ArrayList<Float>();
				params.put("agent",string);
				params.put("curday", curday);
				float buy=saleService.agentbuyMony(params);
				float sale=saleService.agentsaleMony(params);
				saleandbuy.add(sale);
				saleandbuy.add(buy);
				agentsaleandbuy.add(saleandbuy);
			}
		}
		agentArray=JSONArray.fromObject(agentsaleandbuy);
		
		String data="";
		if(!content.equals("")){
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"day\":"+day+",\"agentname\":"+agentnameArray+",\"agentArray\":"+agentArray+"}";
		}else{
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"day\":"+day+",\"agentname\":"+agentnameArray+"}";
		}
		JsonInfo jsoninfo=new JsonInfo();
		jsoninfo.setState("success");
		jsoninfo.setData(data);
		return jsoninfo;
	}
	
	@RequestMapping(value="/zhouzhuan1.action",method=RequestMethod.POST)
	public @ResponseBody JsonInfo zhouzhuanone(@RequestBody String content){
		int flycount=saleService.flyCount();
		int agentcount=saleService.agentCount();
		
		int day=(int) saleService.agentDayCount();
		

		List<String> agentname=saleService.agentName();
		JSONArray agentnameArray=JSONArray.fromObject(agentname);
		
		ArrayList agentsaleandbuy=new ArrayList();
		JSONArray agentArray=null;
		if(!content.equals("")){
			JSONObject object = JSONObject.fromObject(content);
			
			JSONArray agentList=object.getJSONArray("agent");
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int i = 0; i < agentList.size(); i++) {  
	            arrayList.add(agentList.getString(i));  
	        }  
			for(String string:arrayList){
				Map<String,Object> params=new HashMap<String, Object>();
				List<Float> saleandbuy = new ArrayList<Float>();
				params.put("agent",string);
				params.put("startday",1);
				params.put("endday", day);	
				
				List<Map<String, Object>> buy=saleService.agentbuyMonys(params);
				List<Map<String, Object>> sale=saleService.agentsaleMonys(params);
				float[] buyarr=new float[day+1];
				float[] salearr=new float[day+1];
				for(int i=0;i<buy.size();i++){
					Map<String, Object> buymap=buy.get(i);
					long buydayid=(long) buymap.get("day_id");
					buyarr[(int) buydayid]=(float) buymap.get("round");
				}
				for(int i=0;i<sale.size();i++){
					Map<String, Object> salemap=sale.get(i);
					long saledayid=(long) salemap.get("day_id");
					salearr[(int)saledayid]=(float) salemap.get("round");
				}
				for(int i=1;i<=91;i++){
					float sum=salearr[i]-buyarr[i];
					saleandbuy.add(sum);
				}
				
				agentsaleandbuy.add(saleandbuy);
			}
		}
		agentArray=JSONArray.fromObject(agentsaleandbuy);
		
		String data="";
		if(!content.equals("")){
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"day\":"+day+",\"agentname\":"+agentnameArray+",\"agentArray\":"+agentArray+"}";
		}else{
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"day\":"+day+",\"agentname\":"+agentnameArray+"}";
		}
		JsonInfo jsoninfo=new JsonInfo();
		jsoninfo.setState("success");
		jsoninfo.setData(data);
		return jsoninfo;
	}

	@RequestMapping(value="/huoyuedu.action",method=RequestMethod.POST)
	public @ResponseBody JsonInfo huoyuedu(@RequestBody String content){
		int flycount=saleService.flyCount();
		int agentcount=saleService.agentCount();
		
		int day=(int) saleService.agentDayCount();
		

		List<String> agentname=saleService.agentName();
		JSONArray agentnameArray=JSONArray.fromObject(agentname);
		
		ArrayList agentsaleandbuy=new ArrayList();
		JSONArray agentArray=null;
		if(!content.equals("")){
			JSONObject object = JSONObject.fromObject(content);
			
			JSONArray agentList=object.getJSONArray("agent");
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int i = 0; i < agentList.size(); i++) {  
	            arrayList.add(agentList.getString(i));  
	        }  
			for(String string:arrayList){
				Map<String,Object> params=new HashMap<String, Object>();
				List<Integer> saleandbuy = new ArrayList<Integer>();
				params.put("agent",string);
				params.put("startday",1);
				params.put("endday", day);	
				
				List<Map<String, Object>> buy=saleService.agentbuyCounts(params);
				List<Map<String, Object>> sale=saleService.agentsaleCounts(params);
				int[] buyarr=new int[day+1];
				int[] salearr=new int[day+1];
				for(int i=0;i<buy.size();i++){
					Map<String, Object> buymap=buy.get(i);
					long buydayid=(long) buymap.get("day_id");
					long buycnt=(long) buymap.get("cnt");
					buyarr[(int) buydayid]=(int) buycnt;
				}
				for(int i=0;i<sale.size();i++){
					Map<String, Object> salemap=sale.get(i);
					long saledayid=(long) salemap.get("day_id");
					long salecnt=(long) salemap.get("cnt");
					salearr[(int)saledayid]=(int) salecnt;
				}
				for(int i=1;i<=91;i++){
					int sum=salearr[i]-buyarr[i];
					saleandbuy.add(sum);
				}
				
				agentsaleandbuy.add(saleandbuy);
			}
		}
		agentArray=JSONArray.fromObject(agentsaleandbuy);
		
		String data="";
		if(!content.equals("")){
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"day\":"+day+",\"agentname\":"+agentnameArray+",\"agentArray\":"+agentArray+"}";
		}else{
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"day\":"+day+",\"agentname\":"+agentnameArray+"}";
		}
		JsonInfo jsoninfo=new JsonInfo();
		jsoninfo.setState("success");
		jsoninfo.setData(data);
		return jsoninfo;
	}
	@RequestMapping(value="/huoyuedu1.action",method=RequestMethod.POST)
	public @ResponseBody JsonInfo huoyueduone(@RequestBody String content){
		int flycount=saleService.flyCount();
		int agentcount=saleService.agentCount();
		
		int day=(int) saleService.agentDayCount();
		

		List<String> agentname=saleService.agentName();
		JSONArray agentnameArray=JSONArray.fromObject(agentname);
		
		ArrayList agentsaleandbuy=new ArrayList();
		JSONArray agentArray=null;
		if(!content.equals("")){
			JSONObject object = JSONObject.fromObject(content);
			int curday=Integer.parseInt(object.getString("day"));
			
			JSONArray agentList=object.getJSONArray("agent");
			ArrayList<String> arrayList = new ArrayList<String>();
			for (int i = 0; i < agentList.size(); i++) {  
	            arrayList.add(agentList.getString(i));  
	        }  
			for(String string:arrayList){
				Map<String,Object> params=new HashMap<String, Object>();
				List<Integer> saleandbuy = new ArrayList<Integer>();
				params.put("agent",string);
				params.put("curday", curday);
				int buy=saleService.agentbuyCountByDay(params);
				int sale=saleService.agentsaleCountByDay(params);
				saleandbuy.add(sale);
				saleandbuy.add(buy);
				agentsaleandbuy.add(saleandbuy);
			}
		}
		agentArray=JSONArray.fromObject(agentsaleandbuy);
		
		String data="";
		if(!content.equals("")){
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"day\":"+day+",\"agentname\":"+agentnameArray+",\"agentArray\":"+agentArray+"}";
		}else{
			data="{\"flycount\":"+flycount+",\"agentcount\":"+agentcount+",\"day\":"+day+",\"agentname\":"+agentnameArray+"}";
		}
		JsonInfo jsoninfo=new JsonInfo();
		jsoninfo.setState("success");
		jsoninfo.setData(data);
		return jsoninfo;
	}
}
