package arithmetic.sort;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 * 基本思想是:通过对待排序序列从前向后(从小标较小的元素开始),依次比较相邻元素的值,
 * 若发现逆序则交换,使值较大的元素逐渐从前移向后部,(每趟不断比较相邻的两个数，将小数放
 * 在前面，大数放在后面。)就像水底下的气泡一样逐渐往上冒.
 * <p>
 * 原理：比较两个相邻的元素，将值大的元素交换至右端。
 * <p>
 * 优化:因为排序的过程中,各元素不断接近自己的位置,如果一趟比较下来没有进行过交换,就说明
 * 序列有序,因此要在排序过程中设置一个标志flag判断元素是否进行过交换.从而减少不比要的比较.
 * 时间复杂度O(n^2)
 *
 *
 * 优点:每趟结束时,不仅能挤出一个最大值到最后面位置,还能同时部分理顺其他元素
 */
public class BubbleSort {
    // 3, 9, -1, 10, 20 [原始数组]

    // 3, 9, -1, 10, 20 [第一趟排序1] 比较3和9
    // 3, -1, 9, 10, 20 [第一趟排序2] 比较-1和9
    // 3, -1, 9, 10, 20 [第一趟排序3] 比较9和10
    // 3, -1, 9, 10, 20 [第一趟排序4] 比较10和20 [最大的数就确定下来了20]

    // -1, 3, 9, 10, 20 [第二趟排序1] 比较3和-1
    // -1, 3, 9, 10, 20 [第二趟排序2] 比较3和9
    // -1, 3, 9, 10, 20 [第二趟排序3] 比较9和10 [最大的数就确定下来了20和10]

    // -1, 3, 9, 10, 20 [第三趟排序1] 比较-1和3
    // -1, 3, 9, 10, 20 [第三趟排序2] 比较3和9  [最大的数就确定下来了20和10和9]

    // -1, 3, 9, 10, 20 [第四趟排序1] 比较-1和3 [最大的数就确定下来了20和10和9和3,而-1就明显不用排]
    //

    // * 小结冒泡排序规则
    // * [1] 一共进行(数组的大小-1)次大的循环,即n个记录,总共需要n-1趟.
    //       第m趟+需要比较的次数 = n  所以,每趟需要比较多少次 = n-第m趟
    // * [2] 每趟排序的次数在逐渐的减少,每趟把未排序部分的最大的确定下来
    // * [3] 如果我们发现某趟排序中,没有发生一次交换,可以提前结束冒泡排序
    @Test
    public void test1() {
        int[] arr = {3, 9, -1, 10, -2};
        System.out.println("排序前(不算上趟数)");
        System.out.println(Arrays.toString(arr));
        //冒泡排序
        sort(arr);
        System.out.println("最终结果(不算上趟数)");
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test2() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);//生成一个[0~8000000)数
        }
        String begin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("排序前的时间是=" + begin);
        sort(arr);
        String end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("排序后的时间是=" + end);
    }

    // 冒泡排序的双重for循环的理解
    private void sort(int[] arr) {
        boolean flag = false;   //优化项: 标识变量,表示是否进行过交换
        int temp = 0;           //临时变量
        for (int i = 0; i < arr.length - 1; i++) {          // 总共需要[数组个数-1]趟
            for (int j = 0; j < arr.length - 1 - i; j++) {  //
                // 如果前面的数比后面的数大,则交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;  // 优化项:进入交换了,但凡一次交换了,就为true表示交换过,如果没有一次,那么为false
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // System.out.println("第" + (i + 1) + "趟排序后的数组");
            // System.out.println(Arrays.toString(arr));
            if (!flag) {//在一趟排序中,一次交换都没有过
                break;
            } else {
                flag = false;//重置flag,进行下次判断
            }

        }
    }
}
/**
 * <p>
 * 小结冒泡排序规则
 * [1] 一共进行(数组的大小-1)次大的循环
 * [2] 每趟排序的次数在逐渐的减少
 * [3] 如果我们发现某趟排序中,没有发生一次交换,可以提前结束冒泡排序
 * <p>
 * 模拟推导过程
 * //第一趟排序,就是将最大的数排在最后
 * int temp = 0;
 * for (int i = 0; i < arr.length - 1; i++) {
 * //如果前面打的数比后面的数大,则交换
 * if (arr[i] > arr[i + 1]) {
 * temp = arr[i];
 * arr[i] = arr[i + 1];
 * arr[i + 1] = temp;
 * }
 * }
 * System.out.println("第一趟排序就的数组");
 * System.out.println(Arrays.toString(arr));
 * <p>
 * //第二趟排序,就是将第二大的数排在倒数第二位
 * for (int i = 0; i < arr.length - 1 - 1; i++) {
 * //如果前面打的数比后面的数大,则交换
 * if (arr[i] > arr[i + 1]) {
 * temp = arr[i];
 * arr[i] = arr[i + 1];
 * arr[i + 1] = temp;
 * }
 * }
 * System.out.println("第二趟排序就的数组");
 * System.out.println(Arrays.toString(arr));
 * <p>
 * //第二趟排序,就是将第二大的数排在倒数第二位
 * for (int i = 0; i < arr.length - 1 - 1 - 1; i++) {
 * //如果前面打的数比后面的数大,则交换
 * if (arr[i] > arr[i + 1]) {
 * temp = arr[i];
 * arr[i] = arr[i + 1];
 * arr[i + 1] = temp;
 * }
 * }
 * System.out.println("第三趟排序就的数组");
 * System.out.println(Arrays.toString(arr));
 * <p>
 * //第二趟排序,就是将第二大的数排在倒数第二位
 * for (int i = 0; i < arr.length - 1 - 1 - 1 - 1; i++) {
 * //如果前面打的数比后面的数大,则交换
 * if (arr[i] > arr[i + 1]) {
 * temp = arr[i];
 * arr[i] = arr[i + 1];
 * arr[i + 1] = temp;
 * }
 * }
 * System.out.println("第四趟排序就的数组");
 * System.out.println(Arrays.toString(arr));
 * 最前一个数字并不需要排序
 */
