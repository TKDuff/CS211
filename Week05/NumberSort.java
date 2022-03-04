import java.util.ArrayList;
import java.util.Scanner;


class NumberSort{
	
	static int[] num = {8,16,10,13,12,11,15,17,14,9};//{90,81,100,98,99,102,86,97};
	static int[] col = {3,4,6,9,9,14,17,17,17,19};
	static int x = 9-1;						//minus one for indexing
	public static void main (String[] args){
			
		findSmallest();
    }
   
	static public void findSmallest() {
		int number = col[x];
		System.out.println("From position " + (x+1) +" the collatz number is currently " + number + " which is "+ num[x]);
		int start = 0; int end  = 0;
		
		for(int i = 0; i < num.length-1; i++) {
			if(col[i] != number && col[i+1] == number) {
				start = i+1;
			}else if(col[i] == number && col[i+1] != number){
				end = i;
			} 
		}
		System.out.println("The start is " + start + " and the end is " + end);
		if(start == end) {
			System.out.println("The number is " + num[x]);
		} else {
			System.out.println("Numbers " + num[start] + " - " + num[end] +" has to be sorted from the array...\n");
			for(int n: num)
				System.out.print(n + ",");
			col = num;
			insertionSort(start, end);
			System.out.println("\nAfter sorting");
			for(int n: col)
				System.out.print(n + ",");
			System.out.println("\nAt position " + (x+1) + " the number is therefore " + col[x]);
		}
		
	}
	
	static public int col(int n) {
		
		if(n == 1) {
			return 0;
		} else if(n%2 == 0) {
			return 1 + col(n/2);
		} else {
			return 1 + col(n*3 + 1);
		}
	}

	
	public static void insertionSort(int left, int right)
	{
	int in, out;
	// sorted on left of out
	for(out=left+1; out<=right; out++)
	{
	int temp1 = col[out];
	int temp2 = num[out];
	// remove marked item
	in = out;
	// start shifts at out
	// until one is smaller,
	while(in>left && col[in-1] >= temp1)
	{
	col[in] = col[in-1]; // shift item to right
	num[in] = num[in-1];
	--in;
	// go left one position
	}
	col[in] = temp1;
	num[in] = temp2;
	// insert marked item
	} // end for
	} // end insertionSort()
}


//90,81,100,98,99,102,86,97	
//17,22,25,25,25,25,30,118


//8,16,10,13,12,11,15,17,14,9
//3,4,6,9,9,14,17,17,17,19