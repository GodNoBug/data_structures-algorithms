package structure.tree;

// 顺序存储二叉树:树的顺序存储结构

import lombok.AllArgsConstructor;
import lombok.Data;

// TODO 堆排序是二叉树的实际应用,就会用到顺序存储二叉树
// 基本说明:
// 从数据存储来看,数组存储方式和树的存储方式可以相互转换,即数组可以转换成树,树也可以转换成数组,
// 要求: 树的节点以数组的方式存储,仍然可以以前序遍历,中序遍历和后序遍历
//      给定一个素组,要求以二叉树的方式进行遍历
// 特点:
//     1)顺序二叉树通常指考虑完全二叉树,若不满足完全二叉树,缺的数组位置值需要置空或置0
//     2)第n个元素的左子节点为2n+1
//     3)第n个元素的右子节点为2n+2
//     4)第n个元素的父子节点为(n-1)/2 (取整)
//     5)n:表示二叉树中的第几个元素(按0开始编号,和数组保持一致)
// 应用: 八大排序算法中的堆排序,就会使用到顺序存储二叉树,关于堆排序,我们放在<<数结构实际应用>>章节讲解
public class ArrayBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7}; // 满二叉树的编号顺序存储在数组中,遍历仍然要以二叉树的前中后序遍历
        ArrayBinaryTree tree = new ArrayBinaryTree(arr);
        tree.preOrder(); // 前序遍历 1,2,4,5,3,6,7
        System.out.println("----------");
        tree.infixOrder(); // 中序遍历 4,2,5,1,6,3,7
        System.out.println("----------");
        tree.postOrder(); // 后序遍历 4,2,5,6,3,7,1
    }
}

//编写一个ArrayBinaryTree,实现顺序存储二叉树的遍历
@Data
@AllArgsConstructor
class ArrayBinaryTree {
    private int[] arr; //存储数据节点的数组

    //重载
    public void preOrder() {
        this.preOrder(0);
    }

    public void infixOrder() {
        this.infixOrder(0);
    }

    public void postOrder() {
        this.postOrder(0);
    }


    // 编写一个方法,完成顺序存储二叉树的前序遍历 index表示数组的下标
    // 思路:
    //
    private void preOrder(int index) {
        //如果数组为空,或者长度为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树遍历");
        }
        System.out.println(arr[index]);//输出当前的数组元素
        // 向左递归遍历
        if ((2 * index + 1) < arr.length) { // 如果没超出数组的个数,那么向左递归遍历,防止越界
            preOrder(2 * index + 1);

        }
        // 向右递归遍历
        if ((2 * index + 2) < arr.length) { // 如果没超出数组的个数,那么向右递归遍历,防止越界
            preOrder(2 * index + 2);
        }
    }

    private void infixOrder(int index) {
        //如果数组为空,或者长度为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树遍历");
        }
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }
        System.out.println(arr[index]);
        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
    }


    private void postOrder(int index) {
        //如果数组为空,或者长度为0
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空,不能按照二叉树遍历");
        }
        if ((2 * index + 1) < arr.length) {
            infixOrder(2 * index + 1);
        }

        if ((2 * index + 2) < arr.length) {
            infixOrder(2 * index + 2);
        }
        System.out.println(arr[index]);
    }

}

