class MyHashMap {
    private final int CAPACITY = 1000; // A reasonable initial capacity
    private Node[] buckets;

    class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        buckets = new Node[CAPACITY];
    }

    public void put(int key, int value) {
        int index = key % CAPACITY;
        Node node = buckets[index];
        while (node != null) {
            if (node.key == key) {
                node.value = value; // Update value if the key already exists
                return;
            }
            node = node.next;
        }
        node = new Node(key, value);
        node.next = buckets[index];
        buckets[index] = node;
    }

    public int get(int key) {
        int index = key % CAPACITY;
        Node node = buckets[index];
        while (node != null) {
            if (node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        return -1; // Key not found
    }

    public void remove(int key) {
        int index = key % CAPACITY;
        Node prev = null;
        Node current = buckets[index];
        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }
}