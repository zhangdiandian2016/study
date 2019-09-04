package study.algorithm.interview;

/**
 * 判断单向链表是否有环? <p>Q1:判断是否有环？ isCycle </> <p>Q2：环长？ count </> <p>Q3: 相遇点？ p1.data </> <p>Q4：入环点？ 头结点到入环点距离为D，入环点到相遇点距离为S1，相遇点再次回到入环点距离为S2.
 * 相遇时p1走了：D+s1,p2走了D+s1+n(s1+s2),n表示被套的圈数。 由于P2速度是P1两倍，D+s1+n(s1+s2)=2（D+s1）--》D=(n-1)(s1+s2)+s2， 即：从相遇点开始，环绕n-1圈，再次回到入环点距离。
 * 最终：只需要一个指针从头结点开始，一个指针从相遇点开始，步长都=1，这次相遇的点即为入环节点</>
 *
 * @author denny
 * @date 2019/9/4 上午10:07
 */
public class LinkedListIsCycle {

    /**
     * 判断是否有环： 1.有环返回相遇点 2.无环返回空
     *
     * @param head 头结点
     * @return
     */
    private static Node isCycle(Node head) {
        Node p1 = head;
        Node p2 = head;
        // 前进次数
        int count = 0;
        while (p2 != null && p2.next != null) {
            // P1每次前进1步
            p1 = p1.next;
            // p2每次前进2步
            p2 = p2.next.next;
            count++;
            if (p1 == p2) {
                System.out.println("1.环长=速度差*前进次数=（2-1）*前进次数=count=" + count);
                System.out.println("2.相遇点=" + p1.data);
                return p1;
            }
        }
        return null;
    }

    /**
     * 获取环入口
     *
     * @param head 头结点
     * @return
     */
    private static Node getCycleIn(Node head) {
        // 是否有环
        Node touch = isCycle(head);
        Node p1 = head;

        // 有环，只需要一个指针从头结点开始，一个指针从相遇点开始，步长都=1，这次相遇的点即为入环节点
        if (touch != null) {
            while (touch != null && p1 != null) {
                touch = touch.next;
                p1 = p1.next;
                if (p1 == touch) {
                    return p1;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        Node in = getCycleIn(node1);
        System.out.println(in != null ? "有环返回入口：" + in.data : "无环");
    }

    /**
     * 链表节点
     */
    private static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

}
