package arithmetic.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 基本思想是:通过对待排序序列从前向后(从小标较小的元素开始),依次比较相邻元素的值,
 * 若发现逆序则交换,使值较大的元素逐渐从前移向后部,就像水底下的气泡一样逐渐往上冒.
 * 优化:因为排序的过程中,各元素不断接近自己的位置,如果一趟比较下来没有进行过交换,就说明
 * 序列有序,因此要在排序过程中设置一个标志flag判断元素是否进行过交换.从而减少不比要的比较.
 * 时间复杂度O(n^2)
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 12};
        System.out.println("排序前(不算上趟数)");
        System.out.println(Arrays.toString(arr));
        //冒泡排序
        sort(arr);
        System.out.println("最终结果(不算上趟数)");
        System.out.println(Arrays.toString(arr));
    }


    private static void sort(int arr[]) {
        //{3, 9, -1, 10, 12}
        //{3, 9, -1, 10, 12}
        boolean flag =false;//标识变量,表示是否进行过交换
        int temp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length -1-i; j++) {
                if (arr[j] > arr[j+1]) {
                    flag=true;  //进入交换了,表示交换过
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println("第"+(i+1)+"趟排序后的数组");
            System.out.println(Arrays.toString(arr));
            if (!flag){//在一趟排序中,一次交换都没有过
                break;
            }else {
                flag=false;//重置flag,进行下次判断
            }

        }
    }
}
/**
 * ↓  ↓
 * 原始数组:  3  9 -1 10 20
 * 第一趟排序:
 * ↓ ↓
 * [1] 3 9 -1 10 20 //如果相邻的元素逆序就交换 两个指针后移
 * ↓ ↓
 * [2] 3 -1 9 10 20
 * ↓ ↓
 * [3] 3 -1 9 10 20
 * ↓  ↓
 * [4] 3 -1 9 10 {20}
 * 第二趟 排序:
 * [1] -1 3 9 10 20
 * [2] -1 3 9 10 20
 * [3] -1 3 9 10 20
 * [4] -1 3 9 {10 20}
 * 第三趟 排序:
 * [1] -1 3 9 10 20
 * [2] -1 3 9 10 20
 * [3] -1 3 9 10 20
 * [4] -1 3 {9 10 20}
 * <p>
 * 小结冒泡排序规则
 * [1] 一共进行(数组的大小-1)次大的循环
 * [2] 每趟排序的次数在逐渐的减少
 * [3] 如果我们发现某趟排序中,没有发生一次交换,可以提前结束冒泡排序
 *
 *  模拟推导过程
 *         //第一趟排序,就是将最大的数排在最后
 *         int temp = 0;
 *         for (int i = 0; i < arr.length - 1; i++) {
 *             //如果前面打的数比后面的数大,则交换
 *             if (arr[i] > arr[i + 1]) {
 *                 temp = arr[i];
 *                 arr[i] = arr[i + 1];
 *                 arr[i + 1] = temp;
 *             }
 *         }
 *         System.out.println("第一趟排序就的数组");
 *         System.out.println(Arrays.toString(arr));
 *
 *         //第二趟排序,就是将第二大的数排在倒数第二位
 *         for (int i = 0; i < arr.length - 1 - 1; i++) {
 *             //如果前面打的数比后面的数大,则交换
 *             if (arr[i] > arr[i + 1]) {
 *                 temp = arr[i];
 *                 arr[i] = arr[i + 1];
 *                 arr[i + 1] = temp;
 *             }
 *         }
 *         System.out.println("第二趟排序就的数组");
 *         System.out.println(Arrays.toString(arr));
 *
 *         //第二趟排序,就是将第二大的数排在倒数第二位
 *         for (int i = 0; i < arr.length - 1 - 1 - 1; i++) {
 *             //如果前面打的数比后面的数大,则交换
 *             if (arr[i] > arr[i + 1]) {
 *                 temp = arr[i];
 *                 arr[i] = arr[i + 1];
 *                 arr[i + 1] = temp;
 *             }
 *         }
 *         System.out.println("第三趟排序就的数组");
 *         System.out.println(Arrays.toString(arr));
 *
 *         //第二趟排序,就是将第二大的数排在倒数第二位
 *         for (int i = 0; i < arr.length - 1 - 1 - 1 - 1; i++) {
 *             //如果前面打的数比后面的数大,则交换
 *             if (arr[i] > arr[i + 1]) {
 *                 temp = arr[i];
 *                 arr[i] = arr[i + 1];
 *                 arr[i + 1] = temp;
 *             }
 *         }
 *         System.out.println("第四趟排序就的数组");
 *         System.out.println(Arrays.toString(arr));
 *         最前一个数字并不需要排序
 */
