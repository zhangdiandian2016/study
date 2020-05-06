package study.algorithm.graph;

import study.algorithm.base.In;
import study.algorithm.base.IndexMinPQ;
import study.algorithm.base.Stack;
import study.algorithm.base.StdOut;

/***
 * @Description 边权重非负的加权有向图的单起点最短路径树
 * @author denny.zhang
 * @date 2020/4/23 11:29 上午
 */
public class DijkstraSP {

    /**
     * 最短距离数组，元素：原点到所有顶点的最短距离
     */
    private double[] distTo;

    /**
     * 有向边数组：最短路径最后一条边数组，每个顶点都有一条这样的边，就组成了最短路径树。
     */
    private DirectedEdge[] edgeTo;

    /**
     * 索引最小优先级队列 值=顶点
     */
    private IndexMinPQ<Double> pq;

    /**
     * 计算从原点S 到 其它所有顶点 的"最短路径" 边权重 图
     *
     * @param  G the edge-weighted digraph 边权重图
     * @param  s the source vertex 原点
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DijkstraSP(EdgeWeightedDigraph G, int s) {
        // 负权重校验
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException("edge " + e + " has negative weight");
            }
        }
        // 最短路径数组长度=顶点个数
        distTo = new double[G.V()];
        // 构造长度为顶点总数的最短路径边数组
        edgeTo = new DirectedEdge[G.V()];
        // 校验原点值
        validateVertex(s);
        // 初始化所有顶点的路径为无穷大
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        // 初始化到原点最小路径为0
        distTo[s] = 0.0;

        // 构造一个长度为 顶点总数的 索引最小优先队列
        pq = new IndexMinPQ<Double>(G.V());
        // 把原点插入，路径为0
        pq.insert(s, distTo[s]);
        // 只要队列不空(从上往下，顺序遍历一遍pq[])，
        while (!pq.isEmpty()) {
            // 删除最小key（即pq[1]），并返回最小值（顶点）
            int v = pq.delMin();
            // 遍历顶点v的邻接表，每一条边
            for (DirectedEdge e : G.adj(v)) {
                // 放松边
                relax(e);
            }
        }

        // 校验
        assert check(G, s);
    }

    /**
     * 放松并更新pq
     * @param e
     */
    private void relax(DirectedEdge e) {
        // 起点、终点
        int v = e.from(), w = e.to();
        // 如果原点到终点w的距离 > 原点到起点v的距离+边权重  说明原点到w松弛了
        if (distTo[w] > distTo[v] + e.weight()) {
            // 最新距离
            distTo[w] = distTo[v] + e.weight();
            // 到终点w的边赋值为新边
            edgeTo[w] = e;
            // 如果优先队列已经包含终点w
            if (pq.contains(w)) {
                // 比较下标为w的key如果>当前路径（即当前值比队列中值小），重新排序
                pq.decreaseKey(w, distTo[w]);
            } else {
                // 不包含，插入并排序
                pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     * s->v的最短路径
     * @param  v the destination vertex
     * @return the length of a shortest path from the source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public double distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * s->v是否可达
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    /**
     * s->v的最短可迭代边（1->2->3）
     *
     * @param  v the destination vertex
     * @return a shortest path from the source vertex {@code s} to vertex {@code v}
     *         as an iterable of edges, and {@code null} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) {
            return null;
        }
        // 可迭代有向边栈
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        // e是顶点v的最短路径树的最后一条边，沿着边往上追溯上一个顶点 3->2->1
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            // 压栈
            path.push(e);
        }
        return path;
    }


    // check optimality conditions:
    // (i) for all edges e:            distTo[e.to()] <= distTo[e.from()] + e.weight()
    // (ii) for all edge e on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight()
    private boolean check(EdgeWeightedDigraph G, int s) {

        // 校验边权重不为负值
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // 校验到顶点的路径为0且到顶点的边为空
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        // 遍历顶点
        for (int v = 0; v < G.V(); v++) {
            // 起点跳过
            if (v == s) {
                continue;
            }
            // 到顶点v的最后一条边为空（不可达） 且 到顶点v的最短路径不是无穷大（即有值）两者冲突
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // 校验所有边非松弛
        for (int v = 0; v < G.V(); v++) {
            // 遍历顶点v的邻接边
            for (DirectedEdge e : G.adj(v)) {
                int w = e.to();
                // 校验松弛
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // 校验最短路径树：满足 distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < G.V(); w++) {
            // 跳过不可达顶点
            if (edgeTo[w] == null) {
                continue;
            }
            // 最后一条边
            DirectedEdge e = edgeTo[w];
            // 起点
            int v = e.from();
            //终点
            if (w != e.to()) {
                return false;
            }
            // 校验：最短路劲树，起点路径+权重=终点路径
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    /**
     * Unit tests the {@code DijkstraSP} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // 图文件名称
        In in = new In(args[0]);
        // 构造边权重有向图
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        // 顶点
        int s = Integer.parseInt(args[1]);

        // 计算最短路径
        DijkstraSP sp = new DijkstraSP(G, s);

        // 遍历所有顶点
        for (int t = 0; t < G.V(); t++) {
            // 可达
            if (sp.hasPathTo(t)) {
                // 原点到t的路径 长度
                StdOut.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                // 原点到t的路径图
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + "   ");
                }
                // 换行
                StdOut.println();
            }
            // 不可达
            else {
                StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }

}