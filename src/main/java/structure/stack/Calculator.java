package structure.stack;

import org.junit.Test;

import java.util.Scanner;

/**
 * 对计算机而言,它收到的就是一个字符串
 * 栈实现综合计算器
 * <p>
 * 数栈:   存放数    numStack
 * 符号栈: 存放运算符 operStack
 * <p>
 * 使用栈完成表达式的计算的思路:
 * 1.通过一个index值(索引)来遍历我们的表达式
 * 2.如果我们发现index扫描到发现是一个数字,就直接入数栈
 * 3.如果发现扫描到是一个符号,就分如下情况
 * 3.1 如果发现当前符号栈为空,就直接入栈
 * 3.2 如果符号栈有操作符,就进行比较,如果当前的操作符的优先级小于或等于栈中的操作符,
 * 就需要从数栈中pop中两个数,再从符号栈中pop出一个符号,进行运算,将得到结果,
 * 入数栈然后将当前的操作符入符号栈
 * 3.3 如果当前的操作符优先级大于栈中的操作符,就直接入符号栈
 * 4. 当表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的数和符号并运算
 * 5. 最后数栈只有一个数字,就是表达式的结果
 */
public class Calculator { // 简单的中缀表达式
    @Test
    public  void test1() {
        // Scanner scanner = new Scanner(System.in);
        String expression = "70+2*6-4"; // 该数不能使用括号和两位数以上的计算
        // 创建两个栈,数栈,一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        // 定义需要的相关变量
        int index = 0; // 用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将每次扫描得到的char保存到ch
        String keepNum="";//用于拼接多位'数'
        // 开始while循环扫描expression
        while (true) {
            // 依次得到expression的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是什么,然后做相应的处理
            if (operStack.isOper(ch)) { // 如果是运算符
                if (!operStack.isEmpty()) { // 判断当前的符号是否为空,不为空
                    //如果符号栈有操作符,就进行比较,如果当前的操作符的优先级小于或等于栈中的操作符,
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //就需要从数栈中pop中两个数,再从符号栈中pop出一个符号,进行运算,将得到结果
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //得到结果,入数栈然后将当前的操作符入符号栈
                        numStack.push(res);
                        operStack.push(ch);
                    } else { // 如果当前的操作符优先级大于栈中的操作符,就直接入符号栈
                        operStack.push(ch);
                    }
                } else { // 符号栈为空就入符号栈
                    operStack.push(ch);
                }
            } else { // 如果是数字,char类型转int类型,就不是原来的数字.见码表
                // TODO 如果一发现是数字的话就入栈,那不能计算多位数了
                // 1.不能发现是一位数就立即入栈,因为它可能是多位数
                // 2.处理数,需要向表达式的index后再看一位,如果是数,就继续进行扫描,如果是符号才入栈
                // 3.因此我们需要定义一个变量字符串,用于拼接

                //处理多位数
                keepNum +=ch;

                // 如果ch已经是表达式的最后一位,就直接入栈
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    // 判断一下一个字符是否为数字,如果是数字,则继续扫描,如果是运算符则入栈
                    // 注意只是看后一位,不是index++
                    // 如果后一位是操作符则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        // 清空keepNum
                        keepNum = "";
                        //numStack.push(ch - 48);
                    }
                }
            }
            // 让index + 1 ,并判断是否扫描到表达式的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 当表达式扫描完毕,就顺序从数栈和符号栈pop出相应的数和符号,并运行
        while (true) {
            // 如果符号栈为空,则到最后结果,数栈中只有一个数字,即结果
            if (operStack.isEmpty()) {
                res = numStack.pop();
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式的结果为:%d", res);


    }
}

// 先创建一个栈,需要扩展一些功能
class ArrayStack2 {
    private int maxSize;    // 栈的大小
    private int[] stack;    // 数组,数组模拟栈,数据就放在该数组
    private int top = -1;   // top表示栈顶,初始化为-1

    public ArrayStack2(int maxSize) {
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
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,不能再取数据");
        }
        int value = this.stack[top];
        this.top--;
        return value;
    }

    // 查看栈顶数据,不弹出
    public int peek() {
        return this.stack[this.top];
    }


    // 遍历栈,遍历需要从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据");
            return;
        }
        for (int i = top; i > 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, this.stack[i]);
        }
    }

    // 返回运算符的优先级.优先级是程序来定的,优先级使用数字表示.
    // 数字越大,则优先级就越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前的表达式只含+,-,*,/
        }
    }

    // 判断是否是一个运算符
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算方法
    public int cal(int num1, int num2, int oper) {
        int res = 0;// 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;// 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num1 / num2;
                break;
            default:
                break;
        }
        return res;
    }
}
