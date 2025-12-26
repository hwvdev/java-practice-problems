package topJavaQues;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private static final Map<Integer, Node> hashmap;
    private final int capacity;
    private static final Node head;
    private static final Node tail;

    static {
        hashmap = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    private LRUCache(int cap) {
        this.capacity = cap;
    }

    public void put(int key, int val) {
        Node curr = hashmap.get(key);

        if (curr == null) {
            Node node = new Node(key, val);
            hashmap.put(key, node);
            insertAtHead(node);
            int size = hashmap.size();
            if (size > capacity) {
                Node delete = tail.prev;
                hashmap.remove(delete.key);
                deleteFromEnd();
            }
        } else {
            curr.val = val;
            Node next = curr.next;
            Node prev = curr.prev;
            prev.next = next;
            next.prev = prev;
            insertAtHead(curr);
        }
    }

    private void deleteFromEnd() {
        Node deleteNode = tail.prev;
        deleteNode.prev.next = tail;
        tail.prev = deleteNode.prev;
    }

    public int get(int key) {
        Node test = head.next;
        while (test!= tail) {
            System.out.print(test.key + " -> ");
            test=test.next;
        }
        System.out.println();


        Node curr = hashmap.get(key);
        if (curr == null) {
            System.out.println("key does not exists");
            return -1;
        }

        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;

        insertAtHead(curr);

        test = head.next;
        while (test!= tail) {
            System.out.print(test.key + " -> ");
            test=test.next;
        }
        System.out.println();

        return curr.val;
    }

    private void insertAtHead(Node node) {
        Node next = head.next;
        head.next = node;
        node.next = next;
        next.prev = node;
        node.prev = head;
    }

    private static final class Node {
        private final int key;
        private int val;
        private Node next;
        private Node prev;
        private Node(int k, int v) {
            this.key = k;
            this.val = v;
            this.prev = null;
            this.next = null;
        }
    }
}
