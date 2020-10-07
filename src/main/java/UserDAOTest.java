

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.yxh.mapper.MybatisUtils;
import com.yxh.mapper.StudentMapper;
import com.yxh.mapper.UserMapper;
import com.yxh.mapper.allhaveMapper;
import com.yxh.pojo.Student;
import com.yxh.pojo.User;

import com.yxh.pojo.allhave;

public class UserDAOTest {
	
	public static void main(String []args) {
//		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
//		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
//		StudentMapper userMapper=context.getBean("StudentMapper",StudentMapper.class);
//		Student stu=new Student();
//			System.out.println(userMapper.insertstudent(stu));
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
 		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
 		allhaveMapper allhaveMapper=context.getBean("allhaveMapper",allhaveMapper.class);
		String str = getHttpInterface("https://interface.sina.cn/news/wap/fymap2020_data.d.json");
		 Gson gson = new Gson();
		JSONObject rootObject = new JSONObject(str);
         JSONObject paramzObject = rootObject.getJSONObject("data");
         JSONArray feedsArray = paramzObject.getJSONArray("list");
         allhave[] all=new allhave[feedsArray.length()];
         for (int i = 0; i < feedsArray.length(); i++) {
             JSONObject sonObject = feedsArray.getJSONObject(i);
             allhave all1=new allhave();
             String subjectStr = sonObject.getString("name");
             all1.setName(subjectStr);
             String subjectStr2 = sonObject.getString("value");
             String subjectStr3 = sonObject.getString("econNum");
             all1.setValue(subjectStr2);
             all1.setValue2(subjectStr3);
             all[i]=all1;
             System.out.println(all1);
     		
     		
     			System.out.println(allhaveMapper.updateallhave(all1));
         }
        
		
		
////		  alldata user = gson.fromJson(str, alldata.class);
	
	
//		 System.out.println(user);
//			 listdata ld2=new listdata();
//			 ld2.setName("dsds");
//			 ld2.setValue("22");
//			 
//			 listdata ld1=new listdata();
//			 ld1.setName("dsdsds");
//			 ld1.setValue("3232");
//			 listdata[] li=new listdata[2];
//			 li[0]=ld1;
//			 li[1]=ld2;
//			 list list=new list();
//			 list.setList(li);
//			 Gson gson = new Gson();
//			 String str=gson.toJson(list);
//			 System.out.println(str);
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

}
