Given an array w of positive integers, where w[i] describes the weight of index i, 
write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.  (KEY!!!)

BASICALLY, we have an array with weighted inputs

// This means binary search will be used alot, but the
// intial O(n) to make the prearray will only take O(N) one time!!


BRUTE FORCE:
// Generate an array with x occurences of each index, where x is the weight
// Won't be optimal because we will have to build a HUGE array



Optimized:

1. Create an array with accumlted frequency, last element arr[length-1] will contain total weight

2. Generate random number from (1, arr[length-1]) 

3. Binary search for random number

//TC: O(n) to build to initial array, O(logn) for each pick index call!
//SC: O(n) to store the array

class Solution {
    int[] prefix; 
    Random rand; 
    public Solution(int[] w) {
        rand = new Random(); 
        prefix=new int[w.length]; 
        prefix[0] = w[0];
        for(int i=1; i<w.length; i++){
            prefix[i] = prefix[i-1] + w[i];
        }
    }
    
    public int pickIndex() {
        int target = rand.nextInt(prefix[prefix.length-1]);
        int left =0;
        int right = prefix.length;
        while(left<right){
            int mid = left + (right-left)/2; 
            if(prefix[mid] > target){
                right=mid;
            } else{
                left=mid+1;
            }
        }
        return left; 
    }
}