// Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

// Your algorithm's runtime complexity must be in the order of O(log n).

// If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]


//KEY USE BINARY SEARCH MODIFYED, have two helper functions which search for 
//first and last appearance of the value 

THE MODIFCATION IS, even when we find our target value, we KEEP SEARCHING,
then we update our idx value with the leftmost or rightmost value of target,
at the end of the iteration, our idx will hold either rightmost/leftmost value of the target!!

In FindFirst, the idx move towards the lower index, and in FIndLast he makes it move towards 
the higher index. The loop doesn't  exist even if the target is found, it moves to one end, 
(left==right) and then exits. 


//O(logn)
//O(1) 


public class Solution {
public int[] searchRange(int[] nums, int target) {
    int[] result = new int[2];
    result[0] = findFirst(nums, target);
    result[1] = findLast(nums, target);
    return result;
}

private int findFirst(int[] nums, int target){
    int idx = -1; //keep track of first occurence 
    int start = 0;
    int end = nums.length - 1;
    while(start <= end){
        int mid = (start + end) / 2;
        if(nums[mid] >= target){
            end = mid - 1;
        }else{
            start = mid + 1;
        }
        if(nums[mid] == target) idx = mid;
    }
    return idx;
}

private int findLast(int[] nums, int target){
    int idx = -1; //keep track of laast occurence 
    int start = 0;
    int end = nums.length - 1;
    while(start <= end){
        int mid = (start + end) / 2;
        if(nums[mid] <= target){
            start = mid + 1;
        }else{
            end = mid - 1;
        }
        if(nums[mid] == target) idx = mid;
    }
    return idx;
}