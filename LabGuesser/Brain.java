import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Brain{
	
	static Dictionary dict = new Dictionary();
	static Random r = new Random();
	static public ArrayList<String> dictionary = new ArrayList<String>();
	
	
	static String word = "roate";
	static String guess = "00000";
	
	public static void main(String[] args) {
		
		/*Create dictionary array list */
		for(int i = 0; i < 15287; i++) {
			dictionary.add(dict.getWord(i));
		} 
		
		
		
		int count  = 0;
		String input = "00000";
		
		while(count < 7 && !input.equals("22222")) {
			input = guessWord(feedback(input));		//new guess called with method that takes previous uses the words feedback
			count++;
		} 
		
	}
	
	
	public static String guessWord(String feedback) {
		//Using feedback now filter the ArrayList
		System.out.println("\n" + dictionary.size());
		for(int i = 0; i < 5; i++) {
			if(feedback.charAt(i) == '0') {
				black(String.valueOf(guess.charAt(i)));		//Need to something about casting that char to string for black method, maybe store guess in array using .split
			}
			else if(feedback.charAt(i) == '2') {
				green(guess.charAt(i), i);
			}
			else if(feedback.charAt(i) == '1') {			//Watch out for yellow
				yellow(guess.charAt(i), i);
			}
			}
		System.out.println(feedback + "\n" +guess);
			
		
		
		
		
		
		
		//Picking the next word to be filtered
		guess = dictionary.get(r.nextInt(dictionary.size()));
		System.out.println("The next word is " + guess + "\n");
		return guess;
		
		
	}
	
	
	/*Gives feedback of guessed word*/
	private static String feedback(String input) {
			String ret = "00000";
			for(int i = 0; i < 5; i++) {
				if(word.contains(String.valueOf(input.charAt(i)))) {
					for(int j = 0; j < 5; j++) {
						if(input.charAt(i) == word.charAt(j)) {
							if(i == j) {
								ret = ret.substring(0,i) + "2" + ret.substring(i+1);	
							} else {
								ret = ret.substring(0,i) + "1" + ret.substring(i+1);
							}
						}
					}
				} 
			}
			return ret;
		}
	
	private static void black(String letter) {
		int count = 0;
		while(count != dictionary.size() && dictionary.size() > 1) {	//Need to do something about that 1 check
			if(dictionary.get(count).contains(letter)) {
			dictionary.remove(count);
			count--;
			}
			count++;
			}
		}
	
	private static void green(char letter, int index) {
		int count = 0;
		while(count != dictionary.size() && dictionary.size() > 1) {
			if(dictionary.get(count).charAt(index) != letter) {
				dictionary.remove(count);
				count--;
			}
			count++;
		}
	}
	
	private static void yellow(char letter, int index) {
		int count = 0;
		while(count != dictionary.size() && dictionary.size() > 1) {
			if(dictionary.get(count).charAt(index) == letter || !dictionary.get(count).contains(String.valueOf(letter))) { //if word contains letter at index or not contain letter at all
				dictionary.remove(count);
				count--;
			}
			count++;
		}
	}
	
}
