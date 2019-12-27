package structure.tree;
//TODO 堆排序基本介绍

//堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，它的最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序。
//堆是具有以下性质的完全二叉树：
// 1.每个结点的值都大于或等于其左右孩子结点的值，称为大顶堆,
//   (注意 : 没有要求结点的左孩子的值和右孩子的值的大小关系。)
// 大顶堆的特点: arr[i]>=arr[2*i+1]&&arr[i]>=arr[2*i+2] // i 对应第几个节点，i从0开始编号

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

// 2.每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆
//   小顶堆：arr[i] <= arr[2*i+1] && arr[i] <= arr[2*i+2] // i 对应第几个节点，i从0开始编号 一般升序采用大顶堆，降序采用小顶堆 
// TODO 对于数组中任一个i上位置的元素,其左儿子在位置2i上,右儿子在左儿子后的单元(2i+1),其父亲节点则在位置[i/2]上
public class HeapSort {
    public static void main(String[] args) {
        // 要求将数组进行升序排序
        // int[] arr = {4, 6, 8, 5, 9};
        // hoopSort(arr);
        int[] arr=new int[8000000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        System.out.println("生成排序前");
        System.out.println("排序前的时间是:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        hoopSort(arr);
        System.out.println("排序后的时间是:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

//        System.out.println("排序后="+Arrays.toString(arr));

    }

    /**
     * 堆排序的基本思想是：
     * 将待排序序列构造成一个大顶堆
     * 此时，整个序列的最大值就是堆顶的根节点。
     * 将其与末尾元素进行交换，此时末尾就为最大值。
     * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了。
     * 可以看到在构建大顶堆的过程中，元素的个数逐渐减少，最后就得到一个有序序列了.
     */
    // 思路
    //

    /**
     * 编写一个堆排序的方法
     */
    public static void hoopSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序");
        // 分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次调整"+ Arrays.toString(arr));
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次调整"+ Arrays.toString(arr));
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        for (int j = arr.length - 1; j > 0; j--) {
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        // System.out.println("最终结果" + Arrays.toString(arr));

    }

    /**
     * 将一个数组(二叉树),调整成一个大顶堆
     * 功能: 完成将以i为非叶子节点的数调整成大顶堆
     * 举例 int[] arr = {4,6,8,5,9}; => i=1 => adjustHeap =>{4,9,8,5,6}
     * 如果我们再次调用 adjustHeap,传入的是i=0 =>{9,6,8,5,4}
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引
     * @param length 对多少个元素进行调整,length是逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i]; // 先取出当前元素的值,保存在临时变量,待会要用
        // 开始调整
        // 1.k = i*2+1 k是i节点的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) { // 说明左子节点的值小于右子节点的值
                k++;    // k指向右子节点
            }
            if (arr[k] > temp) { // 如果子节点大于父节点
                arr[i] = arr[k]; // 把较大的值赋给当前节点
                i = k;           // 让i指向k,继续循环比较
            } else {
                break;           //
            }
        }
        // 当for循环结束后,我们已经将i为父节点的数的最大值,放在了最顶上(局部的)
        arr[i] = temp; // 将temp值放到调整后的位置
    }
    //
}
