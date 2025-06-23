// You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.

// Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

// Return any permutation of s that satisfies this property.

// Input: order = "cba", s = "abcd"

// Output: "cbad"

// Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".


TC: O(N) where N is length of S, it will always be >= order length
SC: O(1) since can use counts map to store 

class Solution {
    public String customSortString(String order, String s) {
        int[] counts = new int[26];
        for(char c: s.toCharArray()){
            counts[c-'a']++;
        }
        StringBuilder res = new StringBuilder();
        for(char c: order.toCharArray()){
            int size = counts[c-'a'];
            for(int i=0; i<size; i++){
                res.append(c);
                counts[c-'a']--; 
            }
        }
        for(char c='a'; c<='z'; c++){
            if(counts[c-'a']>0){
                int size = counts[c-'a'];
                for(int i=0; i<size;i++){
                    res.append(c);
                }
            }
        }
        return res.toString();
    }
}

