package arithmetic.sort;

import java.util.Arrays;

//选择排序
//数组从后面找最小的依次放在前面
//1.选择排序一共有数组大小-1轮排序
//2,每一轮排序又是一轮循环,循环的规则(代码)
//3.假定当前这个数是最小数,然后和后面的每个数比较,如果发现有比当前更小的数,重新确定最小数,并得到下班
//  当遍历到数组的最后时,就得到本轮最小数和下标,交换[代码]
// 复杂度T(n^2)
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {8, 3, 2, 1, 7, 4, 6, 5};//前面每一趟排好,每一趟少一个
        int j =0;
        for (int i = 0; i < arr.length-1; i++) {
            System.out.println(arr[i]);
        }
        //sort(arr);
        //System.out.println(Arrays.toString(arr));


    }
//                 i  j=i+1
//                 ↓  ↓
//    int[] arr = {8, 3, 2, 1, 7, 4, 6, 5}
    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = arr[i];   //设置当前最小的数
            int minIndex = i;   //设置当前最小的数的索引
            for (int j = i + 1; j < arr.length; j++) {//这样i值就代表了被比较值，而j就代表了比较值
                if (min > arr[j]) {//说明假定的最小值并不是最小的
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {//判断,minIndex还是那个当前最左边那个minIndex
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
/**
 * 复杂的算法拆分成简单的问题,然后逐步解决
 * //找最小值的思路
 * int min = arr[0];
 * for (int i = 0; i < arr.length - 1; i++) {
 *     if (min>arr[i]){
 *         min=arr[i];
 *     }
 * }
 * //好到,排好,忘掉已经排好的
 * System.out.println(min);
 *
 * 第一轮:
 *  原始数组: 101,34,199,1
 *  第一轮排序: 1,34,199,101
 *  int minIndex = 0 //假定最小索引是数组第一个数的索引
 *  int min = arr[0] //假定最小数为第一个数
 *  for(int j =0+1 ; j<arr.length; j++){
 *     if (min>arr[i]){//说明假定的最小值并不是最小的
 *         min=arr[i];//重置min
 *         minIndex=j;//重置minIndex
 *     }
 *  }
 *  //将最小值放在arr[0],即交换
 *  if (minIndex!=0){
 *     arr[minIndex] = arr[0];
 *     arr[0] = min;
 *  }
 *
 *  System.out.println("第1轮后~~");
 *  System.out.println(Arrays.toString(arr));
 *
 *  第二轮:
 *  minIndex = 1
 *  min = arr[1]
 *  for(int j =0+1+1 ; j<arr.length; j++){
 *     if (min>arr[i]){//说明假定的最小值并不是最小的
 *         min=arr[i];//重置min
 *         minIndex=j;//重置minIndex
 *     }
 *  }
 *  if (minIndex!=1){
 *     arr[minIndex] = arr[1];
 *     arr[1] = min;
 *  }
 *  System.out.println("第2轮后~~");
 *  System.out.println(Arrays.toString(arr));// 1,34,119,101
 *
 *  第三轮:
 *  minIndex = 2
 *  min = arr[2]
 *  for(int j =0+1+1+1 ; j<arr.length; j++){
 *     if (min>arr[i]){//说明假定的最小值并不是最小的
 *         min=arr[i];//重置min
 *         minIndex=j;//重置minIndex
 *     }
 *  }
 *  if (minIndex!=2){
 *     arr[minIndex] = arr[2];
 *     arr[2] = min;
 *  }
 *  System.out.println("第3轮后~~");
 *  System.out.println(Arrays.toString(arr));// 1,34,119,101
 */
