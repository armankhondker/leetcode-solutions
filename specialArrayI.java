An array is considered special if the parity of every pair of adjacent elements is different. In other words, one element in each pair must be even, and the other must be odd.

You are given an array of integers nums. Return true if nums is a special array, otherwise, return false.

TC: O(N)
SC: O(1)

//Just use moduluo comparator
class Solution {
    public boolean isArraySpecial(int[] nums) {
        if(nums.length == 1 || nums.length == 0) return true; 
        for(int i=1; i<nums.length; i++){
            if((nums[i]%2 == 1 && nums[i-1]%2 == 1) || (nums[i]%2 == 0 && nums[i-1]%2 == 0)) return false; 
        }
        return true; 
    }
}