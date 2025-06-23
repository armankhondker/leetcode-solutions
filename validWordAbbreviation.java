A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.

For example, a string such as "substitution" could be abbreviated as (but not limited to):



Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.

"s10n" ("s ubstitutio n")
"sub4u4" ("sub stit u tion")
"12" ("substitution")
"su3i1u2on" ("su bst i t u ti on")
"substitution" (no substrings replaced)

The following are not valid abbreviations:

"s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
"s010n" (has leading zeros)
"s0ubstitution" (replaces an empty substring)




class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0;
        int j = 0;
        while(i<word.length() && j<abbr.length()){
            char c = word.charAt(i);
            if(c == abbr.charAt(j)){
                i++;
                j++;
                continue;
            } 
            if(!Character.isDigit(abbr.charAt(j)) || abbr.charAt(j) == '0'){
                return false;}
            int skip = 0;
            while(j<abbr.length() && Character.isDigit(abbr.charAt(j))){
                    skip=skip*10 + abbr.charAt(j) - '0'; 
                    j++;
            }
            i+=skip;
        }
        
        return i == word.length() && j == abbr.length(); 
    }

}