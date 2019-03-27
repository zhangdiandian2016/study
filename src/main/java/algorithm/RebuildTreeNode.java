package algorithm;

import lombok.Data;

import java.util.ArrayList;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * @author denny
 */
public class RebuildTreeNode {

    /**
     *  根据前序+中序-》返回二叉树
     * @param pre 前序数组
     * @param in 中序数组
     * @return
     */
    private static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    /**
     * 递归重构二叉树：对于pre数组，
     * @param pre 前序
     * @param startPre 开始下标
     * @param endPre 结束下标
     * @param in 中序
     * @param startIn 开始下标
     * @param endIn 结束下标
     * @return
     */
    private static TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {

        if (startPre > endPre || startIn > endIn){
            return null;
        }
        // 构造根节点
        TreeNode root = new TreeNode(pre[startPre]);
        // 寻找下一个根节点
        for (int i = startIn; i <= endIn; i++) {
            // 在中序中找到根节点
            if (in[i] == pre[startPre]) {
                // 核心在于startPre + （i - startIn）：下一个left元素个数=》 当前in元素个数 = i - startIn
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                // 核心在于（i - startIn） + startPre + 1：下一个right元素下标=》（i - startIn） + startPre 加1
                root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
                break;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        // 构建二叉树
        TreeNode treeNode = reConstructBinaryTree(new int[]{1, 2, 4, 7, 3, 5, 6, 8}, new int[]{4, 7, 2, 1, 5, 3, 8, 6});

        // 打印前序
        System.out.println("前序遍历：");
        TreeNode.preOrderBinaryTree(treeNode).forEach(e -> System.out.print(e + " "));

        // 打印中序
        System.out.println("\n中序遍历：");
        TreeNode.inOrderBinaryTree(treeNode).forEach(e -> System.out.print(e + " "));

        // 打印后序
        System.out.println("\n后序遍历：");
        TreeNode.postOrderBinaryTree(treeNode).forEach(e -> System.out.print(e + " "));
    }
}


@Data
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    //前序遍历二叉树
    static ArrayList<Integer> preList = new ArrayList<Integer>();
    static ArrayList<Integer> InList = new ArrayList<Integer>();
    static ArrayList<Integer> postList = new ArrayList<Integer>();

    /**
     * 前序遍历
     * @param pTreeRoot 根节点
     * @return
     */
    public static ArrayList<Integer> preOrderBinaryTree(TreeNode pTreeRoot) {
        if (pTreeRoot != null) {
            //先根结点
            preList.add(pTreeRoot.val);
            //再左边
            preOrderBinaryTree(pTreeRoot.left);
            //再右边
            preOrderBinaryTree(pTreeRoot.right);
        } else {
            return null;
        }
        return preList;
    }

    /**
     * 中序遍历二叉树
     * @param pTreeRoot
     * @return
     */
    public static ArrayList<Integer> inOrderBinaryTree(TreeNode pTreeRoot) {
        if (pTreeRoot != null) {
            //先左边
            inOrderBinaryTree(pTreeRoot.left);
            //再中间
            InList.add(pTreeRoot.val);
            //再右边
            inOrderBinaryTree(pTreeRoot.right);
        } else {
            return null;
        }
        return InList;
    }

    /**
     * 后序遍历二叉树
     * @param pTreeNode
     * @return
     */
    public static ArrayList<Integer> postOrderBinaryTree(TreeNode pTreeNode) {
        if (pTreeNode != null) {
            //先左边
            postOrderBinaryTree(pTreeNode.left);
            //再右边
            postOrderBinaryTree(pTreeNode.right);
            //再中间
            postList.add(pTreeNode.val);
        } else {
            return null;
        }
        return postList;
    }

}
