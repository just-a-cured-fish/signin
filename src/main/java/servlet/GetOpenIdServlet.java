package servlet;
 
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
 
import com.google.gson.Gson;
 

 
@WebServlet(urlPatterns={"/GetOpenIdServlet"})
public class GetOpenIdServlet extends HttpServlet {
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String op=request.getParameter("op");
		if("findstu".equals(op)) {
			dostu(request,response);
		}else if("findtea".equals(op)) {
			dotea(request,response);
		}

    }
 
    private void dotea(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
    	//设置请求编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
 
       
        //转成json数据
        String appid=request.getParameter("appid");
        String secret=request.getParameter("secret");
        String js_code=request.getParameter("js_code");
        
        WeChatService getOpenId=new WeChatService();
        String openId=getOpenId.GetOpenID("wxdf406ec942665120","f804fe42e19905777a50bd120f4b04b7",js_code);
        Map<String, String> result = new HashMap<String, String>();;
        result.put("openId",openId);
        result.put("msg", "后台已收到");
        String json = new Gson().toJson(result);
        //返回值给微信小程序
        Writer out = response.getWriter();
        out.write(json);
        out.flush();
	}

	private void dostu(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		// TODO Auto-generated method stub
    	//设置请求编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        /* 设置响应头允许ajax跨域访问 */
        response.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
 
       
        //转成json数据
        String appid=request.getParameter("appid");
        String secret=request.getParameter("secret");
        String js_code=request.getParameter("js_code");
        
        WeChatService getOpenId=new WeChatService();
        String openId=getOpenId.GetOpenID("wxded1ac7cd5d630d2","27323a538da20f9deb29d353e9832d19",js_code);
        Map<String, String> result = new HashMap<String, String>();;
        result.put("openId",openId);
        result.put("msg", "后台已收到");
        String json = new Gson().toJson(result);
        //返回值给微信小程序
        Writer out = response.getWriter();
        out.write(json);
        out.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}