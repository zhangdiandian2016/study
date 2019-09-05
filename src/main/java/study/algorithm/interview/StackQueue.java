package study.algorithm.interview;

import java.util.Stack;

/**
 * 栈实现队列：时间复杂度：入队O(1) 出队O(1)（均摊时间复杂度）
 *
 * @author denny
 * @date 2019/9/5 上午11:14
 */
public class StackQueue {
    // 入队
    private Stack<Integer> stackIn = new Stack<>();
    // 出队
    private Stack<Integer> stackOut = new Stack<>();

    /**
     * 入队：直接入栈
     *
     * @param element
     */
    private void enQueue(int element) {
        stackIn.push(element);
    }

    /**
     * 出队
     *
     * @return
     */
    private Integer deQueue() {
        // 出队为空
        if (stackOut.isEmpty()) {
            // 如果入队为空，直接返回空
            if (stackIn.isEmpty()) {
                return null;
            }
            // 入队不为空，IN元素全部转移到OUT
            transfer();
        }
        // 出队不为空，直接弹出
        return stackOut.pop();
    }

    /**
     * 入队元素转到出队
     */
    private void transfer() {
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }

    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue();
        stackQueue.enQueue(1);
        stackQueue.enQueue(2);
        stackQueue.enQueue(3);
        System.out.println("出队：" + stackQueue.deQueue());
        System.out.println("出队：" + stackQueue.deQueue());
        stackQueue.enQueue(4);
        System.out.println("出队：" + stackQueue.deQueue());
        System.out.println("出队：" + stackQueue.deQueue());

    }

}
