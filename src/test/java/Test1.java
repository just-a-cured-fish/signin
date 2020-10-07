import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yxh.mapper.StudentMapper;
import com.yxh.mapper.allhaveMapper;
import com.yxh.mapper.classMapper;
import com.yxh.mapper.newsMapper;
import com.yxh.mapper.signinMapper;
import com.yxh.mapper.teacherMapper;
import com.yxh.mapper.thesignMapper;
import com.yxh.pojo.Teacher;
import com.yxh.pojo.allhave;
import com.yxh.pojo.class1;
import com.yxh.pojo.code;
import com.yxh.pojo.dailydata;
import com.yxh.pojo.data;
import com.yxh.pojo.news;
import com.yxh.pojo.signin;
import com.yxh.pojo.thesign;
import com.yxh.pojo.totoldata;

public class Test1 {
	@Test
	public void test() {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		teacherMapper userMapper=context.getBean("teacherMapper",teacherMapper.class);
		Teacher stu=new Teacher();
		stu.setTid("89");
		Teacher a=userMapper.selectBlog(stu);
	
			Gson gson=new Gson();
			System.out.println(gson.toJson(a));
		
	}
	@Test
	public void test2() {
		String str = getHttpInterface("http://api.tianapi.com/txapi/ncov/index?key=3738ae7c4f7f46704e1adece1d00355f");
		 Gson gson = new Gson();
		 JSONObject rootObject = new JSONObject(str);
		  JSONArray feedsArray = rootObject.getJSONArray("newslist");
		  JSONObject sonObject = feedsArray.getJSONObject(0);
		  JSONArray feedsArray2 = sonObject.getJSONArray("news");
		  ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
			//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		  newsMapper newsMapper=context.getBean("newsMapper",newsMapper.class);
			List<news> newsall=new ArrayList();
		  for (int i = 0; i < feedsArray2.length(); i++) {
			  JSONObject sonObject1 = feedsArray2.getJSONObject(i);
			  String title = sonObject1.getString("title");
			  String pubDatestr = sonObject1.getString("pubDateStr");
			  String summary=sonObject1.getString("summary");
			  news news=new news();
			  if(pubDatestr.indexOf("小时前")!=-1) {
				 
				  int time=Integer.parseInt(pubDatestr.replace("小时前",""));
				  Calendar calendar=Calendar.getInstance();
				  Date nowTime=calendar.getTime();
				
				  calendar.add(Calendar.HOUR, time-2*time); //减填bai负du数zhidao
				  nowTime=calendar.getTime();
			
				  SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");  
				  pubDatestr=formatter.format(nowTime);  
				  
				  
			  } 
			  if(pubDatestr.indexOf("分钟前")!=-1) {
				  
				  int time=Integer.parseInt(pubDatestr.replace("分钟前",""));
				  Calendar calendar=Calendar.getInstance();
				  Date nowTime=calendar.getTime();
				
				  calendar.add(Calendar.SECOND, time-2*time); //减填bai负du数zhidao
				  nowTime=calendar.getTime();
  
				  SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");  
				  pubDatestr=formatter.format(nowTime);  
				  
			  } 
			  news.setTitle(title);
			  news.setPubDateStr(pubDatestr);
			  news.setSummary(summary);
			  System.out.println(title);
			  System.out.println(pubDatestr);
			  System.out.println(summary);
			  newsall.add(news);
			  
		
		  }
		  int a=newsMapper.insertnews(newsall);
		  System.out.println(a);
	}
	@Test
	public void test1() {
		String str = getHttpInterface("https://interface.sina.cn/news/wap/fymap2020_data.d.json");
		 Gson gson = new Gson();
		JSONObject rootObject = new JSONObject(str);
        JSONObject paramzObject = rootObject.getJSONObject("data");
        JSONObject paramzObject2 = paramzObject.getJSONObject("add_daily");
       
        
        totoldata total=new totoldata();
        total.setCuretotal(paramzObject.getString("curetotal"));
        total.setDeathtotal(paramzObject.getString("deathtotal"));
        total.setEconNum(paramzObject.getString("econNum"));
        total.setJwsrNum(paramzObject.getString("jwsrNum"));
        total.setGntotal(paramzObject.getString("gntotal"));
        dailydata daily=new dailydata();
        daily.setAddcon_new(paramzObject2.getString("addcon_new"));
        daily.setAddcure_new(paramzObject2.getString("addcure_new"));
        daily.setAdddeath_new(paramzObject2.getString("adddeath_new"));
        daily.setAddjwsr(paramzObject2.getString("addjwsr"));
        data data=new data();
        data.setTimes(paramzObject.getString("times"));
        data.setDailydata(daily);
        data.setTotoldata(total);
        System.out.println(data);
       
	}
	public static String getHttpInterface(String path){
        BufferedReader in = null;
        StringBuffer result = null;
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");
            connection.connect();
 
            result = new StringBuffer();
            //读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
	@Test
	public void test11() {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		classMapper classMapper=context.getBean("classMapper",classMapper.class);
		thesignMapper TeacherMapper=context.getBean("thesignMapper",thesignMapper.class);
		thesign thesign=new thesign();
		
		String str="";
		Gson gson=new Gson();
		code code=new code();
		class1 class1=new class1();
		class1.setTid("89");
		thesign.setTid(class1.getTid());
	    int b=0;
	    List<thesign> thesign2=TeacherMapper.selectbyid(thesign);
	    int a=0;
	    int c=0;
		try {
		    List<class1> class2=new ArrayList<>();
		   
		    class2=classMapper.selectbyid(class1);
		   
		    a=class2.size();
		    c=thesign2.size();
		    for(class1 class3:class2) {
		    	b=class3.getAmount()+b;
		    }
	
		
			str=gson.toJson(code);
				
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,Integer> map=new HashMap<>();
		map.put("班级人数", a);
		map.put("学生人数", b);
		map.put("发起的签到", c);
	System.out.println(gson.toJson(map));
	}
	@Test
	public void test13() {
//		String str="https://oneisland.top/fresh_images/1597409737406wxa299737fe1b98795.o6zAJs73JY4Fcixpu_gsWS4zXdnQ.xK9yxpwReOcU665a3fd3aa9866a56c92b2a807730bb8.png";
//		 str = str.substring(str.indexOf("fresh_images"));
//		System.out.println(str);
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		signinMapper signinMapper=context.getBean("signinMapper",signinMapper.class);
		
		String str="";
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

		code code=new code();
		signin signin=new signin();
		signin.setThesignid(1);
		Map<String,Integer> themap=new HashMap();
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
				System.out.println("key:"+key+",value:"+value);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Test
	public void test32() {
		String str = getHttpInterface("https://c.m.163.com/ug/api/wuhan/app/data/list-total");
		 Gson gson = new Gson();
		JSONObject rootObject = new JSONObject(str);
      JSONObject paramzObject = rootObject.getJSONObject("data");
      
      JSONObject paramzObject2 = paramzObject.getJSONObject("chinatotal");
     System.out.print(paramzObject2.toString());
      
//      totoldata total=new totoldata();
//      total.setCuretotal(paramzObject.getString("curetotal"));
//      total.setDeathtotal(paramzObject.getString("deathtotal"));
//      total.setEconNum(paramzObject.getString("econNum"));
//      total.setJwsrNum(paramzObject.getString("jwsrNum"));
//      total.setGntotal(paramzObject.getString("gntotal"));
//      total.setAsymptoNum(paramzObject.getString("asymptomNum"));
//      dailydata daily=new dailydata();
//      daily.setAddcon_new(paramzObject2.getString("addcon_new"));
//      daily.setAddcure_new(paramzObject2.getString("addcure_new"));
//      daily.setAdddeath_new(paramzObject2.getString("adddeath_new"));
//      daily.setAddjwsr(paramzObject2.getString("addjwsr"));
//      daily.setAddasymptom(paramzObject2.getString("addasymptom"));
//      daily.setAddecon_new(paramzObject2.getString("addecon_new"));
//      data data=new data();
//      data.setTimes(paramzObject.getString("times"));
//      data.setDailydata(daily);
//      data.setTotoldata(total);
//     try {
//		toPrintJson(response,data);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
		
	}
}
