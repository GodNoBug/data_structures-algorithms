package tree.threaded;

import lombok.Getter;
import lombok.Setter;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root =new HeroNode(1,"tom");
        HeroNode node2 =new HeroNode(3,"tom");
        HeroNode node3 =new HeroNode(6,"smith");
        HeroNode node4 =new HeroNode(8,"mary");
        HeroNode node5 =new HeroNode(10,"king");
        HeroNode node6 =new HeroNode(14,"dim");
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
        System.out.println("10号节点的前驱节点是="+leftNode);
        System.out.println("10号节点的后继节点是="+rightNode);



    }
}

// 创建
// 定义一个 ThreadedBinaryTree 实现了线索化功能的二叉树
@Setter
class ThreadedBinaryTree {
    private HeroNode root;
    //为了实现线索化,需要创建要给指向当前节点的前驱节点的指针
    private HeroNode pre = null;
    // 在递归进行线索化

    public void threadedNodes(){
        this.threadedNodes(root);
    }
    /**
     * 编写传入一个节点对二叉树进行红谷线索化的方法的方法
     *
     * @param node 当前需要线索化的节点
     */
    public void threadedNodes(HeroNode node) {
        //如果node==null,不能线索化
        if (node == null) {
            return;
        }
        // 1.先线索化左子树
        threadedNodes(node.getLeft());
        // 2.线索化当前节点
        // 先处理当前节点的前驱节点
        if (node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型,指向前驱节点
            node.setLeftType(1);
        }
        // 处理后续节点
        if (pre != null && pre.getRight() == null) {
            // 让前驱节点的右指针指向当前节点
            pre.setRight(node);
            // 修改前驱节点的有纸质类型
            pre.setRightType(1);
        }
        // 每处理一个节点后,让当前节点执行
        pre = node;
        // 3.再线索化右子树
        threadedNodes(node.getRight());

    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        }
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
    //
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
//   利用二叉链表中的空指针域，存放指向该结点在某种遍历次序下的前驱和后继结点的指针（这种附加的指针称为"线索"）
// 2.这种加上了线索的二叉链表称为线索链表，相应的二叉树称为线索二叉树(Threaded BinaryTree)。
//   根据线索性质的不同，线索二叉树可分为前序线索二叉树、中序线索二叉树和后序线索二叉树三种
//
// 3.一个结点的前一个结点，称为前驱结点
// 4.一个结点的后一个结点，称为后继结点

// 思路:
//    说明: 当线索化二叉树后，Node节点的 属性 left 和 right ，有如下情况:
//          left 指向的是左子树，也可能是指向的前驱节点.
//          right指向的是右子树，也可能是指向后继节点.
