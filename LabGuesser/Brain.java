import java.util.ArrayList;
import java.util.Random;

public class Brain{
	
	static Dictionary dict = new Dictionary();
	static Random r = new Random();
	static public ArrayList<String> dictionary = new ArrayList<String>();
	
	
	static String word = "aeiou";
	static String guess = "00000";
	
	public static void main(String[] args) {
		
		/*Create dictionary array list*/
		for(int i = 0; i < 15287; i++) {
			dictionary.add(dict.getWord(i));
		}
		int count  = 0;
		String input = "00000";
		
		while(count < 7) {
			input = guessWord(feedback(input));		//new guess called with method that takes previous uses the words feedback
			count++;
		}
		
	}
	
	
	public static String guessWord(String feedback) {
		//Using feedback now filter the ArrayList
		//System.out.println(guess + " -----> " + feedback);
		for(int i = 0; i < 5; i++) {
			System.out.print(guess.charAt(i));
		}
		System.out.println();
		for(int i = 0; i < 5; i++) {
			System.out.print(feedback.charAt(i));
		}
		System.out.println();
			
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
		
	}