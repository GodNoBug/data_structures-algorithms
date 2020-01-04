package structure.tree.code;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.*;

// 2)统计字符对应的出现的次数
// 3)按照上面字符出现的次数构建一颗哈夫曼树,次数作为权值
// 4)根据哈夫曼树,给各个字符规定编码,向左的路径为0,向右的路径为1,

// 注意, 这个赫夫曼树根据排序方法不同，也可能不太一样，这样对应的赫夫曼编码也不完全一样，但是wpl 是一样的，都是最小的

public class HuffmanTreeCode {
    @Test
    public void test1() {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println(bytes.length); //40
        List<Node> nodes = getNodes(bytes);
        System.out.println("nodes=" + nodes);
        Node huffmanTree = createHuffmanTree(nodes);
        System.out.println("赫夫曼树");
        preOrder(huffmanTree);
        // 是否生成
    }

    @Test
    public void test2() {
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        List<Node> nodes = getNodes(bytes);
        Node huffmanTree = createHuffmanTree(nodes);
        Map<Byte, String> codes = getCodes(huffmanTree);
        System.out.println("生成的赫夫曼编码表是:" + codes);
    }


    // TODO 生成赫夫曼树
    private Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序是从小到大的
            Collections.sort(nodes);
            // 取出第一颗最小的二叉树
            Node left = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node right = nodes.get(1);
            // 创建一颗新的二叉树,它的根节点 没有data,只有权值
            Node parent = new Node(null, left.weight + right.weight);
            parent.left = left;
            parent.right = right;
            // 将已经处理的二叉树从nodes删除
            nodes.remove(left);
            nodes.remove(right);
            // 将新的二叉树加入到nodes
            nodes.add(parent);
        }
        // nodes最后的节点,就是赫夫曼树的根节点
        return nodes.get(0);
    }
    // 完成数据的解压
    // 思路
    // 1.将huffmanCodeBytes:[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
    //重新转成赫夫曼对应的二进制的二进制字符串
    // 2.赫夫曼编码对应的二进制字符串 转成明文字符串

    /**
     * 将一个byte转成一个二进制的字符串
     *
     * @param b 传入的bytes
     * @param flag 标识是否需要补高位.如果是true标识需要补高位,如果是false标识不补
     * @return 是该b 对应的二进制的字符串,(注意是按补码返回的)
     */
    private String byteToBitString(boolean flag, byte b) {
        // 使用一个变量保存b
        int temp = b;// 将b转成int
        // 如果是正数还存在补高位的问题
        if (flag) {
            temp |= 256; // 按位与256 1 0000 0000 | 0000 0001 => 1 0000 0001
        }
        String str = Integer.toBinaryString(temp); // 返回的是temp是二进制的补码
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /**
     * 编写一个方法,将字符串对应的byte[]数组,通过生成的赫夫曼编码表,返回一个赫夫曼编码压缩后的一个Byte数组
     *
     * @param bytes        这时元素的字符串对应的byte[]
     * @param huffmanCodes 生成赫夫曼编码的map
     * @return 返回赫夫曼编码处理后的Byte数组
     * 举例:String str = "i like like like java do you like a java"; =>  byte[] bytes = str.getBytes();
     * 返回的是这个字符串对应的赫夫曼编码后的数组
     */
    private byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1.利用huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历bytes
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        // 转成byte数组
        // 一句话搞定 int len = (stringBuilder.length()+7)/8;
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 创建 存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; // 记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 因为每8位对应一个byte,所以步长为8
            String strByte;
            if (i + 8 > stringBuilder.length()) { // 不够八位
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte转成一个byte放到数组中
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    /**
     * 封装zip方法,便于调用
     *
     * @param bytes
     * @return
     */
    private byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = this.getNodes(bytes);
        // 根据 nodes 创建的赫夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        // 对应的赫夫曼编码
        Map<Byte, String> huffmanCodeBytes = getCodes(huffmanTree);
        // 根据生产的赫夫曼编码,压缩得到压缩后的字节数组
        return this.zip(bytes, huffmanCodeBytes);
    }

    @Test
    public void test3() {
//        String str="10101000";
//        System.out.println((byte)Integer.parseInt(str,2));
        String str = "i like like like java do you like a java";
        byte[] bytes = str.getBytes();
        System.out.println("压缩前的长度" + bytes.length);
        byte[] zip = huffmanZip(bytes);
        System.out.println("压缩前的长度" + zip.length);
        System.out.println("压缩后的结果是:" + Arrays.toString(zip));
        System.out.println("网络发送");
        System.out.println("收到数据是:" + Arrays.toString(zip));
        System.out.println("解码");
    }

    //生成赫夫曼树对应的赫夫曼编码
    //1. 将赫夫曼编码存放在Map<Byte,String>形式
    //    如: 32=>01 a=>100 d=>11000 u=>11001 e=>1110 等等
    private Map<Byte, String> huffmanCodes = new HashMap<>();
    // 2.在生成赫夫曼编码表示,需要去拼接路径,定义一个StringBuilder存储某个叶子节点的路径
    private StringBuilder stringBuffer = new StringBuilder();

    // 为了调用方便,我们处在getCodes
    private Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理root的左子树
        this.getCodes(root.left, "0", stringBuffer);
        // 处理root的右子树
        this.getCodes(root.right, "1", stringBuffer);
        return this.huffmanCodes;
    }

    /**
     * TODO 功能: 将传入的Node节点的所有叶子节点的赫夫曼编码存放到 huffmanCodes集合
     *
     * @param node          传入节点
     * @param code          路径: 左子节点0 右子节点为1
     * @param stringBuilder 是用于拼接路径的
     */
    private void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) { // 如果node==null 不处理
            // 判断当前node是叶子节点还是非叶子节点
            if (node.data == null) { // 非叶子节点
                // 递归处理
                // 向左递归
                getCodes(node.left, "0", stringBuilder2);
                // 向右递归
                getCodes(node.right, "1", stringBuilder2);
            } else { // 说明是叶子节点
                // 就表示找到了某个叶子节点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }

        }
    }

    //
    private List<Node> getNodes(byte[] bytes) {
        //1.先创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<>();
        //2.遍历bytes,统计存储每个byte出现的次数->map[key,value]
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {// Map还没有这个字符数据
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }
        // 把每一个键值对转成一个Node对象,并加入到Nodes集合
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    // 编写一个前序遍历的方法
    private void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("是空树,不能遍历~");
        }
    }
}

@Getter
@Setter
class Node implements Comparable<Node> {
    public Byte data; // 存放数据(字符)本身,比如'a'=>97 ' '=>32
    public int weight;// 权值,表示字符出现的次数
    public Node left; // 指向左子节点
    public Node right;// 指向右子节点


    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }

    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight; // 从小到大排序
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}
