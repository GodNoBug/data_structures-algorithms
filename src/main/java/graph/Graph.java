package graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;

// 图的表示方式
// 1.二维数组表示(邻接矩阵)
// 2.链表表示(邻接)

// 邻接矩阵:是标识图形中顶点相邻关系的矩阵,对于n个顶点的图而言,矩阵是row和col表示的是1...n个点.
// 链接表:
//   1) 链接矩阵需要为每一个顶点分配n个边的空间,其实有很多边都是不存在,会造成空间的一定损失
//   2) 链接的实现只关心存在的边,不关心不存在的边.因此没有空间浪费,邻接表由数组+链表组成
//      数组位:顶点
//      数组下挂链表:链表表示能连接的地方

// 定义无向图
@Data
public class Graph {
    private ArrayList<String> vertexList;   // 存储顶点列表
    private int[][] edges;                  // 存储图对应的邻接矩阵,表示顶点之间相邻关系的矩阵
    private int numOfEdges;                 // 表示边的数目

    public static void main(String[] args) {
        int n = 5; //节点的个数
        Graph graph = new Graph(n);
        // 添加顶点
        String[] vertexes = {"A", "B", "C", "D", "E"};
        for (String vertex : vertexes) {
            graph.insertVertex(vertex);
        }
        // 添加边
        // A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();

    }

    public Graph(int n) {
        this.edges = new int[n][n];             //边的矩阵
        this.vertexList = new ArrayList<>(n);   //顶点值
        this.numOfEdges = 0;                    //多少条边
    }

    // 插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param v1     表示点的下标即是第几个顶点
     * @param v2     表示第二个顶点对应的下标
     * @param weight 权值
     */
    public void insertEdge(int v1, int v2, int weight) { // 增加节点之间的关系,增加边
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;     // 无向图的双向
        this.numOfEdges++;          // 边数++
    }

    //图中常用 的方法
    //返回节点的个数
    public int getNumOfVertex() {
        return this.vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return this.numOfEdges;
    }

    //返回节点i下标对应的数据
    public String getValueByIndex(int i) {
        return this.vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return this.edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : this.edges) {
            System.err.println(Arrays.toString(link));
        }
    }
}

// 图:
// 线性表局限于一个直接前驱和一个直接后续的关系
// 树叶只能有一个直接前驱也就是父节点
// 当我们需要表示多对多的关系时,这里我们就用到图
// 图是一种数据结构,其中结点可以具有零个或多个相邻元素.两个节点之间的连接称为边.节点也可称为顶点
// 概念:
//  顶点(vertex)/边(edge)/路径 /无向图(也可看做双向图) /有向图 /带权图(也叫网,边带权值的图)


