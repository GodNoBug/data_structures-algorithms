package leetcode;

class MyLinkedList {

    class Node {
        int val;
        Node next;
        Node(int x) {
            this.val = x;
        }
    }

    int size = 0;
    private Node head = new Node(0);//头节点，
    private Node tail = head;//尾结点

    /**
     * Initialize your data structure here.
     */

    public MyLinkedList() {

    }


    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node curr = head;
        while (index >= 0) {
            index--;
            curr = curr.next;
        }
        return curr.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        size++; //加就该改大小
        Node node = new Node(val);
        node.next = head.next;
        head.next = node;
        if (tail == head) {//尾结点（只有一个节点的时候）
            tail = node;
        }
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        size++;
        Node node = new Node(val);
        tail.next = node;
        tail = tail.next;


    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        size++;
        Node node = new Node(val);
        if (index == size - 1) {
            tail.next = node;
            tail = tail.next;
            return;
        }
        Node curr = head;
        while (index > 0) {
            index--;
            curr = curr.next;
        }
        node.next = curr.next;
        curr.next = node;

    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        Node curr = head;
        while (index > 0) {
            index--;
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next;
        }
        if (curr.next == null) {
            tail = curr;
        }

    }
}
