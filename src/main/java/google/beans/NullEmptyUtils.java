package google.beans;

import java.util.List;

public class NullEmptyUtils {

	public static boolean isNullorEmptyOrNullString(String name) {
		// TODO Auto-generated method stub
		return (name==null || name.trim().equals(""));
	}

	public static boolean isNullorEmpty(List<?> list) {
		// TODO Auto-generated method stub
		return (list==null || list.isEmpty());
	}

	public static boolean isNullorEmpty(String text) {
		// TODO Auto-generated method stub
		return isNullorEmptyOrNullString(text);
	}

	public static boolean isNullorEmptyString(String name) {
		// TODO Auto-generated method stub
		return isNullorEmptyOrNullString(name);
	}

}
