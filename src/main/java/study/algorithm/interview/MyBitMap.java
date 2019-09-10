package study.algorithm.interview;

/**
 * 实现一个位图BitMap（海量数据查找、去重存储）
 *
 * @author denny
 * @date 2019/9/9 下午4:04
 */
public class MyBitMap {
    // 64位二进制数据
    private long[] words;
    // Bitmap的位数
    private int size;

    public MyBitMap(int size) {
        this.size = size;
        this.words = new long[getWordIndex(size - 1) + 1];
    }

    /**
     * 判断某一位的状态
     *
     * @param index
     * @return
     */
    public boolean getBit(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index 无效！");
        }
        int wordIndex = getWordIndex(index);
        // 位与:都是1才是1，否则是0.   index对应值为1返回true
        return (words[wordIndex] & (1L << index)) != 0;
    }

    /**
     * 设置bitmap 在index处为1（true）
     *
     * @param index
     */
    public void setBit(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index 无效！");
        }
        int wordIndex = getWordIndex(index);
        // 位或：只要有一个1就是1，2个0才是0 ，因为1L << index就是1，所以|=就是在index位置，赋值1
        words[wordIndex] |= (1L << index);
    }

    /**
     * 定位Bitmap某一位对应的word
     *
     * @param index
     * @return
     */
    private int getWordIndex(int index) {
        // 右移6位即除以64
        return index >> 6;
    }

    public static void main(String[] args) {
        MyBitMap bitMap = new MyBitMap(128);
        bitMap.setBit(126);
        bitMap.setBit(88);
        System.out.println(bitMap.getBit(126));
        System.out.println(bitMap.getBit(88));
    }

}
