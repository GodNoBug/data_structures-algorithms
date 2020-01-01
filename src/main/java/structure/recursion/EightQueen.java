package structure.recursion;

import org.junit.Test;

/**
 * 八皇后问题介绍
 *      八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔于1848年提出：
 *      在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，即：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 * 思路分析
 * 1.第一个皇后先放第一行第一列
 * 2.第二个皇后放在第二行第一列、然后判断是否OK， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 3.继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4.当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 5.然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
 * <p>
 * TODO 说明：理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题.
 *  arr = {0 , 4, 7, 5, 2, 6, 1, 3}对应arr 下标 表示第几行，即第几个皇后，arr[i] = val ,
 *  val 表示第i+1个皇后，放在第i+1行的第val+1列
 */
public class EightQueen {
    private int max = 8;                    //共有多少个皇后
    private int[] array = new int[max];     //保存八皇后位置的结果,比如{0 , 4, 7, 5, 2, 6, 1, 3}
    private static int count =0;

    @Test
    public  void test1() {
        EightQueen queen =new EightQueen();
        queen.check();
        System.out.println("一共有多少种解法:"+count);
    }

    public void check(){
        this.check(0);
    }

    //编写一个方法,放置第n个皇后 TODO,check是每一次递归时,都有一个for循环
    private void check(int n){
        if (n == max){ //n=8,八个皇后就已经放好了
            print();
            return;
        }
        //遍历8列
        for (int i = 0; i < max; i++) {
            //先把当前皇后n放到该行的第1列
            array[n] = i;
            if (judge(n)){//判断当前放置第n个皇后到i列时,是否冲突
                //不冲突 接着放n+1个皇后,开始递归
                check(n+1);
            }
            //如果冲突,就继续执行 array[n] = i,即将array[n],放置在本行的后移的一个位置
        }
    }
    // 数组下标是行数  arr[n]-arr[i] 的绝对值 == n-i的绝对值
    //   0 1 2 3 4 5 6 7
    // 0|*   *
    // 1|  *
    // 2|
    // 3|
    // 4|
    // 5|
    // 6|
    // 7|
    /**
     * TODO 每放置皇后后判断是否冲突
     * 查看当我们放置第n个皇后,就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第n个皇后
     * @return 返回是否通过
     */
    public boolean judge(int n){
        for (int i = 0; i < n; i++) { // 和前面安置好的比较
            //如果是同一列或者在其对角线上(也就是正方形对角线,证明垂直距离和水平距离相等),没必要判断是否在同一行上,因为n每次都在递增
            //Math.abs(n-i) == Math.abs(array[n]-array[i]
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;//冲突校验通过,证明可以
    }
    //将最后结果数组打印[八皇后位置]出来 [简单] 每调用一次输出一种解法
    private void print() {
        count++;
        for (int i = 0; i < this.array.length; i++) {
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

}
// 回溯算法的思考
