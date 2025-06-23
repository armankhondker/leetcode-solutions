You are given an inclusive range [lower, upper] and a sorted unique integer array num.

A number x is considered missing if x is in the range [lower, upper] and x is not in nums.

Return the shortest sorted list of ranges that exactly covers all the missing numbers. 

Example 1:

Input: nums = [0,1,3,50,75], lower = 0, upper = 99
Output: [[2,2],[4,49],[51,74],[76,99]]
Explanation: The ranges are:
[2,2]
[4,49]
[51,74]
[76,99]

class Solution {
    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        int n = nums.length;
        List<List<Integer>> missingRanges = new ArrayList<>();

        //Empty array, just return the ranges 
        if (n == 0) {
            missingRanges.add(Arrays.asList(lower, upper));
            return missingRanges;
        }
        // Check for any missing numbers between the lower bound and nums[0].
        if (lower < nums[0]) {
            missingRanges.add(Arrays.asList(lower, nums[0] - 1));
        }

        // Check for any missing numbers between successive elements of nums.
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] <= 1) {
                continue;
            }
            missingRanges.add(Arrays.asList(nums[i] + 1, nums[i + 1] - 1));
        }
        
        // Check for any missing numbers between the last element of nums and the upper bound.
        if (upper > nums[n - 1]) {
            missingRanges.add(Arrays.asList(nums[n - 1] + 1, upper));
        }

        return missingRanges;
    }
}
