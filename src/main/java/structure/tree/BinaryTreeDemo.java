package structure.tree;

import lombok.Getter;
import lombok.Setter;


// 遍历
// 查找


// 二叉树
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        tree.setRoot(root);


//        System.out.println("------前序遍历------");
//        tree.preOrder();
//        System.out.println("------中序遍历------");
//        tree.infixOrder();
//        System.out.println("------后续遍历------");
//        tree.postOrder();

//        System.out.println(tree.preOrderSearch(5));
//        System.out.println(tree.infixOrderSearch(5));
//        System.out.println(tree.postOrderSearch(5));

        System.out.println("~~~删除前~~~");
        tree.preOrder();
        tree.deleteNode(5);
        tree.deleteNode(3);
        System.out.println("~~~删除后~~~");
        tree.preOrder();
    }
}

// 定义一个BinaryTree
@Setter
class BinaryTree {
    private HeroNode root;

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
    public void deleteNode(int no){
        if (this.root!=null){
            //如果只有一个root节点,这里立即判断root是否就是要删除的节点
            if (root.getNo()==no){
                this.root=null;
            }else {
                //递归删除
                this.root.deleteNode(no);
            }
        }else {
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
        if (this.left!=null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right!=null && this.right.no == no) {
            this.right = null;
            return;
        }
        //
        if (this.left!=null){
            this.left.deleteNode(no);
        }
        if (this.right!=null){
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

// 二叉树遍历

// 1.前序遍历:输出父节点,再遍历左子树和右子树
// 2.中序遍历:先遍历左子树,再输出父节点,在遍历右子树
// 3.后续遍历:先遍历左子树,在遍历右子树,最后输出父节点
// 小结:看输出父节点的顺序,就是确定是前序,中序还是后序

// 1.前序遍历
// 先输出当前节点,(初始的时候是根节点)
// 如果左子节点不为空,则递归继续前序遍历
// 如果右子节点不为空,则递归继续前序遍历

// 2.中序遍历
// 如果当前节点的左子节点不为空,则递归中序遍历
// 先输出当前节点
// 如果当前节点的右子节点不为空,则递归中序遍历

// 3.后序遍历
// 如果当前节点的左子节点不为空,则递归后序遍历
// 如果当前节点的右子节点不为空,则递归后序遍历
// 先输出当前节点


// 二叉树查找

// 前序查找思路:
// 1.先判断当前节点的no是否等于要查找的
// 2.如果是相等,则返回当前节点
// 3.如果不相等,则判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
// 4.如果左递归前序查找找到了节点则返回,否则继续判断当前节点的右子节点是否为空,如果不为空,则继续向右递归前序查证中

// 中序查找思路:
// 1.判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
// 2.如果找到,则返回,如果没有找到,就和当前节点比较,如果是则返回当前节点,否则继续进行右递归的中序查找
// 3.如果右递归的中序查找,找到就返回,否则返回null

// 后序查找思路:
// 1.判断当前节点的左子节点是否为空,如果不为空,则递归后序查找
// 2.如果找到,则返回,如果没有找到,就判断当前节点的右子节点是否为空,如果不为空继续进行右递归的后序查找,如果找到就返回
// 3.如果右递归后序查找找不到,就和当前节点进行比较,如果是则返回,否则返回null


// 二叉树删除
// 预设要求:
// 1.如果删除的节点是叶子节点,则删除该节点
// 2.如果删除的节点是非叶子节点,则删除该子树

// 删除节点的操作思路:
// 首先处理:考虑如果树是空树root,如果只有一个root节点,则等价将二叉树置空
// 1.因为我们的二叉树是单向的,所以我们是判断当前节点的子节点是否需要删除,而不能去判断当前节点是否是删除的节点
// 2.如果当前节点的左子节点不为空,并且左子节点就是要删除的节点,就将this.left=null;并且返回(结束递归删除)
// 3.假设做子节点找不到,如果当前节点的右子节点不为空,并且右子节点就是要删除的节点,就将this.left=null;并且返回(结束递归删除)
// 4.如果第2步和第3步没有删除节点,那么我们需要向左子树进行递归删除
// 5.如果第4步也没有删除节点,则应当向右子树进行递归删除









