import java.util.HashMap;

class LRUCache {
    private int capacity;
    private Node head;
    private Node tail;
    private HashMap<Integer, Node> map = new HashMap<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        } else {
            remove(node);
            addToFront(node);
        }
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.val = value;
            if (capacity > map.size()) {
                addToFront(newNode);
                map.put(key, newNode);
            } else {
                Node oldTail = tail.prev;
                remove(oldTail);
                map.remove(oldTail.key);
                addToFront(newNode);
                map.put(key, newNode);
            }
        } else {
            remove(node);
            node.val = value;
            addToFront(node);
        }
    }

    public void remove(Node node) {
        Node next_node = node.next;
        Node prev_node = node.prev;

        prev_node.next = next_node;
        next_node.prev = prev_node;
    }

    public void addToFront(Node node) {
        Node next_node = head.next;
        node.next = next_node;
        next_node.prev = node;
        node.prev = head;
        head.next = node;
    }

    public class Node {
        public Node next = null;
        public Node prev  = null;
        public int key;
        public int val;
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
    }
}
