package structure.linklist;

import lombok.Getter;
import lombok.Setter;

// 单向环形链表
//  Josephu (约瑟夫、约瑟夫环)问题为：
//     1.设编号为1，2，… n的n个人围坐一圈，约定编号为k（1<=k<=n）的人从1开始报数，数到m的那个人出列，
//     它的下一位又从1开始报数，数到m的那个人又出列，依次类推，直到所有人出列为止，由此产生一个出队编号的序列。
//     2.提示：用一个不带头结点的循环链表来处理Josephu 问题：
//          先构成一个有n个结点的单循环链表，然后由k结点起从1开始计数，计到m时，对应结点从链表中删除，然后再
//          从被删除结点的下一个结点又从1开始计数，直到最后一个结点从链表中删除算法结束。
//     3. n=5,即有五个人
//        k=1,从第编号为1的人报数(开始从1报数)
//        m=2,数2下
public class Josephu {
    public static void main(String[] args) {
        CycleSingleLinkList list = new CycleSingleLinkList();
        list.addBoy(5);
        list.showBoy();
        // 测试小孩出圈
        list.countBoy(1, 2, 5);//2->4->5->3
    }

}

class CycleSingleLinkList {
    // 创建一个first节点,当前没有编号
    private Boy first = new Boy(-1);

    // 构建一个单向环形链表的思路:
    //  1. 先创建第一个阶段,让fist指向该节点,并形成一个环形
    //  2. 后面每创建一个新的节点就把该节点,加入到已有的环形链表中
    // 添加小孩节点,构建成一个环形的链表
    public void addBoy(int nums) {
        // nums做一个数据校验
        if (nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null; // 辅助指针,帮助构建环形链表
        // 使用for循环创建我们的环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号创建小孩节点
            Boy boy = new Boy(i);
            if (i == 1) { // 创建第一个节点,自成环状
                first = boy;
                first.setNext(first); // 构成环状
                curBoy = first;
            } else {       // 如果不是第一次创建节点
                curBoy.setNext(boy);  // 新增节点
                boy.setNext(first);   // 形成环状
                curBoy = boy;         // 指针后移 //curBoy = curBoy.getNext();
            }
        }
    }


    // 遍历环形链表
    // 1.先让一个辅助指针curBoy,指向first节点
    // 2.然后通过一个while循环遍历该环形链表,curBoy.next == fist 结束
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空,没有任何小孩");
            return;
        }
        // 因为first不能动,因此我们仍然使用一个辅助指正完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号%d\n", curBoy.getNo());
            if (curBoy.getNext() == first) { // 说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入,计算出圈的顺序
     *
     * @param startNo  从第几个小孩开始数数 k=1,从第编号为1的人报数(开始从1报数)
     * @param countNum 表示数几下  m=2,数2下
     * @param nums     表示最初由几个小孩在圈内 n=5,即有五个人
     */
    public void countBoy(int startNo, int countNum, int nums) {
        // 先对数据进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("参数输入有误,请重新输入");
            return;
        }
        // 创建要一个辅助指正,帮助完成小孩出圈,helper的妙用就是,在first小孩出圈的时候,first后移一位,helper的节点的next域指向该first
        Boy helper = first;
        // 事先应该指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }
        // 小孩报数之前,先让first和helper移动k-1次 k-1
        for (int j = 0; j < startNo - 1; j++) { //从第几个孩子开始数
            first = first.getNext();
            helper = helper.getNext();
        }
        // 当小孩报数是,让first和helper指针同时的移动m-1次,然后出圈 [因为自己也要数一下,所以m-1]
        while (true) {
            if (helper == first) { // 说明圈中只有一个节点
                System.out.printf("最后留在圈中的小孩编号%d\n", helper.getNo());
                break;
            }
            // 让first 和helper指针同时移动countNum-1,
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            // 这时first指向的节点,就是出圈的节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
    }
}

@Getter
@Setter
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }
}
