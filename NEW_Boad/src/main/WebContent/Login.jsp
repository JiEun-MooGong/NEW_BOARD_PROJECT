<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.Member.MemberDAO" %>
<%@ page import="com.Member.MemberDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> : 로그인 : </title>
</head>
 <% 
  request.setCharacterEncoding("utf-8");
	
	try
	{
		//변수 선언
		String strId =  request.getParameter("ID");
		String strPass = request.getParameter("PASSWORD");
		String strName = "";
		
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
		
		//dao
		MemberDAO dao = MemberDAO.getMemberDAO();
		ArrayList<MemberDTO> list = dao.Select();
		for(MemberDTO dto : list)
		{
			out.println("<tr>");
			out.println("<td>" + dto.getId() + "</td>");
			out.println("<td>" + dto.getPw() + "</td>");
			out.println("<td>" + dto.getName() + "</td>");
			out.println("<td>" + dto.getEmail() + "</td>");
			out.println("</tr>");
		}
		
		//if(strName == "" || strName==null)
		//{ 
			//out.println("사용자 정보가 없습니다.<hr>");
		  	//out.print("<input type=\"button\" value=\"BACK\" onClick=\"history.go(-1)\">");
		//}	
		//else
		//{
			//out.println(strName + "님 환영합니다.<hr>");
			//session.setAttribute("userid",strId);
			//response.sendRedirect("list.jsp");
		//}
		
	}
	catch (Exception ex)
	{
		out.println("에러 : " + ex.getMessage() );
		ex.printStackTrace();
	}
%>
<body>
</body>
</html>