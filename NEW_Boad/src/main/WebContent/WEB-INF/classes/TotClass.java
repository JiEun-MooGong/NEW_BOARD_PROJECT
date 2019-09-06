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
	
}//class
