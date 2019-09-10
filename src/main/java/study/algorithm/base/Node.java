package study.algorithm.base;

/**
 * 双向链表节点
 *
 * @author denny
 * @date 2019/9/10 下午2:20
 */
public class Node {
    public String key;
    public String value;
    public Node pre;
    public Node next;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
