package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.yxh.mapper.allhaveMapper;
import com.yxh.pojo.allhave;
import com.yxh.pojo.code;
import com.yxh.pojo.dailydata;
import com.yxh.pojo.data;
import com.yxh.pojo.totoldata;

@WebServlet("/allhave.action")
public class allhaveServlet  extends BaseServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=request.getParameter("op");
		if("findall".equals(op)) {
			dofind(request,response);
		}else if("findtoday".equals(op)) {
			dofind2(request,response);
		}else if("findtotalda".equals(op)) {
			dofind3(request,response);
		}
	}

	private void dofind3(HttpServletRequest request, HttpServletResponse response) {
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
       total.setAsymptoNum(paramzObject.getString("asymptomNum"));
       dailydata daily=new dailydata();
       daily.setAddcon_new(paramzObject2.getString("addcon_new"));
       daily.setAddcure_new(paramzObject2.getString("addcure_new"));
       daily.setAdddeath_new(paramzObject2.getString("adddeath_new"));
       daily.setAddjwsr(paramzObject2.getString("addjwsr"));
       daily.setAddasymptom(paramzObject2.getString("addasymptom"));
       daily.setAddecon_new(paramzObject2.getString("addecon_new"));
       data data=new data();
       data.setTimes(paramzObject.getString("times"));
       data.setDailydata(daily);
       data.setTotoldata(total);
      try {
		toPrintJson(response,data);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	private void dofind2(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");	
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		allhaveMapper allhaveMapper=context.getBean("allhaveMapper",allhaveMapper.class);
		List<allhave> a=allhaveMapper.selectvaluetoday();
		code code=new code();
		if(a!=null) {
			code.setCode(0);
		}else {
			code.setCode(1);
		}
		code.setData(a);
	
		try {
			toPrintJson(response,code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void dofind(HttpServletRequest request, HttpServletResponse response) {
		ApplicationContext context=new ClassPathXmlApplicationContext("spring-dao.xml");
		//UserMapper userMapper=context.getBean("userMapper",UserMapper.class);
		allhaveMapper allhaveMapper=context.getBean("allhaveMapper",allhaveMapper.class);
		List<allhave> a=allhaveMapper.selectvalue();
		code code=new code();
		if(a!=null) {
			code.setCode(0);
		}else {
			code.setCode(1);
		}
		code.setData(a);
	
		try {
			toPrintJson(response,code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
