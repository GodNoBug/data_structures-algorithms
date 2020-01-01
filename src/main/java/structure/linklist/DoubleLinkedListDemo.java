package structure.linklist;

import lombok.Getter;
import org.junit.Test;

//双向链表
// 单向链表的缺陷:
// 1.查找的方向只能是一个方向,而双向链表可以向前向后查找
// 2.单向链表不能自我删除,需要靠辅助接点,而双向链表则可以自我删除,
//   所以前面我们单向链表的删除节点.总是找到temp的下一个节点来删除的
//   temp是待删除节点的前一个节点
//
public class DoubleLinkedListDemo {
    @Test
    public  void test1() {
        System.out.println("双向链表测试");
        HeroNode2 node1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 node2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 node3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 node4 = new HeroNode2(4, "林冲", "豹子头");
        DoubleLinkedList list =new DoubleLinkedList();
        list.add(node1);
        list.add(node2);
        list.add(node3);
        list.add(node4);
        // 修改
        list.update(new HeroNode2(4, "公孙胜", "入云龙"));
        System.out.println("修改后的链表情况");
        list.list();

        // 删除
        list.delete(3);
        System.out.println("删除后 的情况");
        list.list();

    }
}

@Getter
class DoubleLinkedList {
    // 头结点,不存放具体的数据,作用就是表示单链表的头,不能动
    private HeroNode2 head = new HeroNode2(0, "", "");

    // 1.遍历
    //  遍历的方式和单链表一样,只是可以向前,也可以向后查找
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点,不能动,因此我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next; // 不输出head节点
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 2.添加(默认添加到双向链表的最后)
    //   1)先找到双向链表的最后一个节点
    //   2)temp.next = newNode
    //   3)newNode.pre = temp
    public void add(HeroNode2 node) {
        HeroNode2 temp = head; // 因为Head节点不能动,因此我们需要一个辅助遍历temp指针
        //遍历链表,找到链表的最后
        while (temp.next != null) {
            temp = temp.next;//如果没有找到最后,将temp后移
        }
        //当退出while循环时,temp就指向了链表的最后,将最后这个节点的next指向新的节点
        temp.next = node;
        node.pre = temp;
    }

    // 3.修改
    //   思路和原来的单向链表一样
    public void update(HeroNode2 newNode) {
        // 判断是否为空
        if (head.next == null) {
            System.out.println("链表为空,不能更新");
            return;
        }
        boolean flag = false;       // 是否找到节点
        HeroNode2 temp = head.next; // 先定义一个辅助变量
        while (true) {
            if (temp == null) {
                break; // 已经遍历完链表
            }
            if (temp.no == newNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        } else {
            System.out.printf("没有找到编号 %d 的节点,不能修改\n", newNode.no);
        }
    }

    // 4.删除
    //  1)因为是双向链表,因此,我们可以实现自我删除
    //  2)直接找到待删除节点(比如temp),而不需要找到待删除节点的前一个节点
    //  3)temp.pre.next=temp.next
    //  4)temp.next.pre=temp.pre
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空,无法删除");
            return;
        }
        HeroNode2 temp = head.next; // 指向当前节点,不包括head节点
        boolean flag = false;       // 是否找到删除节点的钱一个节点
        while (true) {
            if (temp == null) { // 已经到最后了
                break;
            }
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            if (temp.next != null) { // TODO 如果是最后一个节点就不需要执行这句话,否则会出现空指针异常
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("找不到要删除的节点,该节点%d\n", no);
        }
    }
    // TODO 按照编号顺序添加,按照单链表的顺序添加稍作修改
}


class HeroNode2 {
    public int no;             // 编号
    public String name;        // 名字
    public String nickName;    // 昵称
    public HeroNode2 next;     // 指向下一个节点的域,默认为null
    public HeroNode2 pre;      // 指向前一个节点,默认为null

    public HeroNode2(int no, String name, String nickName) {
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
