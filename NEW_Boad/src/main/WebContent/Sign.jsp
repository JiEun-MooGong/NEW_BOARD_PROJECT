<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import ="java.sql.*" %>

<%@ page import="com.Member.MemberDAO" %>
<%@ page import="com.Member.MemberDTO" %>
<%@ page import="com.Member.TotClass" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> : 회원가입 : </title>
</head>
<%
request.setCharacterEncoding("utf-8");
	
	try
	{
		//변수 선언
		String strId =  request.getParameter("ID");
		String strPass = request.getParameter("PASSWORD");
		String strName = request.getParameter("NAME");
		String strEmail = request.getParameter("EMAIL");
		
		strId = TotClass.getEscapedString(strId);
		//TotClass.getChekingString(strId,"ID");
		
		//유효성 검사
		if(strId==""||strId==null)
		{ 
			out.println("ID을 입력해주세요.<hr>");
		  	out.print("<input type=\"button\" value=\"BACK\" onClick=\"history.go(-1)\">");
		  	return;
		}
		if(strPass==""||strPass==null)
		{ 
			out.println("비밀번호를 입력해주세요.<hr>");
			out.print("<input type=\"button\" value=\"BACK\" onClick=\"history.go(-1)\">");
		  	return;
		}
		if(strName==""||strName==null)
		{ 
			out.println("이름을 입력해주세요.<hr>");
			out.print("<input type=\"button\" value=\"BACK\" onClick=\"history.go(-1)\">");
		  	return;
		}		
		
		MemberDAO dao = MemberDAO.getMemberDAO();
		MemberDTO dto = new MemberDTO(strName,strId,strPass,strEmail);
		
		if(dao.Insert_User(dto) == true)
		{ 
			out.println("회원가입 완료!<hr>");
		  	out.print("<input type=\"button\" value=\"OK\" onClick=\"location.href='Login.html'\">");		  	
		}	
		else
		{	
			out.println("회원가입 실패!<hr>");
			out.print("<input type=\"button\" value=\"BACK\" onClick=\"history.go(-1)\">");		
		}		
	}
	catch (Exception e)
	{	
		out.println("에러 : " + e.getMessage());
		e.printStackTrace();
	}
%>
<body>
</body>
</html>