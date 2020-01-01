package structure.tree;
// 构建哈夫曼树的步骤:
// 1) 从小到大进行排序,将每个数据看作节点,每个节点可以看成是一颗最简单的二叉树
// 2) 取出节点权值最小的两颗二叉树
// 3) 组成一颗新的二叉树,该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
// 4) 再将这颗新的二叉树,以根节点的权值大小再次排序,不断重复 1-2-3-4的步骤,直到数列中,所有的数据都被处理,就得道
//    一颗哈夫曼树

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    @Test
    public void test1() {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanTree = createHuffmanTree(arr);
        // 遍历测试
        preOrder(huffmanTree);
    }
    // 创建哈夫曼树的方法
    public Node createHuffmanTree(int[] arr){
        // 第一步,为了操作方便
        // 1.遍历arr数组
        // 2.将arr的每个元素构建成一个Node
        // 3.将Node放入到ArrayList中
        List<Node> nodes =new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        //我们处理的过程就是循环的过程
        while (nodes.size()>1) {
            // 从小到大排序
            Collections.sort(nodes);
            // 取出根节点权值最小的两颗二叉树
            // (1) 取出权值最小的节点(二叉树)
            Node left = nodes.get(0);
            // (2) 取出权值第二小的节点(二叉树)
            Node right = nodes.get(1);
            // (3) 创建一颗新的二叉树
            Node parent = new Node(left.value + right.value);
            parent.left = left;
            parent.right = right;
            // (4) 从ArrayList删除处理过的二叉树
            nodes.remove(left);
            nodes.remove(right);
            // (5) 将parent加入到nodes
            nodes.add(parent);
        }
        return nodes.get(0);

    }

    // 编写一个前序遍历的方法
    public void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("是空树,不能遍历~");
        }
    }
}

// 创建节点类
// 为了让Node对象支持排序Collections集合排序
// 让Node实现Comparable接口
@Getter
@Setter
class Node implements Comparable<Node> {
    public int value; // 节点权值
    public Node left; // 指向左子节点
    public Node right;// 指向右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大排序
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }

    }
}
