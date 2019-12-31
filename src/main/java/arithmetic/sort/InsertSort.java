package arithmetic.sort;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序 基本思想:(类似理麻将插排,和)
 * 把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，
 * 无序表中包含有n-1个元素，排序过程中每次从无序表中取出第一个元素，把它的排序码依
 * 次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表。
 * <p>
 * 从小到大排序
 * 初始数组:  (17) 3 25 14 20 9
 * 第一次插入:(3 17) 25 14 20 9
 * 第二次插入:(3 17 25) 14 20 9
 * 第三次插入:(3 14 17 25) 20 9
 * 第四次插入:(3 14 17 20 25) 9
 * 第五次插入:(3 9 14 17 20 25)
 * 6个数的数组需要5次插入
 */
public class InsertSort {
    @Test
    public void test1() {
        int[] arr = {17, 3, 25, 14, 20, 9};
        sort(arr);
        System.out.println(Arrays.toString(arr));;
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

    public void sort(int[] arr){
        for (int i = 1; i < arr.length ; i++) {
            int insertValue = arr[i]; // 定义带插入的数
            int insertIndex = i-1;            // 待插入的索引 1-1 即arr[1]的前面这个数的下标
            // 给insertValue找到插入的位置
            // 说明:
            // 1.insertIndex>=0 保证在给insertValue找插入位置是,不越界
            // 2.insertValue < arr[insertIndex] 待插入的数,还没有找到插入位置
            // 3.就需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex--;
            }
            // 当退出while循环时,说明插入的位置找到,insertIndex+1
            if(insertIndex+1!=i){ // 优化项
                arr[insertIndex+1]=insertValue;
            }
        }
    }
    public void sort2(int[] arr) {
        // 使用逐步推导的方法来方式来讲解
        // 第一轮 (17) 3 25 14 20 9 => (3 17) 25 14 20 9
        int insertValue = arr[1]; // 定义带插入的数
        int insertIndex = 0;            // 待插入的索引 1-1 即arr[1]的前面这个数的下标
        // 给insertValue找到插入的位置
        // 说明:
        // 1.insertIndex>=0 保证在给insertValue找插入位置是,不越界
        // 2.insertValue < arr[insertIndex] 待插入的数,还没有找到插入位置
        // 3.就需要将arr[insertIndex]后移
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }
        // 当退出while循环时,说明插入的位置找到,insertIndex+1
        arr[insertIndex+1]=insertValue;
        System.out.println("第一轮输入");
        System.out.println(Arrays.toString(arr));


        insertValue = arr[2];
        insertIndex = 2-1;
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex+1]=insertValue;
        System.out.println("第二轮输入");
        System.out.println(Arrays.toString(arr));


        insertValue = arr[3];
        insertIndex = 3-1;
        while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
            arr[insertIndex+1] = arr[insertIndex];
            insertIndex--;
        }

        arr[insertIndex+1]=insertValue;
        System.out.println("第三轮输入");
        System.out.println(Arrays.toString(arr));
    }
}
