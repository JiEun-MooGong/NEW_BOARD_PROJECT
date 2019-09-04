package com.Member;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO 
{	
	private String strDN = "oracle.jdbc.driver.OracleDriver"; 
	private String url ="jdbc:oracle:thin:board@//localhost:1521/xe";
	private String uid = "board";
	private String upw = "board";
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
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
			String strSql = "SELETE * FROM BOARD_USER";
			
			conn = getConnection();
			pstmt = conn.prepareStatement(strSql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{	
				// ↓ dto클래스에서  set 메소드 생성 후, dto 선언 이후 이렇게 사용 가능. 
				//dto.setId(rs.getString("ID"));
				
				String name = rs.getString("NAME");
				String id = rs.getString("USER_ID");
				String pw = rs.getString("PASSWORD");
				String email = rs.getString("EMAIL");
				
				MemberDTO dto = new MemberDTO(name,id,pw,email);
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
			try {pstmt.close();}catch(SQLException sqlEx) {}
			try {conn.close();}catch(SQLException sqlEx) {}
		}
		return list;
	}
	

} //class
