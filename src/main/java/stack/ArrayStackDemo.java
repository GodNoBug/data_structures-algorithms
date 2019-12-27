package stack;

import javax.xml.crypto.Data;
import java.util.Scanner;

/**
 * 栈的英文为(stack) TODO 改成使用链表来模拟栈
 * 栈是一个先入后出(FILO-First In Last Out)的有序列表。
 * 栈(stack)是限制线性表中元素的插入和删除只能在线性表的同一端进行的一种特殊线性表。
 * 允许插入和删除的一端，为变化的一端，称为栈顶(Top)，另一端为固定的一端，称为栈底(Bottom)。
 * 根据栈的定义可知，最先放入栈中元素在栈底，最后放入的元素在栈顶，而删除元素刚好相反，最后放入
 * 的元素最先删除，最先放入的元素最后删除
 */
public class ArrayStackDemo {
    // 实现栈的思路分析:
    // 1.使用数组来模拟栈
    // 2.定义一个top来表示栈顶,初始化为-1
    // 3.入栈的操作.当有数据加入到栈时,top++;stack[top]=data;
    // 4.出栈的操作. int value = stack[top] ,return value;
    public static void main(String[] args) {
        ArrayStack stack =new ArrayStack(4);
        String key = "";
        boolean loop =true;
        Scanner scanner =new Scanner(System.in);

        while (loop){
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入您的选择");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n",res);
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出程序员=");
    }
}

// 定义栈
class ArrayStack {
    private int maxSize;    // 栈的大小
    private int[] stack;    // 数组,数组模拟栈,数据就放在该数组
    private int top = -1;   // top表示栈顶,初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    // 栈满
    public boolean isFull() {
        return this.top == this.maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return this.top == -1;
    }

    // 入栈
    public void push(int value) {
        if (this.isFull()) {
            System.out.println("栈满,不能再存数据");
            return;
        }
        this.top++;
        this.stack[top] = value;
    }
    // 出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空,不能再取数据");
        }
        int value = this.stack[top];
        this.top--;
        return value;
    }
    // 遍历栈,遍历需要从栈顶开始显示数据
    public void list(){
        if (isEmpty()){
            System.out.println("栈空,没有数据");
            return;
        }
        for (int i=top; i>0;i--){
            System.out.printf("stack[%d]=%d\n",i,this.stack[i]);
        }
    }
}
