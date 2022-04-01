import java.util.Scanner;
public class Lab08Bitwise {
	/*
	First line contains an integer which is the base the number N is represented in (base 1)
	Second line contains an integer which is the base that the number N should be transformed into (base 2)
	Third line contains an integer N
	
	*/
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int b1 = in.nextInt();
		int b2 = in.nextInt();
		String n = in.next();
		
		
		if(b1 == 16) {
			System.out.println(hexToDec(n, b2));
		}else if(b2 == 16) {
			int dec = toDecimal(Integer.parseInt(n), b1);
			System.out.println(DecToHex(dec));
		} else {
			int dec = toDecimal(Integer.parseInt(n), b1);
			System.out.println(decimalToBase(dec, b2));
		}
	}
	
	static int toDecimal(int n, int b1) {
		int store = 0, power = 0;
		while(n != 0) {
			store += n%10 * Math.pow(b1, power);
			n /= 10;
			power++;
		}
		return store;
	}
		
	
	static String decimalToBase (int param, int b2) {
		if(param == 0) {
			return "";
		} else {
			return decimalToBase( (param - (param%b2))/b2, b2) + String.valueOf(param%b2);
		}	
	}
	
	static int hexToDec (String param, int b2) {
		int pow = 0;
		int store = 0;
		String values = "123456789ABCDEF";
		
		
		while(pow < param.length()) {
			int index = param.length()-1 - pow;			//counting from right of string, starting at 0, as move left increment by one
			store += (values.indexOf(param.charAt(index))+1) * Math.pow(16, pow);
			pow++;
		}
		//return store;
		if(b2 == 10)			//if base 16 need to be changed to base 10 no point in converting base 10 output to base 10 again
			return store;
		else
			return Integer.parseInt(decimalToBase(store, b2));
	}
	
	static String DecToHex (int param) {
		String[] val = {"1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
		if(param == 0) {
			return "";
		} else {
			return DecToHex( (param - (param%16))/16) + val[(param%16)-1];
		}
}
}
