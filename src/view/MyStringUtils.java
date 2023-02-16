package view;

public class MyStringUtils {

	public static String repeat(String elem, int length) {
		
		String out = "";
		
		for(int i = 0; i < length; i++)
			out += elem;
		
		return out;
	}
	
	public static String center(String text, int length) {
		
		String out = String.format("%"+ length + "s%s%" + length + "s", "", text, "");
		
		float mid = (out.length()/2);
		float start = mid - (length/2);
		float end = start + length;

		return out.substring((int)start, (int)end);
	}
}
