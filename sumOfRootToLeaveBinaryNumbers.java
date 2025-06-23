// Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number 
// starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then 
// this could represent 01101 in binary, which is 13.

// For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

// Return the sum of these numbers.

Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22





In conclusion:

2^n * nth bit + 2^n-1 * n-1th bit + ... + 2^0 * 0th bit

Will be equivalent to

[2 * (parentSumNode) + Current Bit]


TC: O(n)
SC: O(n)

class Solution {
    public int sumRootToLeaf(TreeNode root) {
        return pathSumRootToLeaf(root, 0);
    }
    
    private int pathSumRootToLeaf(TreeNode root, int parentNodeSum){
        if(root == null) return 0;
        
        parentNodeSum = 2 * parentNodeSum + root.val; 
        if(root.left == null && root.right == null){
            return parentNodeSum;
        }
        
        return pathSumRootToLeaf(root.left, parentNodeSum) + pathSumRootToLeaf(root.right, parentNodeSum);
    }
}


//Iterative, hard to implement

Use one stack to track the nodes and one stack to track the path sum up to the node, 
add the pathSum to the total when we reach the end of path (the left and right child of the node is empty).

public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stackT = new Stack<>();
        Stack<Integer> stackV = new Stack<>();
        stackT.push(root);
        stackV.push(root.val);
        int sum = 0;
        while (!stackT.isEmpty()) {
            TreeNode node = stackT.pop();
            Integer s = stackV.pop(); //path sum so far
            if (node.left != null) {
			//If left child is not empty, store left child and path sum up to left child
                stackT.push(node.left);
                stackV.push(s*2 + node.left.val);
            }
            
            if (node.right != null) {
			//If right child is not empty, store child child and path sum up to right child
                stackT.push(node.right);
                stackV.push(s*2+ node.right.val);
            }
            
            if (node.left == null && node.right == null) { 
			//If current node is at the end of the path with no children, add path sum to total sum
                sum += s;
            }
        }
        return sum;
    }