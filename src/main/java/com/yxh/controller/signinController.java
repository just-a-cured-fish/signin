package com.yxh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.zxing.WriterException;
import com.yxh.mapper.signinMapper;
import com.yxh.mapper.thesignMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.allhave;
import com.yxh.pojo.class1;
import com.yxh.pojo.code;
import com.yxh.pojo.echarts;
import com.yxh.pojo.signin;

@RestController
public class signinController {
	@RequestMapping(value="/findandchoose",produces = "text/html;charset=UTF-8")
	public String json102(signin signin,HttpServletRequest request) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<signin> signin1=signinMapper.selectchoose(signin);
			List<Student> stu=signinMapper.selectstunot(signin);
			if(signin!=null) {
				code.setData(signin1);
				code.setData2(stu);
				code.setCode(0);
			}else {
				code.setCode(1);
			}
			
				
			str=gson.toJson(code);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping(value="/findtruesign",produces = "text/html;charset=UTF-8")
	public String json1(signin signin,HttpServletRequest request) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<signin> signin1=signinMapper.selecttrue(signin);
			if(signin!=null) {
				code.setData(signin1);	
				code.setCode(0);
			}else {
				code.setCode(1);
			}
			
				
			str=gson.toJson(code);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping(value="/findistrue2",produces = "text/html;charset=UTF-8")
	public String json4(signin signin,HttpServletRequest request) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<signin> signin1=signinMapper.selectnottrue2(signin);
			if(signin!=null) {
				code.setData(signin1);	
				code.setCode(0);
			}else {
				code.setCode(1);
			}
			
				
			str=gson.toJson(code);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping(value="/findistrue3",produces = "text/html;charset=UTF-8")
	public String json5(signin signin,HttpServletRequest request) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		try {
			List<signin> signin1=signinMapper.selectnottrue3(signin);
			if(signin!=null) {
				code.setData(signin1);	
				code.setCode(0);
			}else {
				code.setCode(1);
			}
			
				
			str=gson.toJson(code);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	@RequestMapping(value="/echartshen",produces = "text/html;charset=UTF-8")
	public String json10(signin signin) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		
		Map<String,Integer> themap=new HashMap();
		List<allhave> echart=new ArrayList<>();
		try {
			
			List<signin> signin1=signinMapper.selecttrue(signin);
			List<signin> signin2=signinMapper.selectnottrue(signin);
			for(signin signin3 : signin1) {
				String a=signin3.getAddress().substring(0, signin3.getAddress().indexOf("省")+1);
				System.out.println(a);
				themap.put(a,themap.getOrDefault(a, 0)+1);
			}
			for(signin signin3 : signin2) {
				String a=signin3.getAddress().substring(0, signin3.getAddress().indexOf("省")+1);
				System.out.println(a);
				themap.put(a,themap.getOrDefault(a, 0)+1);
			}
				
			Set<String> keySet = themap.keySet();
			 
			//有了Set集合。就可以获取其迭代器。
			Iterator<String> it = keySet.iterator();
	 
			while(it.hasNext())
			{
				String key = it.next();
				//有了键可以通过map集合的get方法获取其对应的值。
				Integer value  = themap.get(key);
				allhave all=new allhave();
				all.setName(key);
				all.setValue(value.toString());
				echart.add(all);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(echart);
	}
	@RequestMapping(value="/echartshi",produces = "text/html;charset=UTF-8")
	public String json11(signin signin) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		
		Map<String,Integer> themap=new HashMap();
		List<allhave> echart=new ArrayList<>();
		try {
			
			List<signin> signin1=signinMapper.selecttrue(signin);
			List<signin> signin2=signinMapper.selectnottrue(signin);
			for(signin signin3 : signin1) {
				String a=signin3.getAddress().substring(0, signin3.getAddress().indexOf("市")+1);
				
				System.out.println(a);
				themap.put(a,themap.getOrDefault(a, 0)+1);
			}
			for(signin signin3 : signin2) {
				String a=signin3.getAddress().substring(0, signin3.getAddress().indexOf("市")+1);
				
				System.out.println(a);
				themap.put(a,themap.getOrDefault(a, 0)+1);
			}
				
			Set<String> keySet = themap.keySet();
			 
			//有了Set集合。就可以获取其迭代器。
			Iterator<String> it = keySet.iterator();
	 
			while(it.hasNext())
			{
				String key = it.next();
				//有了键可以通过map集合的get方法获取其对应的值。
				Integer value  = themap.get(key);
				allhave all=new allhave();
				all.setName(key);
				all.setValue(value.toString());
				echart.add(all);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.toJson(echart);
	}
	@RequestMapping(value="/findnottruesign",produces = "text/html;charset=UTF-8")
	public String json2(signin signin,HttpServletRequest request) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		
		try {
			List<signin> signin1=signinMapper.selectnottrue(signin);
			List<Student> stu=signinMapper.selectstunot(signin);
			for(Student stu1:stu) {
				System.out.println(stu1);
				signin signin2=new signin();
				signin2.setSname(stu1.getSname());
				signin2.setId(-1);;
				signin1.add(signin2);
				
			}
			if(signin!=null) {
				code.setData(signin1);	
				code.setCode(0);
			}else {
				code.setCode(1);
			}
			
				
			str=gson.toJson(code);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}