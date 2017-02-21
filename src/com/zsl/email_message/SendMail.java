package com.zsl.email_message;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  

public class SendMail extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SendMail() {
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

		 request.setCharacterEncoding("UTF-8");//传值编码
		 response.setContentType("text/html;charset=UTF-8");//设置传输编码
		 final String host = "smtp.163.com";  //smtp服务器  
		 final String from = "songlei_zhang@163.com";  //发件人地址  
		 final String to = request.getParameter("email");;    //收件人地址  
		 final String user = "songlei_zhang@163.com";  //用户名  
		 final String pwd = "9160minswo";   //密码  
		 final String subject = "心随乐动邮箱验证"; //邮件标题  
		 
		
		 
		 Random random = new Random();
		 String fourRandom = random.nextInt(10000)+"";
		 int randLength = fourRandom.length();
	        if(randLength<4){
	          for(int i=1; i<=4-randLength; i++)
	              fourRandom = "0" + fourRandom  ;
	     }
	      
		 String context = "您的验证码是："+fourRandom+"。请尽快完成验证，不要回复此邮件";
		 
		
		
		System.out.println(to);
		System.out.println(subject);
		System.out.println(context);
		
		
		 	Properties props = new Properties();  
	        //设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）  
	        props.put("mail.smtp.host", host);  
	        //需要经过授权，也就是有户名和密码的校验，这样才能通过验证  
	        props.put("mail.smtp.auth", "true");  
	        //用刚刚设置好的props对象构建一个session  
	        Session session = Session.getDefaultInstance(props);  
	        
	        //有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使  
	        //用（你可以在控制台（console)上看到发送邮件的过程）  
	        session.setDebug(true);  
	        
	        //用session为参数定义消息对象  
	        MimeMessage message = new MimeMessage(session);  
	        try{  
	         //加载发件人地址  
	            message.setFrom(new InternetAddress(from));  
	           //加载收件人地址  
	            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	           //加载标题  
	            message.setSubject(subject);  
	            
	            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件  
	            Multipart multipart = new MimeMultipart();           
	            
	            
	            //设置邮件的文本内容  
	            BodyPart contentPart = new MimeBodyPart();  
	            contentPart.setText(context);  
	            multipart.addBodyPart(contentPart);  	            
	            
	            //将multipart对象放到message中  
	            message.setContent(multipart);  
	            //保存邮件  
	            message.saveChanges();  
	            //   发送邮件  
	            Transport transport = session.getTransport("smtp");  
	            //连接服务器的邮箱  
	            transport.connect(host, user, pwd);  
	            //把邮件发送出去  
	            transport.sendMessage(message, message.getAllRecipients());  
	            transport.close();  
	        }catch(Exception e){  
	            e.printStackTrace();  
	        }  
		
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
