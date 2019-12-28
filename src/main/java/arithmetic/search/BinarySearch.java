package arithmetic.search;
// 二分查找
// 思路:
// low
// high
// 1.首先确定该数组的中间的下标
//  mid =(low+high)/2
// 2.然后让需要查找的数key和arr[mid]比较
//   key>arr[mid] low=mid+1 (+1是因为mid已经比较过了),说明你要查找的数在mid右边,一次需要递归的向右查找
//   key<arr[mid] high=mid-1(-1是因为mid已经比较过了),说明你要查找的数在mid左边,一次需要递归的向左查找
//   key==arr[mid] 说明找到了,就返回
// 3.结束递归条件
//   1)找到return
//   2)low>high时,查找失败
// 如果结果不为偶数


import org.junit.Test;

import java.util.ArrayList;

// 二分方法查找,TODO 首先它是有序的
public class BinarySearch {


    @Test
    public void test1() {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int key = 1000;
        System.out.println("查出key对应的下标为:" + search(arr, key));
    }

    @Test
    public void test2() {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int key = 100;
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        System.out.println("查出key对应的下标为:" + search2(arr, key, low, high, mid));
    }

    @Test
    public void test3() {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1234};
        int key = 1000;
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        System.out.println("查出key对应的下标为:" + search3(arr, key, low, high, mid));
    }

    /**
     * 递归法,虽然靠非递归法直觉做出来的,建议debug分析过程
     *
     * @param arr  数组
     * @param key  要找的值
     * @param low  左边的索引,也有人写left
     * @param high 右边的索引,也有人写right
     * @param mid  中间值
     * @return 如果找到就返回数组的下标, 否则返回-1
     */
    private int search2(int[] arr, int key, int low, int high, int mid) {
        mid = (high + low) / 2;
        if (key == arr[mid]) {
            return mid;
        } else if (low > high) {
            return -1;
        }
        if (key < arr[mid]) { // 向左递归
            high = mid - 1;
            return search2(arr, key, low, high, mid);
        }
        if (key > arr[mid]) { // 向右递归
            low = mid + 1;
            return search2(arr, key, low, high, mid);
        }
        return -1;
    }

    // 课后思考题:如果有两个1000怎么办
    //  int[] arr = {1, 8, 10, 89, 1000,1000, 1234};
    // 首先它是有序的,那么如果真存在相同数字,可能在找到该数的左边也可能在右边
    // 思路:
    //    1.在找到mid值时,不要马上返回,
    //    2.先向mid索引的左边扫描,将所有满足1000的元素下标,加入到集合ArrayList
    //    3.向mid索引的右边扫描,将所有满足1000的元素下标,加入到集合ArrayList
    //    4.向mid索引的右边扫描,将所有满足1000的元素下标,加入到集合ArrayList
    //    5.返回ArrayList
    private ArrayList<Integer> search3(int[] arr, int key, int low, int high, int mid) {
        mid = (high + low) / 2;
        if (key == arr[mid]) {
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //向左扫描
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != key) {
                    break;
                }
                // 否则,就将temp放入到集合中
                resIndexList.add(temp);
                temp -= 1;//temp左移

            }
            resIndexList.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != key) {
                    break;
                }
                // 否则,就将temp放入到集合中
                resIndexList.add(temp);
                temp += 1;//temp左移

            }

            return resIndexList;
        } else if (low > high) {
            return null;
        }
        if (key < arr[mid]) { // 向左递归
            high = mid - 1;
            return search3(arr, key, low, high, mid);
        }
        if (key > arr[mid]) { // 向右递归
            low = mid + 1;
            return search3(arr, key, low, high, mid);
        }
        return null;
    }

    // 非递归法
    private int search(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        int mid;
        while (!(low > high)) {
            mid = (high + low) / 2;
            if (key == arr[mid]) {
                return mid;
            } else if (key > arr[mid]) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            }
        }
        return -1;
    }
}
