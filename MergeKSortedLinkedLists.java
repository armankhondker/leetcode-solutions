// Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
// Example:

// Input:
// [
//   1->4->5,
//   1->3->4,
//   2->6
// ]
// Output: 1->1->2->3->4->4->5->6



// Brute force SOLUTION

// 1. go through all lists and put them into array
// 2. sort the arrays
// 3. creat the result and populate it from arrays
// TC: O(NlogN) time to sort the arrays , where N is total number of nodes 
// SC: O(N) for result list

Intution, can solve this by putting every Node into a minheap, and then remove every node, and then we will have
sorted list

MINHEAP SOLUTION

TC: O(NlogK) where k is the number of linked lists 
SC: O(N) space to store new linkedlist will all the nodes

class Solution {
   class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a,b)->a.val - b.val);
        
        ListNode dummy = new ListNode(-1); //dummy.next will be the head to returns
        ListNode curr = dummy; 
        
        for(ListNode x: lists){
            if(x!=null){
                minHeap.add(x); 
            }
        }
        
        while(!minHeap.isEmpty()){
            curr.next = minHeap.poll(); 
            curr = curr.next; 
            
            if(curr.next!=null){
                minHeap.add(curr.next);
            }
            
        }
        
        return dummy.next; 
    }
}
}


Merge lists one by one (use helper function two merge two sorted lists)

TC: O(kN) where k is number of LL and N is total number of nodes 
SC: O(1) 

public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode h = new ListNode(0);
    ListNode ans=h;
    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
            h.next = l1;
            h = h.next;
            l1 = l1.next;
        } else {
            h.next = l2;
            h = h.next;
            l2 = l2.next;
        }
    }
    if(l1==null){
        h.next=l2;
    }
    if(l2==null){
        h.next=l1;
    } 
    return ans.next;
}
public ListNode mergeKLists(ListNode[] lists) {
    if(lists.length==1){
        return lists[0];
    }
    if(lists.length==0){
        return null;
    }
    ListNode head = mergeTwoLists(lists[0],lists[1]);
    for (int i = 2; i < lists.length; i++) {
        head = mergeTwoLists(head,lists[i]);
    }
    return head;
}



Divide and Conquer 
- dont need to traverse most nodes many times repeatedly like last solution 
- split up work to merge list in pairs, then split that up again, and go until we only have two left to merge

TC: O(Nlogk)
SC :O(1)
// }
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int amount = lists.length;
        if(amount == 0) return null;
        int interval = 1;
        while (interval < amount) {
            for (int i = 0; i < amount - interval; i += interval * 2) {
                lists[i] = merge2Lists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0]; 
    }

    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode point = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                point.next = l1;
                l1 = l1.next;
            } else {
                point.next = l2;
                l2 = l1;
                l1 = point.next.next;
            }
            point = point.next;
        }
        if (l1 == null) {
            point.next = l2;
        } else {
            point.next = l1;
        }
        return head.next;
    }
}


//cleaner 
   public ListNode mergeKLists(ListNode[] lists) {
  return divideAndConquer(lists, 0, lists.length - 1);
}

private ListNode divideAndConquer(ListNode[] lists, int lo, int hi) {
    if (lists == null || lists.length == 0) {
    return null;
  }
  if (lo > hi) { // invalid
    return null;
  }
  if (lo == hi) { // size = 1
    return lists[lo];
  }
  int mid = lo + (hi - lo) / 2; // left-leaning
  ListNode left = divideAndConquer(lists, lo, mid);
  ListNode right = divideAndConquer(lists, mid + 1, hi);
  return merge2lists(left, right);  
}
 public ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode point = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                point.next = l1;
                l1 = l1.next;
            } else {
                point.next = l2;
                l2 = l1;
                l1 = point.next.next;
            }
            point = point.next;
        }
        if (l1 == null) {
            point.next = l2;
        } else {
            point.next = l1;
        }
        return head.next;
    }