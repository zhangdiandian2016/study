package study.algorithm.interview;

import study.algorithm.base.Node;

import java.util.HashMap;

/**
 * LRU（Least Recently Used）最近最少使用算法(非线程安全) head（最少使用）<-->*<-->*<-->end(最近使用) 注：JDK中LinkedHashMap实现了LRU哈希链表，构造方法：LinkedHashMap(int
 * initialCapacity容量,float
 * loadFactor负载,boolean accessOrder是否LRU访问顺序，true代表LRU)
 *
 * @author denny
 * @date 2019/9/9 下午6:01
 */
public class LRUCache {

    // 双向链表头节点（最后时间）
    private Node head;
    // 双向链表尾节点（最早时间）
    private Node end;
    // 缓存储存上限
    private int limit;
    // 无序key-value映射。只有put操作才会写hashMap
    private HashMap<String, Node> hashMap;

    public LRUCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    /**
     * 插入
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        Node node = hashMap.get(key);
        // key 不存在，插入新节点
        if (node == null) {
            // 达到容量上限
            if (hashMap.size() >= limit) {
                // 移除头结点
                String oldKey = removeNode(head);
                //同步hashMap
                hashMap.remove(oldKey);
            }
            // 构造节点
            node = new Node(key, value);
            // 添加到尾节点
            addNodeToEnd(node);
            // 同步hashmap
            hashMap.put(key, node);
        } else {
            // key存在，刷新key-value
            node.value = value;
            // 刷新被访问节点的位置
            refreshNode(node);
        }
    }

    /**
     * 获取
     *
     * @param key
     * @return
     */
    public String get(String key) {
        Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        //刷新节点（提升该节点为尾结点，即最新使用节点）
        refreshNode(node);
        return node.value;
    }

    /**
     * 刷新被访问节点的位置
     *
     * @param node
     */
    private void refreshNode(Node node) {
        // 如果访问的是尾结点，则无须移动节点
        if (node == end) {
            return;
        }
        //移除节点
        removeNode(node);
        //尾部插入节点,代表最新使用
        addNodeToEnd(node);
    }

    /**
     * 移除节点
     *
     * @param node
     * @return
     */
    private String removeNode(Node node) {
        // 如果就一个节点，把头尾节点置空
        if (node == head && node == end) {
            head = null;
            end = null;
        } else if (node == end) {
            // 移除尾结点
            end = end.next;
            end.next = null;
        } else if (node == head) {
            //移除头结点
            head = head.next;
            head.pre = null;
        } else {
            // 移除中间节点
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    /**
     * 尾部插入节点
     *
     * @param node
     */
    private void addNodeToEnd(Node node) {
        if (head == null && end == null) {
            head = node;
            end = node;
        }
        // 添加节点
        end.next = node;
        // pre=之前的end
        node.pre = end;
        // node next不存在
        node.next = null;
        // 新节点为尾结点
        end = node;
    }

    public static void printLRUCache(LRUCache lruCache) {
        for (Node node = lruCache.head; node != null; node = node.next) {
            System.out.println("key=" + node.key + ",value=" + node.value);
        }
        System.out.println("===========================");
    }

    public static void main(String[] args) {
        // 构造一个容量为5的LRU缓存
        LRUCache lruCache = new LRUCache(5);
        lruCache.put("001", "value1");
        lruCache.put("002", "value2");
        lruCache.put("003", "value3");
        lruCache.put("004", "value4");
        lruCache.put("005", "value5");
        // 打印
        System.out.println("1. 插入 5个节点");
        printLRUCache(lruCache);

        // 002到尾结点
        lruCache.get("002");
        // 打印
        System.out.println("2. 002到尾结点");
        printLRUCache(lruCache);

        // 004到尾结点，且value更新
        lruCache.put("004", "value4更新");
        // 打印
        System.out.println("3. 004到尾结点，且value更新");
        printLRUCache(lruCache);

        // 001倍移除，006在尾结点
        lruCache.put("006", "value6");
        // 打印
        System.out.println("4. 超长，001倍移除，006在尾结点");
        printLRUCache(lruCache);
    }

}
