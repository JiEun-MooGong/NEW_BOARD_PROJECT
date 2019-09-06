package com.Member;

public class TotClass 
{
	public TotClass()
	{
	}
	
	//---------------------------
	//스크립트 이스케이프
	//---------------------------
	public static String getEscapedString(String s)
	{
		try
		{
			String str = s;
			str = str.replace("&", "&amp;");
			str = str.replace("<", "&lt;");
			str = str.replace(">", "&gt;");
			str = str.replace("\"", "&quot;");
		
			return str;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}	
	}
	
	//---------------------------
	//스크립트 이스케이프
	//---------------------------
	public static void getChekingString(String pram,String pramName)
	{
		try
		{
			//유효성 검사
			if(pram==""||pram==null)
			{ 
				System.out.println(pramName + " 입력해주세요.<hr>");
				System.out.print("<input type=\"button\" value=\"BACK\" onClick=\"history.go(-1)\">");
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}	
	
	
}//class
