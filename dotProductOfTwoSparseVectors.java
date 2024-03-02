// Given two sparse vectors, compute their dot product.

// Implement class SparseVector:

Optimal SOLUTION, store in a point, with {index, value}
TC: O(N) 
SC: O(L), where L is the number of non zero elements

FOLLOW UP, if only one of them is spare,
CAN DO BINARY SEARCH on 

class SparseVector {
    List<int []> pairs; //list of all nonzero points with mapping {index, array val}
    SparseVector(int[] nums) {
        pairs = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            if(nums[i]!=0){
                pairs.add(new int[]{i, nums[i]});
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int total = 0;
        int p = 0;
        int q = 0;
        while(p < pairs.size() && q <vec.pairs.size()){
            if(pairs.get(p)[0] == vec.pairs.get(q)[0]){
                total+= pairs.get(p)[1] * vec.pairs.get(q)[1]; 
                p++;
                q++;
            } else if (pairs.get(p)[0] > vec.pairs.get(q)[0]){
                q++; 
            } else {
                p++;
            }
        }
        return total;
    }
}


//MAP Approach, not optimal because hashing function could take extra time
//TC: O(N) , have to iterate on all elements
// SC: O(L), where L is the number of non zero elements

class SparseVector {
    int [] arr;
    Map<Integer, Integer> mapping;
    SparseVector(int[] nums) {
        mapping = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            mapping.put(i, nums[i]);
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int total = 0;
        for(Integer i: mapping.keySet()){
            if(vec.mapping.containsKey(i)){
                total += mapping.get(i) * vec.mapping.get(i);
            }
        }
        return total;
    }
}



NAIVE SOLUTION, just store in Array
TC: O(N)
SC: O(1) since we just save a reference of input array

class SparseVector {
    int [] arr;
    SparseVector(int[] nums) {
        arr = nums; 
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int total = 0;
        for(int i=0; i<arr.length; i++){
            total+= arr[i] * vec.arr[i];
        }
        return total;
    }
}