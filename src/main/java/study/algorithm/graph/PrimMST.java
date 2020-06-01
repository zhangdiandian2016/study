package study.algorithm.graph;

import study.algorithm.base.*;

import java.util.Arrays;

/***
 * @Description 使用Prim算法计算一棵最小生成树
 * 入参：
 * 8 16
 * 4 5 0.35
 * 4 7 0.37
 * 5 7 0.28
 * 0 7 0.16
 * 1 5 0.32
 * 0 4 0.38
 * 2 3 0.17
 * 1 7 0.19
 * 0 2 0.26
 * 1 2 0.36
 * 1 3 0.29
 * 2 7 0.34
 * 6 2 0.40
 * 3 6 0.52
 * 6 0 0.58
 * 6 4 0.93
 *
 * @author denny.zhang
 * @date 2020/5/26 9:50 上午
 */
public class PrimMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    /**
     * 顶点索引，树顶点到非树顶点的最短边(距离树最近的边)
     */
    private Edge[] edgeTo;
    /**
     * 顶点索引，最短边的权重
     */
    private double[] distTo;
    /**
     * 顶点索引，标记顶点是否在最小生成树中
     */
    private boolean[] marked;
    /**
     * 有效的横切边（索引最小优先队列，索引为顶点v,值pq[v]=edgeTo[v].weight()=distTo[v]）
     */
    private IndexMinPQ<Double> pq;

    /**
     * 计算一个边加权图的最小生成树
     * @param G the edge-weighted graph
     */
    public PrimMST(EdgeWeightedGraph G) {
        // 初始化3个顶点索引数组
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        // 初始化：顶点索引最小优先队列
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++) {
            // 初始化为无穷大
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        // 遍历顶点数
        for (int v = 0; v < G.V(); v++)
        {
            // 如果顶点0开始能全进树，那就一次搞定
            StdOut.println("v="+ v+",marked[v]="+marked[v]);
            // 如果没进树
            if (!marked[v]) {
                StdOut.println("v="+v+"，执行prim");
                // 最小生成树
                prim(G, v);
            }
        }

        // 校验
        assert check(G);
    }


    /**
     * 从顶点s开始生成图G
     * @param G
     * @param s
     */
    private void prim(EdgeWeightedGraph G, int s) {
        // 顶点s的权重=0
        distTo[s] = 0.0;
        // 顶点s进队列，key为索引，value为边权重
        pq.insert(s, distTo[s]);
        StdOut.println("顶点s进队列:  s="+ s+",distTo[s]="+distTo[s]);
        StdOut.println("pq="+ Arrays.toString(pq.keys));

        // 循环
        while (!pq.isEmpty()) {
            // 取出最小权重边的顶点（最近的顶点）
            int v = pq.delMin();
            // 添加到树中
            scan(G, v);
        }
    }

    /**
     * 将顶点V添加到树中，更新数据
     * @param G
     * @param v
     */
    private void scan(EdgeWeightedGraph G, int v) {
        StdOut.println("v="+ v+",进树");
        // 标记 进树
        marked[v] = true;
        // 遍历顶点v的邻接边
        for (Edge e : G.adj(v)) {
            StdOut.println("遍历顶点v的邻接边：v="+ v+",e="+e.toString());
            // 另一个顶点
            int w = e.other(v);
            StdOut.println("w=" + w);
            // 如果w已进树，跳过（至少有一个点不在树中，计算才有意义）
            if (marked[w]) {
                StdOut.println("已进树，跳过w=" + w);
                continue;
            }
            // 如果边e的权重 < 当前到顶点w的权重
            if (e.weight() < distTo[w]) {
                StdOut.println("e.weight()="+e.weight()+" ，distTo[w]=" + distTo[w]);
                // 更新最小权重
                distTo[w] = e.weight();
                // 连接w和树的最佳边变为e
                edgeTo[w] = e;
                // 顶点w在pq队列中
                if (pq.contains(w)) {
                    StdOut.println("顶点w在pq队列中：w="+w);
                    // 减小w索引对应的权重值，小值上浮
                    pq.decreaseKey(w, distTo[w]);
                 // 顶点w不在队列中
                } else {
                    StdOut.println("顶点w不在pq队列中，插入队列前：w="+w+"，pq="+ Arrays.toString(pq.keys));
                    // 插入队列
                    pq.insert(w, distTo[w]);
                    StdOut.println("顶点w不在pq队列中，插入队列后：w="+w+"，pq="+Arrays.toString(pq.keys));
                }
            }
        }
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }


    // check optimality conditions (takes time proportional to E V lg* V)
    private boolean check(EdgeWeightedGraph G) {

        // check weight
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
            return false;
        }

        // check that it is acyclic
        UF uf = new UF(G.V());
        for (Edge e : edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : G.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new UF(G.V());
            for (Edge f : edges()) {
                int x = f.either(), y = f.other(x);
                if (f != e) {
                    uf.union(x, y);
                }
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (uf.find(x) != uf.find(y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }

    public static void main(String[] args) {
        // 读取图文件
        In in = new In(args[0]);
        // 初始化：边加权无向图
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        // 核心算法Prim
        PrimMST mst = new PrimMST(G);
        // 打印全部边
        for (Edge e : mst.edges()) {
            StdOut.println(e);
        }
        // 打印总权重
        StdOut.printf("%.5f\n", mst.weight());
    }


}