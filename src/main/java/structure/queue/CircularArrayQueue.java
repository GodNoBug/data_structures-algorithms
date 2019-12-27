package structure.queue;

import java.util.Scanner;

/**
 *   循环队列就是将队列存储空间的最后一个位置绕到第一个位置，形成逻辑上的环状空间，供队列循环使用。
 *   在循环队列结构中，当存储空间的最后一个位置已被使用而再要进入队运算时，只需要存储空间的第一个位置空闲，
 * 便可将元素加入到第一个位置，即将存储空间的第一个位置作为队尾。  循环队列可以更简单防止伪溢出的发生，
 * 但队列大小是固定的。
 *   在循环队列中，当队列为空时，有front=rear，而当所有队列空间全占满时，也有front=rear。
 * 为了区别这两种情况，规定循环队列最多只能有MaxSize-1个队列元素，当循环队列中只剩下一个空存储单元时，队列就已经满了。
 * 因此，队列判空的条件是front=rear，而队列判满的条件是front=（rear+1)%MaxSize。
 *
 * 条件处理:
 *    循环队列中，由于入队时尾指针向前追赶头指针；出队时头指针向前追赶尾指针，造成队空和队满时头尾指针均相等。因此，无法通过条件front==rear来判别队列是"空"还是"满"。
 * 解决这个问题的方法至少有两种：
 *  ① 另设一布尔变量以区别队列的空和满；
 *  ②另一种方式就是数据结构常用的：
 *      队满时：(rear+1)%n==front，n为队列长度（所用数组大小），
 *      由于rear，front均为所用空间的指针，循环只是逻辑上的循环，所以需要求余运算。
 *
 * 类型定义采用环状模型来实现队列,各数据成员的意义如下：
 * front指定队首位置，删除一个元素就将front顺时针移动一位；
 * rear指向元素要插入的位置，插入一个元素就将rear顺时针移动一位；
 * count存放队列中元素的个数，当count等于MaxQSize时，不可再向队列中插入元素。
 *   作业:
 *      同学们完成环形数组模拟的队列的输出
 *      (cq.rear + cq.maxSize – cq.front) % cq.maxSize
 */
public class CircularArrayQueue {
    public static void main(String[] args) {
        System.out.println("");
        CircularArray arrayQueue = new CircularArray(4);//有效数据最多为3
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class CircularArray {
    private int[] arr; //该数组用于存储数据,模拟队列
    private int maxSize;    // 表示数组最大容量
    private int front;      // 队列头,数组下标,含义做调整.front就指向队列的第一个元素,也就是说arr[front]就是队列的第一个元素,front的初始值为0
    private int rear;       // 队列尾,数组下标,含义做调整rear指向队列的最后一个元素的后一个位置,因为希望空出一个空间作为约定

    public CircularArray(int arrSize) {
        this.arr = new int[arrSize];
        this.maxSize = arrSize;
    }

    // 判断队列是否满
    public boolean isFull() {
        return (this.rear + 1) % this.maxSize == this.front;
    }

    // 判断对象已经空
    public boolean isEmpty() {
        return this.rear == this.front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断是否满了
        if (isFull()) {
            System.out.println("队列满,不能加入数据");
            return;
        }
        // 直接将数据加入
        arr[rear] = n;
        // 将rear后移,要考虑取模
        rear = (rear + 1) % maxSize;
    }

    // 数据出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取");
        }
        // 这里需要分析出front指向队列的第一个元素
        // 1.先把front对应的值保存到临时变量中
        // 2.将front后移
        // 3.将临时保存的变量返回
        int value = arr[front];
        this.front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,没有数据");
        }
        // 思路:从front开始遍历,变量多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    // 求出当前队列有效数据个数
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据,而非取出数据
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[front];
    }

}

