import java.util.*;
public class Brain {

	public String[] dictionary;
	public String hiddenWord = "________";
	public String he = "";
	ArrayList<String> list;
	String used = "";
	ArrayList<String> invalid = new ArrayList<String>();
	Scanner in = new Scanner(System.in);
	
	public Brain(String[] wordlist, String target) {
		dictionary = wordlist;
		hiddenWord = target;
		he = hiddenWord.replaceAll("[a-zA-Z]", "_");
		list = new ArrayList<String>(Arrays.asList(wordlist));
	}
	
	public char guessLetter() {
		
		
		if(hiddenWord.equals(he) && used.length()!=0) {	//if hidden word equal to last previous hiddenword, the last inputed character was not in the word, so invalid
			invalid.add(String.valueOf(used.charAt(used.length()-1)));     //(used.get(used.size()-1));
		} else {
			he = hiddenWord;
		}
		
		list.removeIf(s -> s.length() != hiddenWord.length());		//Remove all words not the same length as the target word
		
		char input = findMostFrequent(list, invalid, used); 
		used += input;
		return input;	
	}

	char findMostFrequent(ArrayList<String> list, ArrayList<String> invalid, String used) {
		for(int i = 0; i < invalid.size(); i++) {
			String letter = invalid.get(i);
			list.removeIf(s -> s.contains(letter));
		}
		return getMax(String.join("", list), used);
	}
	
	static char getMax(String s, String used) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
		    char c = s.charAt(i);
		    Integer val = map.get(c);
		    if (val != null) {
		        map.put(c, new Integer(val + 1));
		    }
		    else {
		       map.put(c, 1);
		   }
		}
		int maxValue = 0;
		int mKey = 0;
		for(Character key: map.keySet()){
		    if(map.get(key) > maxValue && (!used.contains(String.valueOf((char)key))) ){
		        maxValue = map.get(key);
		        mKey = key;
		    }
		}
		return (char)mKey;
	}
}