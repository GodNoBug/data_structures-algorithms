package structure.recursion;

// 二维数组模拟迷宫
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组模拟迷宫地图 8行7列
        int[][] map = new int[8][7];//[][] 其中，声明语句的第一个方括号可以称为行数组，第二个方括号可以称为列数组。
        //使用 1 表示墙 上下全部置为1
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;              //第一行所有列
            map[map.length-1][i] = 1;   //第八行所有列
        }
        //设置挡板, 1表示
        map[3][1] = 1;
        map[3][2] = 1;
        // 左右全部置为1
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;              //第1列所有行
            map[i][map[0].length-1] = 1;//第7列所有行
        }

        //输出地图
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        //使用递归回溯,给小球找路,[1,1]为起点 [6,5]为终点
        setWay(map,1,1);

        //新地图
        System.out.println("完成");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

     //   0 1 2 3 4 5 6
     // 0|
     // 1|
     // 2|
     // 3|      *
     // 4|
     // 5|
     // 6|
     // 7|
     // * => [3,3] 往下走 [3+1,3]. 往右[3,3+1]. 往上[3-1,3] 往左[3,3-1]
    /**
     * TODO 使用递归回溯给小球找路
     * i,j表示地图的哪个位置开始出发,这里设(1,1)
     * 如果小球能到 map[6][5]位置书目通路,则说明通路找到
     * 约定:当map[i][j] 为0表示该点没有走过 当为1表示墙 2表示通路可以走 3表示该点已经做过,但是走不通
     *
     * @param map 表示地图
     * @param i   j 从哪个位置开始找
     * @return 如果找到通路, 就返回true, 否则返回false
     * 策略:在走迷宫时,我们需要确定一个策略,下→右→上→左 在当前节点走不通回到当前节点再走,走不通再回溯
     *
     *
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 终点条件.满足不调用递归法
            return true;
        } else {
            if (map[i][j] == 0) { //如果当前这个节点没有走过 //按照这个策略下→右→上→左走
                map[i][j] = 2;    //假定当前点可以走通的,而后试着策略走
                if (setWay(map, i + 1, j)) {//向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) { //向左走
                    return true;
                } else {//说明该点是死路,置3
                    map[i][j] = 3;
                    return false;
                }
            }else {//map[i][j] != 0 可能是 1/2/3
                return false;
            }
        }
    }

    // 修改找路的策略,改成,先上->右->下->左  TODO 思考求最短路径
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 终点条件.满足不调用递归法
            return true;
        } else {
            if (map[i][j] == 0) { //如果当前这个节点没有走过 //按照这个策略下→右→上→左走
                map[i][j] = 2;    //假定当前点可以走通的,而后试着策略走
                if (setWay2(map, i - 1, j)) {//向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) { //向左走
                    return true;
                } else {//说明该点是死路,置3
                    map[i][j] = 3;
                    return false;
                }
            }else {//map[i][j] != 0 可能是 1/2/3
                return false;
            }
        }
    }

}
