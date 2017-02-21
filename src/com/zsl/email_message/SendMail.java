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

		 request.setCharacterEncoding("UTF-8");//��ֵ����
		 response.setContentType("text/html;charset=UTF-8");//���ô������
		 final String host = "smtp.163.com";  //smtp������  
		 final String from = "songlei_zhang@163.com";  //�����˵�ַ  
		 final String to = request.getParameter("email");;    //�ռ��˵�ַ  
		 final String user = "songlei_zhang@163.com";  //�û���  
		 final String pwd = "9160minswo";   //����  
		 final String subject = "�����ֶ�������֤"; //�ʼ�����  
		 
		
		 
		 Random random = new Random();
		 String fourRandom = random.nextInt(10000)+"";
		 int randLength = fourRandom.length();
	        if(randLength<4){
	          for(int i=1; i<=4-randLength; i++)
	              fourRandom = "0" + fourRandom  ;
	     }
	      
		 String context = "������֤���ǣ�"+fourRandom+"���뾡�������֤����Ҫ�ظ����ʼ�";
		 
		
		
		System.out.println(to);
		System.out.println(subject);
		System.out.println(context);
		
		
		 	Properties props = new Properties();  
	        //���÷����ʼ����ʼ������������ԣ�����ʹ�����׵�smtp��������  
	        props.put("mail.smtp.host", host);  
	        //��Ҫ������Ȩ��Ҳ�����л����������У�飬��������ͨ����֤  
	        props.put("mail.smtp.auth", "true");  
	        //�øո����úõ�props���󹹽�һ��session  
	        Session session = Session.getDefaultInstance(props);  
	        
	        //������������ڷ����ʼ��Ĺ�������console����ʾ������Ϣ��������ʹ  
	        //�ã�������ڿ���̨��console)�Ͽ��������ʼ��Ĺ��̣�  
	        session.setDebug(true);  
	        
	        //��sessionΪ����������Ϣ����  
	        MimeMessage message = new MimeMessage(session);  
	        try{  
	         //���ط����˵�ַ  
	            message.setFrom(new InternetAddress(from));  
	           //�����ռ��˵�ַ  
	            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	           //���ر���  
	            message.setSubject(subject);  
	            
	            // ��multipart����������ʼ��ĸ����������ݣ������ı����ݺ͸���  
	            Multipart multipart = new MimeMultipart();           
	            
	            
	            //�����ʼ����ı�����  
	            BodyPart contentPart = new MimeBodyPart();  
	            contentPart.setText(context);  
	            multipart.addBodyPart(contentPart);  	            
	            
	            //��multipart����ŵ�message��  
	            message.setContent(multipart);  
	            //�����ʼ�  
	            message.saveChanges();  
	            //   �����ʼ�  
	            Transport transport = session.getTransport("smtp");  
	            //���ӷ�����������  
	            transport.connect(host, user, pwd);  
	            //���ʼ����ͳ�ȥ  
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
