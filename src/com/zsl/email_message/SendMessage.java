package com.zsl.email_message;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class SendMessage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SendMessage() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 request.setCharacterEncoding("gbk");//传值编码
		 response.setContentType("text/html;charset=gbk");//设置传输编码

		 final String to = request.getParameter("phone");;    //收件人号码
		 
		 Random random = new Random();
		 String fourRandom = random.nextInt(10000)+"";
		 int randLength = fourRandom.length();
	        if(randLength<4){
	          for(int i=1; i<=4-randLength; i++)
	              fourRandom = "0" + fourRandom  ;
	     }
	      
		 String context = "您的验证码是："+fourRandom+"。请尽快完成验证，不要回复此短信";
		 System.out.println(context);
		 
		 HttpClient client = new HttpClient();
		 PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn"); 
		 post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码
		 NameValuePair[] data ={ new NameValuePair("Uid", "zhang0619"),new NameValuePair("Key", "567c1220822bc74daac8"),
				 				 new NameValuePair("smsMob",to),new NameValuePair("smsText",context)};
		 post.setRequestBody(data);

		 client.executeMethod(post);
		 Header[] headers = post.getResponseHeaders();
		 int statusCode = post.getStatusCode();
		 System.out.println("statusCode:"+statusCode);
		 for(Header h : headers)
		 {
		 System.out.println(h.toString());
		 }
		 String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
		 System.out.println(result); //打印返回消息状态

		 post.releaseConnection();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
