/*
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
Implement the LRUCache class (https://en.wikipedia.org/wiki/Cache_replacement_policies#LRU):
	• LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
	• int get(int key) Return the value of the key if the key exists, otherwise return -1.
	• void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.
Example 1:
Input: 
 ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]Output: [null, null, null, 1, null, -1, null, -1, 3, 4]
Explanation: LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4
Constraints:
1 <= capacity <= 3000
*/

import java.util.Map;
import java.util.LinkedHashSet;

class LRUCache1 {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.head = new Node(-1, -1); // Dummy head node
        this.tail = new Node(-1, -1); // Dummy tail node
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            moveToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            if (cache.size() >= capacity) {
                removeTail();
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
        }
    }

    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeTail() {
        Node tailNode = tail.prev;
        cache.remove(tailNode.key);
        removeNode(tailNode);
    }
}

// Using Java in-built HashMap, LinkedHashSet

class LRUCache2 {
    private final int capacity;
    private final Map<Integer, Integer> cache;
    private final LinkedHashSet<Integer> lru;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>(capacity);
        this.lru = new LinkedHashSet<>(capacity);
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            lru.remove(key);
            lru.add(key);
            return cache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            lru.remove(key);
        } else if (lru.size() >= capacity) {
            int evictKey = lru.iterator().next();
            lru.remove(evictKey);
            cache.remove(evictKey);
        }
        cache.put(key, value);
        lru.add(key);
    }
}
