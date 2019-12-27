package structure.queue;

import lombok.AllArgsConstructor;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
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
                        int res =arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop =false;
                    break;
            }
        }
        System.out.println("程序退出");

    }
}

// 使用数组模拟队列
class ArrayQueue {
    private int[] arr; //该数组用于存储数据,模拟队列
    private int maxSize;    // 表示数组最大容量
    private int front;      // 队列头,数组下标指向队列的前一个位置
    private int rear;       // 队列尾,数组下标指向队列尾部

    public ArrayQueue(int maxSize) {
        this.arr = new int[maxSize];
        this.maxSize = maxSize;
        this.front = -1;    // 指向队列头部前一个位置
        this.rear = -1;     // 指向队列尾部(就是队列最后一个数据)
    }

    // 判断队列是否满
    public boolean isFull() {
        return this.rear == maxSize - 1;
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
        rear++; // 让rear后移
        arr[rear] = n;
    }

    // 数据出队列
    public int getQueue() {
        // 判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取");
        }
        this.front++;
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,没有数据");
        }
        for (int i = 0; i < this.arr.length; i++) {
            System.out.printf("arr[%d]%d\n", i, arr[i]);
        }
    }

    // 显示队列的头数据,而非取出数据
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[front + 1];
    }
}
