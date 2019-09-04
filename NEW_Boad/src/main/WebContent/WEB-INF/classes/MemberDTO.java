package com.Member;

public class MemberDTO 
{
	private String m_strId;
	private String m_strName;
	private String m_strPw;
	private String m_strEmail;
	
	public MemberDTO(String p_name, String p_id, String p_pw, String p_Email)
	{
		this.m_strId = p_id;
		this.m_strName = p_name;
		this.m_strPw = p_pw;
		this.m_strEmail = p_Email;
	}	
	
	public String getId()
	{	return m_strId;	}
	
	public String getName()
	{	return m_strName;	}
	
	public String getPw()
	{	return m_strPw;	}
	
	public String getEmail()
	{	return m_strEmail;	}
	
	
}//class