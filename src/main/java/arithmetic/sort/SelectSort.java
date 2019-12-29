package arithmetic.sort;

import org.junit.Test;

import java.util.Arrays;


//选择排序
//基本思想:数组从后面找最小的依次放在前面
//  第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，
//  第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，
//  第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，…，
//  第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，…,
//  第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，
//  得到一个按排序码从小到大排列的有序序列。


// 1.选择排序一共有[数组大小-1]轮排序
// 2.每一轮排序又是一轮循环,循环的规则如下(具体见代码)
// 3.假定当前这个数是最小数,然后和后面的每个数比较,如果发现有比当前更小的数,重新确定最小数,并得到下班
//   当遍历到数组的最后时,就得到本轮最小数和下标,交换[代码]
// 复杂度T(n^2)
public class SelectSort {
    @Test
    public void test1() {
        int[] arr = {8, 3, 2, 1, 7, 4, 6, 5};//前面每一趟排好,每一趟少一个
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    //                 i  j=i+1
    //                 ↓  ↓
    //    int[] arr = {8, 3, 2, 1, 7, 4, 6, 5}
    private void sort(int[] arr) {
        // 8个数字确定7个数就算排序完了,arr.length-1
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];   // 设置当前最小的数
            int minIndex = i;   // 设置当前最小的数的索引
            // j = i + 1 排除当前的比较后面的
            for (int j = i + 1; j < arr.length; j++) {// 这样i值就代表了被比较值，而j就代表了比较值
                if (min > arr[j]) {// 说明假定的最小值并不是最小的
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {   // 优化项:判断,minIndex还是那个当前最左边那个minIndex,没有改变,则不需要交换
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
/**
 * 复杂的算法拆分成简单的问题,然后逐步解决
 * //找最小值的思路 原始数组: 101,34,199,1
 *
 * // 第一轮
 * int min = arr[0];
 * int minIndex = 0;
 * for (int j = 0+1; j < arr.length; i++) {
 *      if (min>arr[j]){ // 成立说明假定的最小值并不是最小
 *          min=arr[j];  // 重置min
 *          minIndex=j;  // 重置minIndex
 *      }
 * }
 * // 将最小值,放在arr[0],即交换
 *  arr[minIndex] = arr[0];
 *  arr[0] = min;
 *
 *
 * // 第二轮
 * min = arr[1];
 * minIndex = 1;
 * for (int j = 1+1; j < arr.length; i++) {
 *      if (min>arr[j]){ // 成立说明假定的最小值并不是最小
 *          min=arr[j];  // 重置min
 *          minIndex=j;  // 重置minIndex
 *      }
 * }
 * // 将最小值,放在arr[0],即交换
 *  arr[minIndex] = arr[1];
 *  arr[1] = min;
 *
 *
 * 第三轮:
 * min = arr[2];
 * minIndex = 2;
 * for (int j = 1+1+1; j < arr.length; i++) {
 *      if (min>arr[j]){ // 成立说明假定的最小值并不是最小
 *          min=arr[j];  // 重置min
 *          minIndex=j;  // 重置minIndex
 *      }
 * }
 * // 将最小值,放在arr[0],即交换
 *  arr[minIndex] = arr[2];
 *  arr[2] = min;
 */
