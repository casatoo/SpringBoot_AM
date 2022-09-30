package com.KMS.exam.demo.util;

public class Ut {
	
	public static boolean checkStr(Object obj) {
		if(obj == null) {
			return true;
		}
		if(obj instanceof String == false) {
			return true;
		}
		String str = (String) obj;
		
		
		return str.trim().length() == 0;
	}
	
    public static String f(String format, Object... args){
        return String.format(format,args);
    }
}
