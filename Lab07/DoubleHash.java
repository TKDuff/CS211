
public class DoubleHash {

	public static void main(String[] args) {
		Solution s1 = new Solution();
		Dictionary dict = new Dictionary();
		String[] words  = new String[9988];
		
		for(int i = 0; i < 9988; i++) {
			words[i] = dict.getWord(i);
		}
		
		words = s1.fill(16975, words);
		HashTable h1 = new HashTable(words);
		
		
		for(int i = 0; i < 9988; i++) {
			s1.find(16975, h1, dict.getWord(i));
		}
		
		System.out.println(h1.gettotal() + " collisions");

		
	}
}


class Solution {
	
	//only thing can do with hashtable object is call check
	public long find(int size, HashTable mytable, String word) {
		
		long hashVal = Primary(word, size);		//hashVal is long but you cast it to int down the code
		long stepSize = Secondary(hashVal);
		while(!mytable.check((int)hashVal, word))
				{
			hashVal += stepSize;	// add the step
			hashVal %= size;	// for wraparound
			}
		return 0;
	}
	
	public String[] fill(int size, String[] array) {
		String[] hashtable = new String[size];
		
		for(int i = 0; i < array.length; i++) {
			long hashVal = Primary(array[i], size);		//hashVal is long but you cast it to int down the code
			long stepSize = Secondary(hashVal);		
			
			while(hashtable[(int)hashVal] != null) 
			 {
				 hashVal += stepSize;
				 hashVal %= size;
				 }
			 
			hashtable[(int)hashVal] = array[i];
			 }
		return hashtable;
		}
		
	private static long Primary(String string, long size) {
		long hashVal = 0;
		
		for(int j=0; j<string.length(); j++)
		{
			int letter = string.charAt(j) - 96;
			hashVal = (hashVal * 27 + letter)% size;
			}
		return hashVal;
		}
	
	
	private long Secondary(long hashVal) {
		return 5 - (hashVal % 5);				//double hash uses 5 as prime, could change
		
	}


}

class HashTable{
	private String[] hashTable;
	private int total=0;
	
	public HashTable(String[] input) {
		hashTable = input;
	}
	
	public boolean check(int slot, String check) {
		if(hashTable[slot].equals(check)) {
			return true;
		} else {
			total++;
			return false;
		}
	}
	
	public int gettotal() {
		return total;
	}
} 

