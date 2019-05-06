package study.algorithm;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class TopK<T extends Comparable> {
    private PriorityQueue<T> queue;
    private int maxSize; //堆的最大容量

    /**
     * 构造优先级队列
     * @param maxSize
     */
    public TopK(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalStateException();
        }
        this.maxSize = maxSize;
        this.queue = new PriorityQueue<>(maxSize, (o1, o2) -> {
            // 最大堆用o2 - o1，最小堆用o1 - o2
            return (o1.compareTo(o2));
        });
    }

    /**
     * 添加进优先级队列
     * @param e
     */
    public void add(T e) {
        if (queue.size() < maxSize) {
            queue.add(e);
        } else {
            //返回栈顶的值 ,查看栈顶的对象而不移除它。
            T peek = queue.peek();
            if (e.compareTo(peek) > 0) {
                //获取并移除此队列的头，如果此队列为空，则返回null。
                queue.poll();
                queue.add(e);
            }
        }
    }

    /**
     * 前k个排序，由大到小
     * @return
     */
    public List<T> sortedList() {
        List<T> list = new ArrayList<>(queue);
        // 自然排序：有小到大
        Collections.sort(list);
        // 由大到小
        Collections.reverse(list);
        return list;
    }

    public List<T> getTopK(List<T> source){
        TopK pq = new TopK(maxSize);
        for (T n : source) {
            pq.add(n);
        }
        return pq.sortedList();
    }

    public static void main(String[] args) {

        System.out.println(new TopK(3).getTopK(Lists.newArrayList(4, 5, 1, 6, 2, 7, 3, 8)));

    }
}