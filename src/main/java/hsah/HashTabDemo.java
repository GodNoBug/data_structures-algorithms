package hsah;

import java.util.List;

public class HashTabDemo {
    private List<Integer>[] lists;

}
//哈希表,创建HashTab 管理多条链表
class HashTab{
    private EmpLinkList[] empLinkListArray;//链表数组

    //构造器
    public HashTab(int size) {
        this.empLinkListArray = new EmpLinkList[size];
    }
}

//表示一个雇员
class Emp {
    public int id;
    public String name;
    public Emp next;    //默认为空

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkList,表示链表
class EmpLinkList {
    //头指针,执行第一个Emp,因此我们这个链表的head是直接指向第一个Emp
    private Emp head; //默认null

    //添加雇员到列表
    //说明
    //1.假定,当添加雇员时,id是自增长的,即id的分配总是从小到大
    //    因此我们将雇员直接加入到本链表的最后即可
    public void add(Emp emp) {
        //如果是添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        //如果不是第一个雇员,则使用一个辅助的指针,帮助定位到最后
        Emp curEmp = head;
        while (curEmp.next != null) {//退出循环条件,到链表最后
            curEmp = curEmp.next; //指针后移
        }
        //退出循环时,直接将emp加入链表
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(){
        if (head==null){
            System.out.println("链表为空");
            return;
        }
        System.out.println("当前链表的信息为");
        Emp curEmp = head;//辅助指针
        while (curEmp.next != null) {//退出循环条件,到链表最后
            System.out.printf("=> id=%d name=%s\t",curEmp.id,curEmp.name);
            curEmp = curEmp.next; //指针后移
        }
        System.out.println();
    }
}
