// There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

// The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions. Formally, a building has an ocean view if all the buildings to its right have a smaller height.

// Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.


//iterate from end, right to left
class Solution {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        if (n == 0) return new int[0]; // Return an empty array for empty input

        int maxSoFar = 0;
        List<Integer> res = new ArrayList<>();
        for(int i=n-1; i>=0; i--){
            if(heights[i]>maxSoFar) {
                res.add(i);
            }
            maxSoFar = Math.max(maxSoFar, heights[i]);
        }
        Collections.reverse(res);
        int[] result = new int[res.size()];
        for(int i=0; i<res.size(); i++){
            result[i]=res.get(i);
        }
        return result;
    }
}


//USE STACK if we have to iterate from left to right
class Solution {
    public int[] findBuildings(int[] heights) {
        Stack<Integer> s = new Stack<>();
        for(int i=0; i<heights.length; i++){
            while(!s.isEmpty() && heights[i]>=heights[s.peek()]){
                s.pop();
            }
            s.push(i);
        }
        int [] res = new int[s.size()];
        int i = res.length -1; 
        while(!s.isEmpty()){
            res[i] = s.pop();
            i--;
        }
        return res;

    }
}