package arithmetic.search;

import org.junit.Test;
// 插值查找的公式
// mid = low + (high - low)*(key-arr[low])/(arr[high]-arr[low])

//  (mid - low)/(high - low)=(key-arr[low])/(arr[high]-arr[low])
public class InsertValueSearch {
    @Test
    public void test2() {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println("插值查出key对应的下标为:" + search2(arr, 0,arr.length-1,100));
        System.out.println("二分查出key对应的下标为:" + search3(arr, 100));
    }

    // 非递归法
    private int search(int[] arr, int key) {

        int low = 0;
        int high = arr.length - 1;

        int mid;
        while (!(low > high || key < arr[0] || key > arr[arr.length - 1])) {
            System.out.println("查找次数");
            mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]);
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

    /**
     * 插值查找算法
     *
     * @param arr   数组
     * @param left  左边索引
     * @param right 右边索引
     * @param key   查找值
     * @return 下标
     */
    public int search2(int[] arr, int left, int right, int key) {
        System.out.println("插值查找");
        //  key < arr[0] || key > arr[arr.length - 1] 必须有,否则越界
        if (left > right || key < arr[0] || key > arr[arr.length - 1]) {
            return -1;
        }
        int mid = left + (right - left) * (key - arr[left]) / (arr[right] - arr[left]);
        if (arr[mid] < key) { // 向右递归查找
            return search2(arr, mid + 1, right, key);
        } else if (arr[mid] > key) { // 向左递归查找
            return search2(arr, left, mid - 1, key);
        } else { // 查到了
            return mid;
        }
    }

    // 二分法查找比较
    private int search3(int[] arr, int key) {
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
            System.out.println("二分查找");
        }
        return -1;
    }
}
