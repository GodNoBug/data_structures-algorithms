class MyLinkedList {

    //节点:
    class Node{
        private int val;            //当前节点的值
        private Node next;          //指向下一个节点的指针/引用
        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    int size = 0 ;              //链表大小
    Node head =new Node(0); //头结点
    Node tail =head;            //头结点
    /**
     * 在此初始化您的数据结构。
     */
    public MyLinkedList() {

    }

    /**
     * 获取链表中第index个节点的值。如果索引无效，则返回-1。
     */
    public int get(int index) {
        if (index<0 || index >size){
            return -1;
        }
        Node curr =head;    //指针
        while (index>0){
            index--;
            curr=curr.next;
        }
        return curr.val;
    }

    public static void main(String[] args) {
        MyLinkedList list =new MyLinkedList();
        list.addAtHead(1);
        list.addAtHead(2);
        list.addAtHead(3);
        System.out.println(list);
        System.out.println(list.get(1));
    }

    /**
     * 在链接列表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
     */
    public void addAtHead(int val) {
//        size ++;
//        Node newNode = new Node(val);
//        newNode.next=this.head;
//        this.head =newNode;
        size++;
        Node node = new Node(val);
        node.next = head.next;
        head.next = node;
        if (tail == head) {//尾结点（只有一个节点的时候）
            tail = node;
        }
    }

    /**
     * 将值val的节点追加到链接列表的最后一个元素。
     */
    public void addAtTail(int val) {

    }

    /**
     *在链接列表的第index个节点之前添加一个值为val的节点。如果index等于链表的长度，
     * 则该节点将附加到链表的末尾。如果index大于长度，则不会插入该节点。
     */
    public void addAtIndex(int index, int val) {

    }

    /**
     * 如果索引有效，则删除链接列表中的第index个节点。
     */
    public void deleteAtIndex(int index) {

    }


    @Override
    public String toString() {
        return "MyLinkedList{" +
                "size=" + size +
                ", head=" + head +
                ", tail=" + tail +
                '}';
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
