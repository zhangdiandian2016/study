package study.algorithm.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * A*寻路算法
 *
 * @author denny
 * @date 2019/9/10 下午5:28
 */
public class AStarSearch {

    /**
     * 迷宫地图，1代表障碍物不可走 0代表可走
     */
    private static final int[][] MAZE = {
        {0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 1, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0}
    };

    static class Grid {
        // X轴坐标
        public int x;
        // Y轴坐标
        public int y;
        // 从起点走到当前格子的成本（一开始，当前格子=起点，往后走一步，下一个格子就是当前格子）
        public int g;
        // 在不考虑障碍情况下，从当前格子走到目标格子的步数
        public int h;
        // f=g+h，从起点到当前格子，再从当前格子走到目标格子的总步数
        public int f;
        public Grid parent;

        public Grid(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void initGrid(Grid parent, Grid end) {
            //标记父格子，用来记录轨迹
            this.parent = parent;
            if (parent != null) {
                this.g = parent.g + 1;
            } else {
                this.g = 1;
            }
            this.h = Math.abs(this.x - end.x) + Math.abs(this.y - end.y);
            this.f = this.g + this.h;
        }
    }

    /**
     * A*寻路主逻辑
     *
     * @param start 起点
     * @param end   终点
     * @return
     */
    public static Grid aStarSearch(Grid start, Grid end) {
        // 可走list
        List<Grid> openList = new ArrayList<>();
        // 已走list
        List<Grid> closeList = new ArrayList<>();
        // 把起点加入openList
        openList.add(start);
        // 可走list不为空，一直循环
        while (openList.size() > 0) {
            // 在openList中查找F值最小的节点，将其作为当前格子节点
            Grid currentGrid = findMinGird(openList);
            // 将选中格子从openList中移除
            openList.remove(currentGrid);
            // 将选中格子塞进closeList
            closeList.add(currentGrid);
            // 找到所有邻近节点
            List<Grid> neighbors = findNeighbors(currentGrid, openList, closeList);
            for (Grid grid : neighbors) {
                // 邻近节点不在可走list中，标记"父节点"，GHF，并放入可走格子list
                if (!openList.contains(grid)) {
                    grid.initGrid(currentGrid, end);
                    openList.add(grid);
                }
            }
            // 如果终点在openList中，直接返回终点格子
            for (Grid grid : openList) {
                if ((grid.x == end.x) && (grid.y == end.y)) {
                    return grid;
                }
            }
        }
        // 找不到路径，终点不可达
        return null;
    }

    /**
     * 求当前可走格子的最小f的格子
     *
     * @param openList
     * @return
     */
    private static Grid findMinGird(List<Grid> openList) {
        Grid tempGrid = openList.get(0);
        // 遍历求最小f的Grid
        for (Grid grid : openList) {
            if (grid.f < tempGrid.f) {
                tempGrid = grid;
            }
        }
        return tempGrid;
    }

    /**
     * 查找可以走的格子集合
     *
     * @param grid      当前格子
     * @param openList  可走list
     * @param closeList 已走list
     * @return
     */
    private static ArrayList<Grid> findNeighbors(Grid grid, List<Grid> openList, List<Grid> closeList) {
        ArrayList<Grid> grids = new ArrayList<>();
        if (isValidGrid(grid.x, grid.y - 1, openList, closeList)) {
            grids.add(new Grid(grid.x, grid.y - 1));
        }
        if (isValidGrid(grid.x, grid.y + 1, openList, closeList)) {
            grids.add(new Grid(grid.x, grid.y + 1));
        }
        if (isValidGrid(grid.x - 1, grid.y, openList, closeList)) {
            grids.add(new Grid(grid.x - 1, grid.y));
        }
        if (isValidGrid(grid.x + 1, grid.y, openList, closeList)) {
            grids.add(new Grid(grid.x + 1, grid.y));
        }
        return grids;
    }

    /**
     * 非法校验
     *
     * @param x
     * @param y
     * @param openList
     * @param closeList
     * @return
     */
    private static boolean isValidGrid(int x, int y, List<Grid> openList, List<Grid> closeList) {
        // 坐标有效校验
        if (x < 0 || x >= MAZE.length || y < 0 || y >= MAZE[0].length) {
            return false;
        }
        // 存在障碍物，非法
        if (MAZE[x][y] == 1) {
            return false;
        }
        // 已经在openList中，已判断过
        if (containGrid(openList, x, y)) {
            return false;
        }
        // 已经在closeList中,已走过
        if (containGrid(closeList, x, y)) {
            return false;
        }
        return true;
    }

    /**
     * 是否包含坐标对应的格子
     *
     * @param grids
     * @param x
     * @param y
     * @return
     */
    private static boolean containGrid(List<Grid> grids, int x, int y) {
        for (Grid grid : grids) {
            if ((grid.x == x) && (grid.y == y)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Grid start = new Grid(2, 1);
        Grid end = new Grid(2, 5);
        // 搜索迷宫终点
        Grid resultGrid = aStarSearch(start, end);
        //回溯迷宫路径
        List<Grid> path = new ArrayList<>();
        // 追溯parent
        while (resultGrid != null) {
            path.add(new Grid(resultGrid.x, resultGrid.y));
            resultGrid = resultGrid.parent;
        }
        // 行遍历
        for (int i = 0; i < MAZE.length; i++) {
            // 列遍历
            for (int j = 0; j < MAZE[0].length; j++) {
                // 路径打印
                if (containGrid(path, i, j)) {
                    System.out.print("*,");
                } else {
                    System.out.print(MAZE[i][j] + ",");
                }
            }
            System.out.println();
        }

    }

}
