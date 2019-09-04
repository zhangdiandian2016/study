package study.algorithm.interview;

import java.util.Stack;

/**
 * 求最小栈：实现入栈、出栈、取最小值的时间复杂度都是O（1），最坏情况空间复杂度是O（n）
 *
 * @author denny
 * @date 2019/9/4 下午2:37
 */
public class MinStack {

    private Stack<Integer> mainStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    /**
     * 入栈
     *
     * @param element
     */
    private void push(int element) {
        mainStack.push(element);
        //如果最小栈为空，或者新元素<=栈顶最小值，则入最小栈
        if (minStack.empty() || element <= minStack.peek()) {
            minStack.push(element);
        }
    }

    /**
     * 出栈
     *
     * @return
     */
    private Integer pop() {
        // 如果主栈栈顶元素和最小栈元素相等，最小栈出栈
        if (mainStack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        // 主栈出栈
        return mainStack.pop();
    }

    /**
     * 取最小值
     *
     * @return
     * @throws Exception
     */
    private Integer getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(3);
        stack.push(2);
        stack.push(4);
        stack.push(1);
        stack.push(5);
        //主栈：32415 最小栈：321
        System.out.println("min=" + stack.getMin());
        stack.pop();
        stack.pop();
        System.out.println("min=" + stack.getMin());
    }
}
