Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).

Each node will have a reference to its parent node. The definition for Node is below:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}
According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants (where we allow a node to be a descendant of itself)."



Hashset of ancestors
//TC: O(N)
//SC: O(N)
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        HashSet<Node> ancestors = new HashSet<>();
        while(p!=null){
            ancestors.add(p); 
            p = p.parent; 
        }
        while(!ancestors.contains(q)){
            q=q.parent;
        }
        return q;
    }
}

Best solution, constant space 
Get heights of both, raise to same level, then set to next until they are equal
//TC: O(N)
//SC: O(1)
class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {
        
        int pDepth = depth(p);
        int qDepth = depth(q);
        while (pDepth > qDepth) {
            pDepth--;
            p = p.parent;
        }
        
        while (qDepth > pDepth) {
            qDepth--;
            q = q.parent;
        }

        while(p!=q){
            q=q.parent;
            p=p.parent;
        }

        return p; 
    }

    public int depth(Node root){
        if(root == null) return 0;
        int depth = 0;
        while(root!=null){
            depth++;
            root=root.parent; 
        }
        return depth; 
    }
}