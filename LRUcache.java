//need O(1) time for GET and PUT operations
//Use HashMap for O(1) lookup(GET)
//Use a DLL for O(1) removals/insertions(PUT) , also because WE NEED ORDERING , hashmap will have no ordering by itself!

class LRUCache {
    
 private class ListNode { //definition of Node needed for this problem 
    int key;
    int value;
    ListNode prev;
    ListNode next;
  }
    
    Map<Integer, ListNode> hm = new HashMap<>(); //give us O(1) access to get an item in dll 
    ListNode head;
    ListNode tail; 
    int totalItemsInCache; //# of items currently in the cahce
    int capacity; //items the cache can hold

    public LRUCache(int capacity) {
        
    // Cache starts empty and capacity is set by client
    totalItemsInCache = 0;
    this.capacity = capacity;
        
    // Dummy head and tail nodes to avoid empty states
    head = new ListNode();
    tail = new ListNode();
        
    // Wire the head and tail together
    head.next = tail;
    tail.prev = head;
        
    }
    
    public int get(int key) {
        
        ListNode node = hm.get(key); 
        if(node == null)
        { 
            return -1; 
        }
        
        moveToHead(node); // item has been accessed, move to front
        return node.value;
        
    }
    
    public void put(int key, int value) {
        ListNode node = hm.get(key);
        if(node == null)
        {
            ListNode newNode = new ListNode();
            newNode.key = key;
            newNode.value = value;

            // Add to the hashtable and the actual list that represents the cache
            hm.put(key, newNode);
            addToFront(newNode);
            totalItemsInCache++;

            // If over capacity remove the LRU item
            if (totalItemsInCache > capacity) {
            removeLRUEntry();
      }
        }
        else{
            node.value=value;
            moveToHead(node);
        }
        
    }
    
    private void removeLRUEntry() {
    ListNode tail = popTail();
    hashtable.remove(tail.key);
    --totalItemsInCache;
  }

  private ListNode popTail() {
    ListNode tailItem = tail.prev;
    removeFromList(tailItem);
    return tailItem;
  }

    private void addToFront(ListNode node) {
    // Wire up the new node being to be inserted
    node.prev = head;
    node.next = head.next;

    /*
      Re-wire the node after the head. Our node is still sitting "in the middle of nowhere".
      We got the new node pointing to the right things, but we need to fix up the original
      head & head's next.
      head <-> head.next <-> head.next.next <-> head.next.next.next <-> ...
      ^            ^
      |- new node -|
      That's where we are before these next 2 lines.
    */
    head.next.prev = node;
    head.next = node;
  }

    //just helder method to remove a node from linkedlist
    private void removeFromList(ListNode node) {
    ListNode savedPrev = node.prev;
    ListNode savedNext = node.next;

    savedPrev.next = savedNext;
    savedNext.prev = savedPrev;
  }

  private void moveToHead(ListNode node) {
    removeFromList(node);
    addToFront(node);
  }
}

//CLEANER

class LRUCache {
    class Node {
        Node prev;
        Node next;
        int key;
        int val; 
        public Node(int val, int key){
            this.val = val; 
            this.key = key;
        }
    }
    HashMap<Integer, Node> map; 
    Node head;
    Node tail; 
    int capacity;
    public LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1); 
        head.next = tail;
        tail.prev = head; 
        map = new HashMap<>();
        this.capacity = capacity; 
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Node curr = map.get(key);
            remove(curr);
            insert(curr);
            return curr.val;
        } else {
            return -1; 
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            remove(map.get(key));
        } 
        if(map.size() == capacity){
            remove(tail.prev); 
        }
        insert(new Node(value, key));
    }

        public void remove(Node node){
            map.remove(node.key);
            node.prev.next = node.next; 
            node.next.prev = node.prev;
        }
        public void insert(Node node){
            map.put(node.key, node); 
            Node next = head.next; 
            head.next = node;
            node.prev = head;
            next.prev = node;
            node.next = next; 
        }
    
}

