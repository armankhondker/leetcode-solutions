// Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

// According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined 
// between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself)

//Use partents hashmap to keep track of parent nodes
//THEN, use a hashset to find all ancestors of p,
//THEN, keep iterating on q until we find first common ancestor 

//TC: O(N) where n is number of nodes in tree, to go through all of the nodes in tree
//SC: O(N) to store all of the parents 

  class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
        HashMap<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>(); 
        Queue<TreeNode> queue = new LinkedList<>();
        
        parent.put(root,null); 
        queue.add(root); 
        
        while(!parent.containsKey(p) || !parent.containsKey(q))
        {
            TreeNode node = queue.poll();
            if(node.left != null)
            {
                parent.put(node.left,node);
                queue.add(node.left);
            }
            if(node.right != null)
            {
                parent.put(node.right, node);
                queue.add(node.right);
            }
        }T
        //FIND ALL ANCESTORS OF P, then we will move q up until we see first common ancestor 
        
        HashSet<TreeNode> ancestors = new HashSet<>(); //ancestors for p
        while(p!=null){
            ancestors.add(p);
            p = parent.get(p); 
        }
        
        //the first ancestor of q which appears in p's ancestors set 
        //will be lowest common ancestor
        while(!ancestors.contains(q)){
            q = parent.get(q);
        }
        return q; 
    }
}


Second alternative, get the depths of the two nodes, then raise to same level!!!

class Solution {
    public Node lowestCommonAncestor(Node p, Node q) {

        HashMap<TreeNode, TreeNode> parent = new HashMap<TreeNode, TreeNode>(); 
        Queue<TreeNode> queue = new LinkedList<>();
        
        parent.put(root,null); 
        queue.add(root); 
        
        while(!parent.containsKey(p) || !parent.containsKey(q))
        {
            TreeNode node = queue.poll();
            if(node.left != null)
            {
                parent.put(node.left,node);
                queue.add(node.left);
            }
            if(node.right != null)
            {
                parent.put(node.right, node);
                queue.add(node.right);
            }
        }
        
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
