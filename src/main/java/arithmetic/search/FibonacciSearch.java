package arithmetic.search;

import org.junit.Test;

import java.util.Arrays;

/**
 * 斐波那契数列
 */
public class FibonacciSearch {
    private static int maxSize = 20;

    @Test
    public void test1() {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int i = fibSearch(arr, 1234);
        System.out.println(i);

    }

    // 因为后面我们mid=low+F(k-1)-1,需要使用到斐波那契数列,因此我们需要获取到一个斐波那契数列
    // 非递归的方式得到斐波那契数列
    public int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
    //

    /**
     * 编写斐波那契查找算法(非递归)
     *
     * @param a   数组
     * @param key 需要查找的关键字
     * @return 返回下标
     */
    public int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0; // 表示斐波那契分割数值的下标
        int mid = 0; // 存放我们的mid值
        int[] f = fib(); // 获取到斐波那契数列
        while (high > f[k] - 1) { //
            k++;
        }
        // 因为f[k]大于数组a的长度,一次我们需要使用Arrays类,构造新的数组,并指向a(不足的部分会使用0填充)
        int[] temp = Arrays.copyOf(a, f[k]);
        // 实际上需要使用a数组的最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        // 使用while来循环找大我们的数key
        while (low <= high) { // 只要这个条件满足,可以一直找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) { // 我们应该继续向数组的前面查找(左边)
                high = mid - 1;
                // 1.全部元素 = 前面元素+后面的元素
                // 2.f[k] = f[k-1] = f[k-2]
                // 因为前面有f[k-1]个元素,所以可以继续拆分f[k-1]=f[k-2]+f[k-3]
                // 即在f[k-1] 的前面继续查找 k-- 即下次循环mid=f[k-1-1]-1
                k--;
            } else if (key > temp[mid]) { // 我们应该继续向数组的后面查找
                low = mid + 1;
                // 1.全部元素 = 前面元素+后面的元素
                // 2.f[k] = f[k-1] + f[k-2]
                // 3.因为后面我们有f[k-2] 所以可以继续拆分f[k-1]=f[k-3]+f[k-4]
                // 4.即在f[k-2]的前面进行查找k-=2
                // 5.即下次混合mid=f[k-1-2]-1
                k -= 2;
            } else { // 找到
                // 需要确定,返回的是哪个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }

        }
        return -1;
    }
}
