package servlet;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class aaaservlet extends BaseServlet{
	private static final String IMAGEPATH="../fresh_images/";
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op=request.getParameter("op");
		if("findfive".equals(op)) {
			dofind5(request,response);
		}
	}

	private void dofind5(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String path=request.getRequestURL().toString();
		
		UUID uuid=UUID.randomUUID();

	}
}
