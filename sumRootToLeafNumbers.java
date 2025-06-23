// You are given the root of a binary tree containing digits from 0 to 9 only.

// Each root-to-leaf path in the tree represents a number


DFS

TC: O(N)
SC: O(H) where H is height of tree
class Solution {
    int total =0 ;
    public int sumNumbers(TreeNode root) {
        helper(root, 0);
        return total; 
    }
    public void helper(TreeNode root, int curr){
        if(root == null) return; 
        curr=curr*10 + root.val; 
        if(root.left == null && root.right == null){
            total+=curr;
        }
        helper(root.left, curr);
        helper(root.right, curr);
    }
}

DFS NO GLOBAL

TC: O(N)
SC: O(H)

public int sumNumbers(TreeNode root) {
    return sumNumbers(root, 0);
}

int sumNumbers(TreeNode root, int sum) {
    if (root == null) return 0;
    
    sum = sum*10 + root.val;
    if (root.left == null && root.right == null)
        return sum;
    
    return sumNumbers(root.left, sum) + sumNumbers(root.right, sum);
}


BEST SOLUTION - hard to implement

Morris Traversal 

TC: O(N)
SC: O(1)