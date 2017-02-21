<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0">    
 <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 <meta http-equiv="description" content="This is my page">
 <!--
 <link rel="stylesheet" type="text/css" href="styles.css">
 -->
  </head>
<script type="text/javascript">
    function mail() 
    {
    	var mail=document.getElementById("email").value;

    	
    	if(mail=="")
		{
			alert("请输入邮箱");
			document.form.email.focus();
			return false;
		}
		else
    	{
			
    		document.form.action="sendmail";
			document.form.submit();
			alert("邮箱验证码已发送，请注意查看");
			return false;
    	}
    	
		document.form.email.style.display="none";
			document.form.sendemail.style.display="none";
			document.form.mailcode.style.display="block";
	}
	
	function message() {
		var phone=document.getElementById("phone").value;
		
		if(phone=="")
		{
			alert("请输入电话");
			document.form.phone.focus();
			return false;
		}
		else
		{
			document.form.action="sendmessage";
			document.form.submit();
			alert("短信验证码已发送，请注意查看");
			return true;
		}
		document.form.phone.style.display="none";
		document.form.sendmessage.style.display="none";
		document.form.messagecode.style.display="block";
    }
	
	function check() {
		
        var name=document.getElementById("userName").value;
		var pass=document.getElementById("pass").value;
		var confirmPass=document.getElementById("confirmPass").value;
		var mail=document.getElementById("email").value;
		var phone=document.getElementById("phone").value;
		var addr=document.getElementById("address").value;
		
		if(name=="")
		{
			alert("Enter your userName ,please");
			document.form.userName.focus();
			return false;
		}
		else if(pass=="")
		{
			alert("Enter your password ,please");
			document.form.pass.focus();
			return false;
		}
		else if(confirmPass=="")
		{
			alert("ReEnter your password,please");
			document.form.confirmPass.focus();
			return false;
		}
		else
		{
			document.form.action="register";
			document.form.submit();
			return false;
		}
    }
	
</script>
  </head>
  
 <body>
<form id="form"  name="form" method="post" target="nm_iframe">
           <table> 
           <tr>
           		<td>邮箱：</td>
           		<td><input name="email" type="text" id="email" placeholder="请输入您的邮箱" />
           		<input name="mailcode" type="text" id="mailcode"  hidden="true" placeholder="请输入您的验证码" /></td>
           		<td><input  type="button"  name="sendemail" id="sendemail" value="接收邮箱验证码"  onclick="mail()"/></td>
           </tr>
    	   <tr>
           		<td>手机：</td>
           		<td><input name="phone" type="text" id="phone" placeholder="请输入您的手机号" />
           		<input name="messagecode" type="text" id="messagecode"  hidden="true" placeholder="请输入您的验证码" /></td>
           		<td><input  type="button"  name="sendmessage" id="sendmessage" value="接收短信验证码"  onclick="message()"/></td>
           </tr>
    	</table>
</form>
<iframe id="id_iframe" name="nm_iframe" src="about:blank" style="display:none;"></iframe>
</body>
</html>
