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
