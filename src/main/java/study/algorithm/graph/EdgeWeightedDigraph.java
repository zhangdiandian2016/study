package study.algorithm.graph;

import study.algorithm.base.*;

import java.util.NoSuchElementException;

/***
 * @Description 边权重有向图
 * @author denny.zhang
 * @date 2020/4/24 9:58 上午
 */
public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.getProperty("line.separator");

    /**
     * 顶点总数
     */
    private final int V;
    /**
     * 边总数
     */
    private int E;
    /**
     * 邻接表（每个元素Bag代表 由某个顶点为起点的边数组,按顶点顺序排列），adjacency list
     */
    private Bag<DirectedEdge>[] adj;

    /**  
     * 从输入流中初始化图，输入流格式：
     * 8（顶点数）
     * 15（边总数）
     * 4 5 0.35（每一条边 4->5 权重0.35）
     * 5 4 0.35
     * 4 7 0.37
     * ...
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     */
    public EdgeWeightedDigraph(In in) {
        if (in == null) {
            throw new IllegalArgumentException("argument is null");
        }
        try {
            // 1.读取顶点数
            this.V = in.readInt();
            if (V < 0) {
                throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
            }
            // 初始化邻接表
            adj = (Bag<DirectedEdge>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<DirectedEdge>();
            }
            // 2.读取边数
            int E = in.readInt();
            if (E < 0) {
                throw new IllegalArgumentException("Number of edges must be nonnegative");
            }
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                validateVertex(v);
                validateVertex(w);
                // 3.读取边的权重
                double weight = in.readDouble();
                // 添加权重边
                addEdge(new DirectedEdge(v, w, weight));
            }
        }   
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in EdgeWeightedDigraph constructor", e);
        }
    }

    /**
     * 顶点数
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * 边数
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int E() {
        return E;
    }

    /**
     * 校验顶点合法
     * @param v
     */
    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
        }
    }

    /**
     * 往图中添加边
     *
     * @param  e the edge
     * @throws IllegalArgumentException unless endpoints of edge are between {@code 0}
     *         and {@code V-1}
     */
    public void addEdge(DirectedEdge e) {
        // 边的起点
        int v = e.from();
        // 边的终点
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        // 起点v的邻接表，加入一条边
        adj[v].add(e);
        // 边总数+1
        E++;
    }


    /**
     * 返回从顶点V 指出的全部可迭代边（邻接表）
     *
     * @param  v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 返回全部有向边
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        // 遍历全部顶点
        for (int v = 0; v < V; v++) {
            // 每个顶点的邻接表（指出边）
            for (DirectedEdge e : adj(v)) {
                // 指出边入list
                list.add(e);
            }
        }
        return list;
    } 

    /**
     * Returns a string representation of this edge-weighted digraph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    /**
     * Unit tests the {@code EdgeWeightedDigraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
        StdOut.println(G);
    }

}
