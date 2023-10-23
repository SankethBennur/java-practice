class BrowserHistory {
    class Node {
        String url;
        Node prev;
        Node next;

        Node(String url) {
            this.url = url;
            this.prev = null;
            this.next = null;
        }
    }

    private Node current;
    private int size;

    public BrowserHistory(String homepage) {
        current = new Node(homepage);
        size = 1;
    }

    public void visit(String url) {
        Node newNode = new Node(url);
        current.next = newNode;
        newNode.prev = current;
        current = newNode;
        size++;
    }

    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }

    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }
}
