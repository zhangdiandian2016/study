package study.algorithm.graph;

import study.algorithm.base.StdOut;

/***
 * @Description 无向边
 * @author denny.zhang
 * @date 2020/5/25 10:34 上午
 */
public class Edge implements Comparable<Edge> {

    /**
     * 一个顶点
     */
    private final int v;
    /**
     * 另一个顶点
     */
    private final int w;
    /**
     * 权重
     */
    private final double weight;

    /**
     * Initializes an edge between vertices {@code v} and {@code w} of
     * the given {@code weight}.
     *
     * @param  v one vertex
     * @param  w the other vertex
     * @param  weight the weight of this edge
     * @throws IllegalArgumentException if either {@code v} or {@code w} 
     *         is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public Edge(int v, int w, double weight) {
        if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
        if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * Returns the weight of this edge.
     *
     * @return the weight of this edge
     */
    public double weight() {
        return weight;
    }

    /**
     * 返回边的任意一个顶点
     *
     * @return either endpoint of this edge
     */
    public int either() {
        return v;
    }

    /**
     * 返回边的另一个顶点
     *
     * @param  vertex one endpoint of this edge
     * @return the other endpoint of this edge
     * @throws IllegalArgumentException if the vertex is not one of the
     *         endpoints of this edge
     */
    public int other(int vertex) {
        if      (vertex == v) return w;
        else if (vertex == w) return v;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    /**
     * Compares two edges by weight.
     * Note that {@code compareTo()} is not consistent with {@code equals()},
     * which uses the reference equality implementation inherited from {@code Object}.
     *
     * @param  that the other edge
     * @return a negative integer, zero, or positive integer depending on whether
     *         the weight of this is less than, equal to, or greater than the
     *         argument edge
     */
    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return a string representation of this edge
     */
    public String toString() {
        return String.format("%d-%d %.5f", v, w, weight);
    }

    /**
     * Unit tests the {@code Edge} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Edge e = new Edge(12, 34, 5.67);
        StdOut.println(e);
        StdOut.println("任意一个顶点="+e.either());
        StdOut.println("另一个顶点="+e.other(12));
    }
}