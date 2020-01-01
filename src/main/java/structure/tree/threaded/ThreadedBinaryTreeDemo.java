package structure.tree.threaded;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

//线索化二叉树
//   当用二叉链表作为数的存储结构时,可以很方便地找到某个节点的左右孩子;
// 但一般情况下,无法直接找到节点在某种遍历序列中的前驱和后续节点.
// 提出的问题:
//   如何查找特定遍历序列中的二叉树节点的前驱和后续??
// 解决的方法:
//  1.通过遍历寻找-(费时间)
//  2.再增设前驱,后继指针域-(增加了存储负担)
//  3.利用二叉链表中的空指针域.
// 二叉树链表中空指针域的数量:
//   具有n个节点的二叉链表中,一共有2n个指针域;
// 因为n个节点中有n-1个孩子,即2n个指针域中,有
// n-1个用来指示节点的左右孩子,其余n+1个指针域
// 为空
// 利用二叉链表中的空指针域:
//    如果某个节点的左孩子为空,则将空左孩子指针域改为指向前驱,
//    如果某节点的右孩子为空,则将空的右孩子指针域改为指向其后续
// 为区分左left和right指针到底是指向孩子的指针,还是指向前驱或者后续的指针,对二叉链表中的每个节点增设两个标志域ltag和rtag,并约定
//   ltag=0 left指向该节点的左孩子
//   ltag=1 left指向该节点的前驱
//   rtag=0 left指向该节点的右孩子
//   rtag=0 left指向该节点的后续
//   增加一个头结点,左指向根节点,右指向遍历序列的最后一个节点


// 定义一个 ThreadedBinaryTree 实现了线索化功能的二叉树
@Setter
class ThreadedBinaryTree {
    private HeroNode root;
    // 为了实现线索化,需要创建要给指向当前节点的前驱节点的指针
    // 在递归进行索引化时,pre总是保留前一节点
    private HeroNode pre = null;
    // 在递归进行线索化

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    /**
     * TODO 中序线索化 编写传入一个节点对二叉树进行红谷线索化的方法的方法
     * @param node 当前需要线索化的节点
     */
    //         1
    //     3      6
    //  8   10  14
    public void threadedNodes(HeroNode node) {
        //如果node==null,不能线索化
        if (node == null) {
            return;
        }
        // 1.先线索化左子树
        threadedNodes(node.getLeft());
        // 2.线索化当前节点 因为是单向的,不能同时处理左右节点
        // 先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);   // 让当前节点的左指针指向前驱节点
            node.setLeftType(1); // 修改当前节点的左指针的类型,指向前驱节点
        }
        // 处理后续节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);  // 让前驱节点的右指针指向当前节点
            pre.setRightType(1); // 修改前驱节点的有纸质类型
        }
        pre = node; // TODO 每处理一个节点后,让当前节点执行
        // 3.再线索化右子树
        threadedNodes(node.getRight());
    }

    /**
     * TODO 前序线索化 编写传入一个节点对二叉树进行红谷线索化的方法的方法
     *
     * @param node 当前需要线索化的节点
     */


    /**
     * TODO 前序线索化 编写传入一个节点对二叉树进行红谷线索化的方法的方法
     *
     * @param node 当前需要线索化的节点
     */


    // 原先的遍历会死递归
    //   说明：对前面的中序线索化的二叉树，进行遍历
    //   分析：因为线索化后，各个结点指向有变化，因此原来的遍历方式不能使用，
    // 这时需要使用新的方式遍历线索化二叉树，各个节点可以通过线型方式遍历，因
    // 此无需使用递归方式，这样也提高了遍历的效率。遍历的次序应当和中序遍历保持一致。
    public void infixOrder() {
        // 定义一个变量,存储当前遍历的节点,从root开始
        HeroNode node = root;
        while (node != null) {
            // 循环直到LeftType==1的节点,第一个找到的就是8节点
            // 后面随着遍历而变化,因为LeftType==1时,说明该节点是按值线索化处理后的有效节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点的右指针指向的后续节点,就一直输出
            while (node.getRightType() == 1) {
                // 后去当前节点的后续节点
                node = node.getRight();
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.getRight();
        }

    }

    // 前序遍历
    public void preOrder() {

    }


    // 后序遍历
    public void postOrder() {

    }

    // 前序查找
    public HeroNode preOrderSearch(int no) {
        if (this.root != null) {
            return this.root.preOrderSearch(no);
        } else { //
            return null;
        }
    }

    // 中序查找
    public HeroNode infixOrderSearch(int no) {
        if (this.root != null) {
            return this.root.infixOrderSearch(no);
        } else { //
            return null;
        }
    }

    // 后序查找
    public HeroNode postOrderSearch(int no) {
        if (this.root != null) {
            return this.root.postOrderSearch(no);
        } else { //
            return null;
        }
    }

    //删除
    public void deleteNode(int no) {
        if (this.root != null) {
            //如果只有一个root节点,这里立即判断root是否就是要删除的节点
            if (root.getNo() == no) {
                this.root = null;
            } else {
                //递归删除
                this.root.deleteNode(no);
            }
        } else {
            System.out.println("空树,不能删除");
        }
    }
}

// 先创建HeroNode节点
@Getter
@Setter
class HeroNode {
    private int no;//编号
    private String name;//名字
    private HeroNode left;
    private HeroNode right;

    //说明
    //1. 如果leftType == 0 表示指向的是左子树,如果 1 则表示指向前驱节点
    //2. 如果rightType == 0 表示指向的是右子树, 如果 1 表示指向后续节点
    private int leftType;
    private int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 遍历
     */

    // 前序遍历
    public void preOrder() {
        System.out.println(this);//先输出父节点 递归调用的this为调用方法的对象
        // 递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        // 递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        // 递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    // 后续遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    /**
     * 查找
     */
    // 前序遍历查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");

        if (this.no == no) {
            return this;
        }
        // 1.则判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
        // 2.如果左递归前序查找,找到节点,则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) { //说明我们左子树上找到了
            return resNode;
        }
        // 1.如果左递归前序查找找到了节点则返回,否则继续判断当前节点的右子节点是否为空,
        // 2.如果不为空,则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode; // 右子树有可能找到有可能没找到,这时无论有没有找到都必须返回
    }

    // 中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
        if (this.no == no) {
            return this;
        }

        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 后序遍历查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        // 如果左右子树都没找到,就比较当前节点是不是
        System.out.println("进入后续查找");
        if (this.no == no) {
            return this;
        }
        return null;    //return resNode;
    }

    /**
     * 删除节点
     */
    // 删除节点
    public void deleteNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        //
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}


// 线索二叉树基本介绍
// 1.n个结点的二叉链表中含有n+1  【公式 2n-(n-1)=n+1】 个空指针域。
//   利用二叉链表中的空指针域，存放指向该结点在某种遍历 次序 下的前驱和后继结点的指针（这种附加的指针称为"线索"）
// 2.这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。
//   根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
//
// 3.一个结点的前一个结点，称为前驱结点
// 4.一个结点的后一个结点，称为后继结点

// 思路:
//    说明: 当线索化二叉树后，Node节点的 属性 left 和 right ，有如下情况:
//          left 指向的是左子树，也可能是指向的前驱节点.
//          right指向的是右子树，也可能是指向后继节点.
public class ThreadedBinaryTreeDemo {
    //         1
    //     3      6
    //  8   10  14

    //  8,3,10,1,14,6
    @Test
    public void test1() {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "tom");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        // 二叉树,后面我们要递归的创建,现在简单处理事宜手动创建
        root.setLeft(node2);
        root.setRight(node3);

        node2.setLeft(node4);
        node2.setRight(node5);

        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNodes();

        //测试:以10号
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是=" + leftNode);
        System.out.println("10号节点的后继节点是=" + rightNode);

        System.out.println("使用线索化的方式遍历 线索化二叉树");
        tree.infixOrder();
    }

    @Test
    public void test2() {
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "tom");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        // 二叉树,后面我们要递归的创建,现在简单处理事宜手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        tree.threadedNodes(root);

        //测试:以10号
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();
        System.out.println("10号节点的前驱节点是=" + leftNode);
        System.out.println("10号节点的后继节点是=" + rightNode);

        System.out.println("使用线索化的方式遍历 线索化二叉树");
        tree.infixOrder();
    }


}
