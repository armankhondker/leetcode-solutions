// You are given a string s.

// Your task is to remove all digits by doing this operation repeatedly:

// Delete the first digit and the closest non-digit character to its left.
// Return the resulting string after removing all digits.

// Note that the operation cannot be performed on a digit that does not have any non-digit character to its left.


TC: O(N)
SC: O(1)

class Solution {
    public String clearDigits(String s) {
        StringBuilder res = new StringBuilder();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                res.deleteCharAt(res.length()-1);
            } else{
                res.append(c);
            }
        }
        return res.toString();
    }
}