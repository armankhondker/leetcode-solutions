Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.


BRUTE FORCE - try every possible combination of numbers, record max, output it 
TC: O(n^2)
SC: O(1)

USE A HASHMAP TO GET KEEP TRACK OF last index of each digit 

//TC: O(N) every digit in the input will be conisdered one time 
//SC: O(1) additional space for char array will be at max 10 length since INT_MAX is 2147483647
//can also be considered O(N) depending on how big the integers will get 
class Solution {
    public int maximumSwap(int num) {
        char [] A = Integer.toString(num).toCharArray();
        int[] last = new int[10];
        
        for(int i=0; i<A.length; i++){
            last[A[i] - '0'] = i; //record last index of every number
        }s
        
        for(int i=0; i<A.length; i++){
            for(int d=9; d>A[i]-'0'; d--){
                if(last[d] > i){ //if there is a digit that is bigger, that occurs after this
                                //then we will swap with first one!!
                char tmp = A[i];
                A[i] = A[last[d]];
                A[last[d]] = tmp;
                return Integer.valueOf(new String(A));
                }
             
            }
        }
        
        return num;
    }
}

// we want to go right to left since we care about the most significant digits (to get biggest swap)
// we want rightmost largest digit of all numbers 
// we want to find first smaller number to the left of maxIdx (going from left aka most significant digit)
BEST SOLLUTION ONE PASS
TC: O(N)
SC: O(1)

class Solution {
    public int maximumSwap(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        int maxIdx = chars.length - 1;
        int n = chars.length;
        // larger: rightmost largest digit of all numbers
        // smaller: first smaller number to the left of maxIdx
        int larger = 0, smaller = 0;
        for (int i = n - 2; i >= 0; i --) {
            if (chars[i] > chars[maxIdx])
                maxIdx = i;
            else if (chars[i] < chars[maxIdx]) {
                smaller = i;
                larger = maxIdx;
            }            
        }
        char temp = chars[larger];
        chars[larger] = chars[smaller];
        chars[smaller] = temp;
        return Integer.valueOf(String.valueOf(chars));
    }
}