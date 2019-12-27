package structure.recursion;

// 递归的定义:
//     若一个对象部分第包含自己,或用它自己给自己定义,则称这个对象是递归的; 如链表,树是递归定义的
//     若一个过程直接或间接的调用自己,则称这个过程是递归的过程
//         如:递归求n的阶乘
// 一下三种情况常常用到递归方法
//   递归定义的数学函数: 如阶乘函数,斐波那契数列
//   具有递归特性的数据结构: 如链表,二叉树,广义表
//   可递归求解的问题: 如迷宫问题,走到这一步,下一步走哪里也是同一个办法. 汉诺塔问题

// 递归问题--用分治法求解
//   分治法:对于一个较为复杂的问题,能够分解成几个相对简单的且解法相同或类似的子问题来求解
//   必备的三个条件:
//      (1). 能将一个问题转变成一个新问题,而新问题与原问题的解法相同或类同,不同的仅是处理的对象,且这些
//           处理对象是变化有规律的.
//      (2). 可以通过上述转化而使用问题简化
//      (3). 必须有一个明确的递归出口,或称递归边界(不断产生新问题,总有一个新问题是能解决的,直接解决问题的叫基本项,使问题更小规模的叫归纳项)


import org.junit.Test;

// 函数调用过程
// 递归调用,进行参数传递,直到达到递归边界,结果返回,回归求值
// 控制权
public class Recursion {
    // 递归调用规则
    // 1.当程序执行到一个方法时,就会开辟一个独立的栈空间.存储局部变量,且方法之间的局部变量是独立的
    // 2.如果方法使用的是引用变量,则会共享该引用类型的数据
    // 3.递归必须向退出递归的条件逼近,否则会导致无限递归 StackOverflowError
    // 4.当一个方法执行完毕,或者遇到return,就会返回,遵守谁调用,就将结果返回给谁.同时当方法执行完毕或者返回时
    //   该方法也就执行完毕
    @Test
    public void recursion1() {
        test(4);
    }

    @Test
    public void recursion2() {
        test2(4);
    }

    @Test
    public void recursion3() {
        test3(4);
    }
    @Test
    public void recursion4() {
        test4(4);
    }
    // 条件不满足不调用递归法
    public void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);//2,3,4
    }

    public void test2(int n) {
        if (n > 2) { // 进入if就不进入else
            test2(n - 1);
        } else { // 在n<=2时才输出,而n<2时,已经结束递归
            System.out.println("n=" + n);//2
        }
    }


    // 条件满足不调用递归法
    public void test3(int n) {
        if (n == 2) {
            return;
        }
        System.out.println(n);
        test3(n - 1);
    }

    public int test4(int n) {
        if (n == 1) {
            return 1;
        } else {
            return test4(n = 1) * n;
        }
    }
}
