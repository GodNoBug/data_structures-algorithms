package structure.array;

/**
 * 稀疏数组
 * 当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。
 * <p>
 * 稀疏数组的处理方法是:
 * 记录数组一共有几行几列，有多少个不同的值
 * 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 * <p>
 * 二维数组 6*7 = 42 ==> 稀疏数组 9*3 = 27
 * 因为二维数组的很多值是默认值0. 因此记录了很多没有意义的数据 -> 稀疏素组
 */
public class SparseArray {
    public static void main(String[] args) {
        // 创建原始二维数组
        int[][] chessArr = createChessArr();
        System.out.println("原始的二维数组");
        show(chessArr);

        // 将二维数组转为稀疏数组
        int[][] sparseArr= toParseArray(chessArr);
        System.out.println("稀疏数组");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        // 将稀疏数组 -->恢复成原始的数组
        int[][] chess = toArray(sparseArr);
        System.out.println("恢复过后的二维数组");
        show(chess);
    }

    // 创建原始二维数组
    private static int[][] createChessArr() {
        // 创建一个原始的二维数组 11*11
        // 0: 表示没有子,1表示黑子 2 表示蓝子
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
        return chessArr;
    }

    // 遍历二维数组
    public static void show(int[][] arr){
        for (int[] row : arr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
    //二维数组转稀疏数组的思路
    // 1.遍历元素的二维数组,得到有效数据的个数sun
    // 2.根据sun就可创建稀疏数组sparseArr[sun+1][3]
    // 3.将二维数组的有效数据存入稀疏数组中
    public static int[][] toParseArray(int[][] chessArr){
        // 将二维数组 转稀疏数组的思路
        // 1. 先遍历二维数组得到非0的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        // 2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = chessArr.length;   //行数
        sparseArr[0][1] = chessArr[0].length;//列数
        sparseArr[0][2] = sum;
        // 遍历二维数组,将非0的值存放到稀疏数组中
        int count = 0; // 用于记录是第几个非0数据
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
        return sparseArr;
    }
    //稀疏数组转二维数组的思路
    // 1.先读取到稀疏数组的第一行,根据第一行的数据,创建原始二维数组arr2[11][11]
    // 2.再读取稀疏数组后几行的数据,并赋给原始的二维数组即可
    public static int[][] toArray(int[][] sparseArr){
        int[][] chess = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            chess[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return chess;
    }
}
/*
 *                              row col  val (行不确定,列是三列的动态数组)
 *  00000000000                 0   11  11   2   (第一行记录总共几行几列共有几个有效数据)
 *  00100000000                 1    1   2   1   (其他行是数据的位置和值)
 *  00020000000                 2    2   3   2
 *  00000000000
 *  00000000000
 *  00000000000     ====稀疏数组===> 11*11 ==>3*3
 *  00000000000
 *  00000000000
 *  00000000000
 *  00000000000
 */
