// Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

// Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

// Example 1:

// Given nums = [1,1,2],

// Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

// It doesn't matter what you leave beyond the returned length.



KEY INTUITION, the array is SORTED!! thats means duplicates will always be next to each other 

//TC: O(N)
//SC: O(1)
class Solution {
  public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int index = 0;
    for (int j = 1; j < nums.length; j++) {
        if (nums[j] != nums[index]) { //we have found unique elemnt 
            index++;
            nums[index] = nums[j];
        }
    }
    return index + 1;
}
}

//TC and SC same
class Solution {
    public int removeDuplicates(int[] nums) {
         if (nums.length == 0) return 0;
        int insertIndex = 1;
        for(int i=1; i<nums.length; i++){
            if(nums[i]!=nums[i-1]){
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        return insertIndex;
    }
}