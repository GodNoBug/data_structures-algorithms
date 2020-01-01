package structure.linklist;

import lombok.Getter;
import org.junit.Test;

import java.util.Stack;

//单链表
public class SingleLinkedListDemo {
    @Test
    public  void test1() {
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");
        //创建一个链表
        SingleLinkedList list = new SingleLinkedList();
        //加入节点 0 2 1 3 4
        list.addByOrder(node2);
        list.addByOrder(node1);
        list.addByOrder(node4);
        // 顺序添加测试
        // list.addByOrder(node4);
        list.addByOrder(node3);
        // 更新测试
        // list.update(new HeroNode(1,"张伟豪","豪哥"));
        // 删除测试
        // list.delete(1);
        // list.delete(4);
        // list.delete(2);
        // list.delete(3);
        // 有效节点测试
        // System.out.println("有效的节点个数  =" + getLength(list.getHead()));
        // 遍历测试
        list.list();
        // 倒数节点测试
        // HeroNode res = findLastIndex(list.getHead(),2);
        // System.out.println("res="+res);
        // System.out.println("反转");
        // 反转测试
        // reserve(list.getHead());
        // list.list();
        System.out.println("逆序输出-不改变原有顺序");
        reservePrintList2(list.getHead());

    }

    // TODO 合并两个有序的单链表,合并之后的链表依然有序
//    public static SingleLinkedList uniteList(HeroNode h1, HeroNode h2) {
//
//
//    }

    // TODO 从头打印单链表
    // 思路:
    // 1.上面的题的要求就是逆序打印单链表
    // 2.方式1:先将单链表进行反转操作,然后再遍历即可,这样做的问题是会破坏原来的顺序,不建议
    // 3.可以利用栈这个数据结构,将各个节点压入栈中,然后利用栈的先进后出的特点实现逆序
    public static void reservePrintList(HeroNode head) {
        if (head.next == null) {
            return;
        }
        HeroNode[] stack = new HeroNode[getLength(head)];
        HeroNode cur = head.next;
        for (int i = 0; i < stack.length; i++) {
            stack[i] = cur;
            cur = cur.next;
        }
        for (int i = stack.length - 1; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    public static void reservePrintList2(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }

    // TODO 单链表反转
    // 1.先定义一个节点reserveHead = new Head()
    // 2.从头到尾遍历原来的链表,每遍历一个节点,就将其取出(不是复制),并放在新的链表reverseHead的最前端.
    // 3.原来的链表的head.next = reserveHead.next
    // TODO 本质上是断开与原链表的head节点连接,剩下的一个一个存到新节点的最前端,然后原链表的head节点替换新节点的head节点
    // 图解见最后
    public static void reserve(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode reserveHead = new HeroNode(0, "", "");
        HeroNode cur = head.next; //定义一个辅助的指针,帮助遍历链表
        HeroNode next = null;  // 指向当前节点[cur]的下一个节点
        // 遍历原来的链表
        while (cur != null) {
            next = cur.next;                // 暂时保存当前节点的下一个节点,因为后面需要使用
            cur.next = reserveHead.next;    // 将cur的下一个节点指向新的链表的最前端
            reserveHead.next = cur;           // 新的链表的next指向
            cur = next;                     // 让cur后移
        }
        // 将head.next 指向reserveHead.next实现反转
        head.next = reserveHead.next;
    }


    // TODO 查找单链表中的倒数第k个节点
    // 思路如下
    // 1.编写一个方法,接收head节点,同时接收一个index
    // 2.index表示的是倒数第index个节点
    // 3.先把链表从头到尾遍历一下,统计出链表总长度getLength
    // 4.得到size后,我们从链表的第一个开始遍历(size-index)个
    // 5.如果找到了,则返回该节点,否则返回null
    public static HeroNode findLastIndex(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        // 第一次遍历得到链表的个数
        int size = getLength(head);
        // 第二次遍历 size - index 位置就是我们倒数第K个节点
        if (index < 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    /**
     * 获取单链表的有效节点的个数(如果的带头结点的链表,需要不统计头节点)
     *
     * @param head 链表的头节点
     * @return 返回的就是有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) { // 空链表
            return 0;
        }
        int length = 0;
        // 定义一个辅助的变量,这里我们没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}

// 定义一个SingleLinkedList管理我们的英雄
@Getter
class SingleLinkedList {
    // 头结点,不存放具体的数据,作用就是表示单链表的头,不能动
    private HeroNode head = new HeroNode(0, "", "");

    //TODO 创建思路(当不考虑编号的顺序时)
    // 1.先创建一个head头节点,作用就是表示单链表的头
    // 2.后面我们每添加一个节点,就直接加入到链表的最后,一定得想办法找到链表最后:
    public void add(HeroNode node) {
        HeroNode temp = head; // 因为Head节点不能动,因此我们需要一个辅助遍历temp指针
        //遍历链表,找到链表的最后
        while (temp.next != null) {
            temp = temp.next;//如果没有找到最后,将temp后移
        }
        //当退出while循环时,temp就指向了链表的最后,将最后这个节点的next指向新的节点
        temp.next = node;
    }

    // TODO 修改节点的消息,根据编号no来修改,即no编号不能修改.
    public void update(HeroNode newNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空,不能更新");
            return;
        }
        boolean flag = false;
        HeroNode temp = head.next; // 先定义一个辅助变量
        while (temp != null) { //一种是循环中修改,一种是循环后退出再修改
            if (temp.no == newNode.no) {
                flag = true;
                temp.name = newNode.name;
                temp.nickName = newNode.nickName;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("找到了,并修改");
        } else {
            System.out.println("找不到");
        }
    }

    // TODO 从单链表中删除一个节点的思路
    // 1.我们先找到需要删除的这个节点的钱一个节点temp
    // 2.temp.next = temp.next.next
    // 3.被删除的节点,将不会有其他引用指向,会被垃圾回收机制回收
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空,无法删除");
            return;
        }
        HeroNode temp = head; // 指向前一节点
        boolean flag = false; // 是否找到删除节点的钱一个节点
        while (true) {
            if (temp.next == null) { // 已经到最后了
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("找不到要删除的节点,该节点%d\n", no);
        }
    }


    //TODO 遍历思路
    //  1.通过一个辅助变量,帮助遍历整个单链表
    //  显示链表[遍历]
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点,不能动,因此我们需要一个辅助变量来遍历
        HeroNode temp = head.next; // 不输出head节点
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //  TODO 第二种方式在添加英雄时,根据排名将英雄插入到指定位置(如果有这个排名则添加失败,并给出提示)
    //  需要按照编号的顺序添加
    //  1. 首先找到新添加的节点的位置,是通过辅助变量(指针),通过遍历来搞定 temp是位于添加位置的前一个节点
    //  2. 新的节点.next=temp.next
    //  3. temp.next = 新的节点
    public void addByOrder(HeroNode node) {
        // 因为头节点不能动,因此我们仍然通过一个辅助指针来找到添加的位置,需要找到添加位置的前一个节点才有加的可能
        // 因为是单链表,因为我们找的temp是位于添加位置的前一个节点,否则插入不了
        HeroNode temp = head;
        boolean flag = false; // 标志添加的编号是否已经存在,默认为false
        while (true) {
            if (temp.next == null) { // 说明temp已经在列表的最后
                break;
            }
            if (node.no < temp.next.no) {
                break;
            } else if (temp.next.no == node.no) {// 说明希望添加的hero编号已然存在
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 判断flag值
        if (flag) {
            System.out.printf("准备插入的英雄编号%d 已经存在了,不能加入\n", node.no);
        } else {
            node.next = temp.next;
            temp.next = node;
        }
    }


}

//定义HeroNode,每个HeroNode对象就是一个节点
class HeroNode {
    public int no;             //编号
    public String name;        //名字
    public String nickName;    //昵称
    public HeroNode next;      //指向下一个节点的域

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
// head ->1->2->3->4->5
//       指1 → 指2
//       ↓     ↓
// head [1] -> 2->3->4->5
//      指1→指2
//      ↓   ↓
// head 2->3->4->5
//   / 1 \
// res -> null
//   / 2 \
// res -> 1 ->null
//最终:           head \
// head 替换了res的地位  res->5->4->3->2->1->1
