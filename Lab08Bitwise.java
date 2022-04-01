import java.util.Scanner;
public class Lab08Bitwise {
	
	String val = "";
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int b1 = in.nextInt();
        int b2 = in.nextInt();
        int n = in.nextInt();
		int store = 0;
				
		if(b1 == 16) {
			out(hexTo(String.valueOf(n)), b2);
		} else if (b2 == 16) {
			//int N = Integer.parseInt(n);
			int power = 0;
			while(n != 0) {
				store += n%10 * Math.pow(b1, power);
				n /= 10;
				power++;
			}
			System.out.println(hex(store, b2));
		} else {
			//int N = Integer.parseInt(n);
		int power = 0;
		while(n != 0) {
			store += n%10 * Math.pow(b1, power);
			n /= 10;
			power++;
		}		
		System.out.println(out(store, b2));
		} 
	}
	
	static int hexTo (String param) {
		int store = 0;
		int pow = 0;
		String values = "ABCDEF";
		for(int i = param.length()-1; i >=0; i--) {
			char current = param.charAt(i);
			if(!Character.isDigit(current)) {
				store += (values.indexOf(current) + 10) * (Math.pow(16, pow));
			} else {
				store += Character.getNumericValue(current) * (Math.pow(16, pow));
			}
			pow++;
		}
		return store;
	}
	
	static String out (int param, int b2) {
		if(param == 0) {
			return "";
		} else {
			return out( (param - (param%b2))/b2, b2) + String.valueOf(param%b2);
		}	
	}
	
	static String hex (int param, int b2) {
		String[] val = {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		if(param == 0) {
			return "";
		} else {
			return out( (param - (param%b2))/b2, b2) + val[(param%b2)-1];
		}	
	}
	
	static int bin( String param) {
		int pow = 0;
		int st = 0;
		for(int i = 0; i <param.length(); i++ ) {
			int current = Character.getNumericValue(param.charAt(i));
			st += current * Math.pow(2, (i+1));
		}
		return st;
	}
}
