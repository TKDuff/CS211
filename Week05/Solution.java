import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    static int[]numberPos;
    static int[]coll;
    static int nElems;
    static int start;
    static int end;
    static int x;
    
    public static void main(String args[] ) throws Exception {
        Scanner in = new Scanner(System.in);
        
        start = in.nextInt();
        end = in.nextInt();
        x = in.nextInt()-1;
        nElems = end-start+1;
        numberPos = new int[nElems];
        coll = new int[nElems];
        
        for(int i = 0; i < nElems; i++) {
            numberPos[i] = start+i;
            coll[i] = col(start+i);
            
        } 
        
        recQuickSort(0, nElems-1);
        findSmallest();
    }
    
    static public void findSmallest() {
        int number = coll[x];
        //System.out.println("From position " + (x+1) +" the collatz number is currently " + number + " which is "+ numberPos[x]);
        int start = 0; int end  = 0;
        
        for(int i = 0; i < numberPos.length-1; i++) {
            if(coll[i] != number && coll[i+1] == number) {
                start = i+1;
            }else if(coll[i] == number && coll[i+1] != number){
                end = i;
            } else if( coll[i]==number && coll[i+1] == number && (start+1)==numberPos.length-1) {
                end = i+1;    
               // System.out.println(coll[start] + " " + coll[end]);
                
            }
        }
        //System.out.println("The start is " + start + " and the end is " + end);
        if(start == end) {
            System.out.println(numberPos[x]);
        } else {
            //System.out.println("Numbers " + numberPos[start] + " - " + numberPos[end] +" has to be sorted from the array...\n");
            //for(int n: numberPos)
                //System.out.print(n + ",");
            
            coll = numberPos;
            insertionSort(start, end);
            
            //System.out.println("\nAfter sorting");
            //for(int n: coll)
                //System.out.print(n + ",");
            System.out.println(coll[x]);
        }
        
    }
    
    public static void recQuickSort(int left, int right)
    {
    int size = right-left+1;
    if(size < 10)    // insertion sort if small
    insertionSort(left, right);
    else    // quicksort if large
    {
    long median = medianOf3(left, right);
    int partition = partitionIt(left, right, median);
    recQuickSort(left, partition-1);
    recQuickSort(partition+1, right);
    }
    } // end recQuickSort(
        
    public static long medianOf3(int left, int right)
    {
    int center = (left+right)/2;    // order left & center
    if( coll[left] > coll[center] )
    swap(left, center);        
                    // order left & right
    if( coll[left] > coll[right] )
    swap(left, right);        
                    // order center & right
    if( coll[center] > coll[right] )
    swap(center, right);
    
    swap(center, right-1);// put pivot on right
    return coll[right-1];    // return median value
    } // end medianOf3()  
    
    public static void swap(int dex1, int dex2) // swap two elements
    {
    int temp = coll[dex1];    // A into temp
    coll[dex1] = coll[dex2];// B into A
    coll[dex2] = temp;        // temp into B
    
    temp = numberPos[dex1];    // A into temp
    numberPos[dex1] = numberPos[dex2];// B into A
    numberPos[dex2] = temp;        // temp into B
    }
    
    public static int partitionIt(int left, int right, long pivot)
    {
    int leftPtr = left;        // right of first elem
    int rightPtr = right - 1;    // left of pivot
    while(true)
    {
    while( coll[++leftPtr] < pivot ) // find bigger
    ;                                    // (nop)
    
    while( coll[--rightPtr] > pivot ) // find smaller
    ;                                        // (nop)
    if(leftPtr >= rightPtr)        // if pointers cross,
        break;                    //partition done
    else                    // not crossed, so
        swap(leftPtr, rightPtr); // swap elements
    } // end while(true)
    swap(leftPtr, right-1);        // restore pivot
    return leftPtr;        // return pivot location
    } // end partitionIt()  
    
    public static void insertionSort(int left, int right)
    {
    int in, out;
    // sorted on left of out
    for(out=left+1; out<=right; out++)
    {
    int temp1 = coll[out];
    int temp2 = numberPos[out];
    // remove marked item
    in = out;
    // start shifts at out
    // until one is smaller,
    while(in>left && coll[in-1] >= temp1)
    {
    coll[in] = coll[in-1]; // shift item to right
    numberPos[in] = numberPos[in-1];
    --in;
    // go left one position
    }
    coll[in] = temp1;
    numberPos[in] = temp2;
    // insert marked item
    } // end for
    } // end insertionSort()
    
    static public int col(int n) {
        
        if(n == 1) {
            return 0;
        } else if(n%2 == 0) {
            return 1 + col(n/2);
        } else {
            return 1 + col(n*3 + 1);
        }
    }
      
}