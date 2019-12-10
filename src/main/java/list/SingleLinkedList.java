package list;

//单链表
public class SingleLinkedList {
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");
        //创建一个链表
        SingleLinkedList list = new SingleLinkedList();
        //加入节点 0 2 1 3 4
        list.addByOrder(node2);
        list.addByOrder(node1);
        list.addByOrder(node3);
        list.addByOrder(node4);
        list.list();
        System.out.println(list);

    }

    //定义HeroNode,每个HeroNode对象就是一个节点
    static class HeroNode {
        private int no;         //编号
        private String name;    //名字
        private String nickName;//昵称
        private HeroNode next;  //指向下一个节点的域

        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        @Override//方便展示,非核心代码
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName +
                    '}';
        }
    }

    //头结点,存放具体的数据,作用就是表示单链表的头,不能动
    private HeroNode head = new HeroNode(0, "", "");

    //创建思路
    // 1.先创建一个head头节点,作用就是表示单链表的头
    // 2.后面我们每添加一个节点,就直接加入到链表的最后
    // 一定得想办法找到链表最后:
    public void add(HeroNode node) {
        //因为Head节点不能动,因此我们需要一个辅助遍历temp指针
        HeroNode temp = head;
        //遍历链表,找到链表的最后
        while (temp.next != null) {
            temp = temp.next;//如果没有找到最后,将temp后移
        }
//        while (true){
//            //找到链表的最后
//            if (temp.next == null){
//                break;
//            }
//            temp=temp.next;
//        }
        //当退出while循环时,temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = node;
    }

    // 第二种方式在添加英雄时,根据排名将英雄插入到指定位置(如果有这个排名则添加失败,并给出提示)
    // 需要按照编号的顺序添加
    // 1. 首先找到新添加的节点的位置,是通过辅助变量(指针),通过遍历来搞定 temp是位于添加位置的前一个节点
    // 2. 新的节点.next=temp.next
    // 3. temp.next = 新的节点
    public void addByOrder(HeroNode node) {
        //因为头节点不能动,因此我们仍然通过一个辅助指针来找到添加的位置,需要找到添加位置的前一个节点才有加的可能
        //因为是单链表,因为我们找的temp是位于添加位置的前一个节点,否则插入不了
        HeroNode temp = head;

        if (temp.next == null) {
            head.next = node;
            return;
        }
        // 0
        // 0 2 null
        // 0 [1] 2 null
        // 0 [1] 2 3 null
        // 0 [1] 2 3 4 null
        while (temp.next != null) {
            if (node.no > temp.no && node.no < temp.next.no) {
                node.next = temp.next;
                temp.next = node;
            } else if (node.no == temp.no) {
                System.out.println("此排行已经存在");
                return;
            }
            temp = temp.next;
        }


    }

    //遍历思路
    //  1.通过一个辅助变量,帮助遍历整个单链表
    // 显示链表[遍历]
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点,不能动,因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while (temp.next != null) {
            System.out.println(temp);
            temp = temp.next;
        }

    }
}
