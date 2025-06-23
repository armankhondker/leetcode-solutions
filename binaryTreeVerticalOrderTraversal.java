// // Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

// If two nodes are in the same row and column, the order should be from left to right.

// Examples 1:

// Input: [3,9,20,null,null,15,7]

//    3
//   /\
//  /  \
//  9  20
//     /\
//    /  \
//   15   7 

// Output:

// [
//   [9],
//   [3,15],
//   [20],
//   [7]
// ]


//TC: O(n) since peforming BFS over all nodes in the tree
//SC: O(n) queue could grow max size of number of nodes in tree

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) return res; 
        
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        HashMap<Integer, List<Integer>> map = new HashMap<>();  // (col , list of nodes in col)
        
        int min=0;
        int max=0; 

        q.add(root);
        cols.add(0);
        
        while(!q.isEmpty())
        {
            TreeNode node = q.poll();
            int col = cols.poll();
            
            if(!map.containsKey(col))
            {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val); 
            
            if(node.left!=null)
            {
                q.add(node.left);
                cols.add(col-1);
                min=Math.min(min, col-1);
                
            }
            if(node.right!=null)
            {
                q.add(node.right);
                cols.add(col+1);
                max=Math.max(max, col+1); 
            }
            
        }
        
        for(int i=min; i<=max; i++)
        {
            res.add(map.get(i)); 
        }
        
        return res; 
    }
}

IF solution requires them to be sorted if in same level

//use temporary map to group ndoes by row, so we can sort before inserting into the map 

class Solution {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if(root==null) return res;
        Queue<TreeNode> qt = new LinkedList();
        Queue<Integer> qi = new LinkedList();

        int min=0, max=0;
        Map<Integer, List<Integer>> map = new HashMap();
        qt.add(root);
        qi.add(0);//not root.val
        while(!qt.isEmpty()){
            int size = qt.size();
            Map<Integer,List<Integer>> tmp = new HashMap(); //really just a row mapping
            for(int i=0;i<size;i++){
                TreeNode cur = qt.poll();
                int idx = qi.poll();
                if(!tmp.containsKey(idx)) tmp.put(idx, new ArrayList<Integer>());
                tmp.get(idx).add(cur.val);
                if(idx<min)  min = idx;
                if(idx>max)  max = idx;
                if(cur.left!=null){
                    qt.add(cur.left);
                    qi.add(idx-1);
                }
                if(cur.right!=null){
                    qt.add(cur.right);
                    qi.add(idx+1);
                } 
            }
            for(int key : tmp.keySet()){
                if(!map.containsKey(key)) map.put(key, new ArrayList<Integer>());
                List<Integer> list = tmp.get(key);   //sort by level 
                Collections.sort(list);
                map.get(key).addAll(list);
            }
            
        }
        for (int i=min; i<=max; i++){
            res.add(map.get(i));
        }
        return res;
    }
}