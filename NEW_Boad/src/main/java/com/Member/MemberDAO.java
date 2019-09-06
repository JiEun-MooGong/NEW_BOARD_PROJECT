package com.Member;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.ArrayList;

public class MemberDAO 
{	
	private String strDN = "oracle.jdbc.driver.OracleDriver"; 
	private String url ="jdbc:oracle:thin:board@//localhost:1521/xe";
	private String uid = "board";
	private String upw = "board";
	
	private Connection conn = null;
	//private PreparedStatement pstmt = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	// 싱글톤패턴 ?
	private static MemberDAO dao = new MemberDAO();	

	public static MemberDAO getMemberDAO()
	{
		return dao;
	}
	
	private MemberDAO()
	{	
	}

	private Connection getConnection()
	{	
		try
		{
			Class.forName(strDN);
			conn = DriverManager.getConnection(url,uid,upw);
			
			// ↓ 드라이버설치 db 커넥션 풀 설정 시 
			//Context conntxt = new InitialContext();
			//DataSource ds = (DataSource)conntxt.lookup(name);
			//conn = ds.getConnection();
			return conn;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}		
	}
	
	public ArrayList<MemberDTO> Select()
	{
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
				
		try
		{
			String strSql = "SELECT * FROM BOARD_USER";
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
			
			while(rs.next())
			{	
				// ↓ dto클래스에서  set 메소드 생성 후, dto 선언 이후 이렇게 사용 가능. 
				//dto.setId(rs.getString("ID"));
				
				MemberDTO dto = new MemberDTO(rs.getString("NAME"),rs.getString("USER_ID"),rs.getString("PASSWORD"),rs.getString("EMAIL"));
				list.add(dto);				
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		finally
		{
			try {rs.close();}catch(SQLException sqlEx) {}
			try {stmt.close();}catch(SQLException sqlEx) {}
			try {conn.close();}catch(SQLException sqlEx) {}
		}
		
		return list;
	}
	
	//======================================
	// 로그인시 아이디 체크
	//======================================
	public String CheckId(String p_Id, String p_Pw)
	{
		String strName = "";
		
		try
		{
			String strSql = "SELECT * FROM BOARD_USER WHERE PASSWORD = '" + p_Pw +"' AND USER_ID = '" + p_Id + "'" ;
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
			
			while(rs.next())
			{	
				strName = rs.getString("NAME");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		finally
		{
			try {rs.close();}catch(SQLException sqlEx) {}
			try {stmt.close();}catch(SQLException sqlEx) {}
			try {conn.close();}catch(SQLException sqlEx) {}
		}
		
		return strName;
	}
	
	//======================================
	// 회원가입 
	//======================================
	public boolean Insert_User(MemberDTO dto)
	{	
		try
		{	
			String strSql = "";
			strSql += "INSERT INTO BOARD_USER";
			strSql += "(USER_ID, PASSWORD, NAME, EMAIL)";
			strSql += "VALUES('"+ dto.getId() + "', '" + dto.getPw() +"','" + dto.getName() + "','" + dto.getEmail() + "')";
			
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(strSql);
			
			return true;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}	
		finally
		{
			try {rs.close();}catch(SQLException sqlEx) {}
			try {stmt.close();}catch(SQLException sqlEx) {}
			try {conn.close();}catch(SQLException sqlEx) {}
		}
	}	

} //class
