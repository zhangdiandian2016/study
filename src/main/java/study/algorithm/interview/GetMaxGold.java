package study.algorithm.interview;

/**
 * 求金矿最优收益（动态规划） 时间复杂度：O(n*w)n为人数 w为金矿数 空间复杂度：O（n）
 *
 * @author denny
 * @date 2019/9/6 下午4:21
 */
public class GetMaxGold {

    /**
     * 求金矿最优收益
     *
     * @param w 工人数量
     * @param p 金矿开采所需的工人数量
     * @param g 金矿金子储藏量
     * @return
     */
    private static int getMaxGold(int w, int[] p, int[] g) {
        // 构造数组
        int[] results = new int[w + 1];
        // 遍历所有金矿
        for (int i = 1; i < g.length; i++) {
            // 遍历人数：w->1
            for (int j = w; j >= 1; j--) {
                // 如果人数够这个金矿所需的人数，i-1是因为下标从0开始
                if (j >= p[i - 1]) {
                    // 当前人数，最大收益=Max(采用当前矿，不采用当前矿)
                    results[j] = Math.max(results[j], results[j - p[i - 1]] + g[i - 1]);
                }
            }
        }
        // 返回最后一个格子的值
        return results[w];
    }

    public static void main(String[] args) {
        System.out.println(getMaxGold(10, new int[] {5, 5, 3, 4, 3}, new int[] {400, 500, 200, 300, 350}));
    }
}
