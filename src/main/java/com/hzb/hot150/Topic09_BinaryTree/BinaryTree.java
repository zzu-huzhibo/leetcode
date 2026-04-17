package com.hzb.hot150.Topic09_BinaryTree;

import com.hzb.hot150.dataStructure.NextTreeNode;
import com.hzb.hot150.dataStructure.TreeNode;

import java.util.*;

public class BinaryTree {
    // 104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDept = maxDepth(root.left);
        int rightDept = maxDepth(root.right);
        return Math.max(leftDept, rightDept) + 1;
    }

    // 100. 相同的树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        boolean l = isSameTree(p.left, q.left);
        boolean r = isSameTree(p.right, q.right);
        return l && r;
    }

    // 226. 翻转二叉树
    public void invertTree(TreeNode root) {
        if (root == null) return;
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
    }

    // 101. 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    private boolean check(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);

        while (!queue.isEmpty()) {
            p = queue.poll();
            q = queue.poll();
            if (p == null && q == null) continue;
            if (p == null || q == null || p.val != q.val) return false;
            queue.offer(p.left);
            queue.offer(q.right);

            queue.offer(p.right);
            queue.offer(q.left);
        }
        return true;
    }

    // 105. 从前序与中序遍历序列构造二叉树
    private final Map<Integer, Integer> inMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for (int i = 0; i < n; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTreeFromPreAndIn(preorder, 0, 0, n - 1);
    }

    private TreeNode buildTreeFromPreAndIn(int[] preorder, int idx, int l, int r) {
        if (l > r) return null;
        TreeNode root = new TreeNode(preorder[idx]);
        Integer i = inMap.get(preorder[idx]);
        root.left = buildTreeFromPreAndIn(preorder, idx + 1, l, i - 1);
        root.right = buildTreeFromPreAndIn(preorder, idx + i - l + 1, i + 1, r);
        return root;
    }

    // 106. 从中序与后序遍历序列构造二叉树
    public TreeNode buildTreeII(int[] inorder, int[] postorder) {
        int n = postorder.length;
        for (int i = 0; i < n; i++) {
            inMap.put(inorder[i], i);
        }
        return buildTreeFromPostAndIn(postorder, n - 1, 0, n - 1);
    }

    private TreeNode buildTreeFromPostAndIn(int[] postorder, int idx, int l, int r) {
        if (l > r) return null;
        TreeNode root = new TreeNode(postorder[idx]);
        int i = inMap.get(postorder[idx]);
        root.left = buildTreeFromPostAndIn(postorder, idx - (r - i + 1), l, i - 1);
        root.right = buildTreeFromPostAndIn(postorder, idx - 1, i + 1, r);
        return root;
    }

    // 117. 填充每个节点的下一个右侧节点指针 II
    public NextTreeNode connect(NextTreeNode root) {
        Queue<NextTreeNode> queue = new LinkedList<>();
        if (root == null) return null;
        queue.add(root);
        while (!queue.isEmpty()) {
            NextTreeNode pre = null;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NextTreeNode poll = queue.poll();
                if (i != 0) {
                    pre.next = poll;
                }
                pre = poll;

                assert poll != null;
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
            }
        }
        return root;
    }

    // 114. 二叉树展开为链表
    public void flatten(TreeNode root) {
        if (root == null) return;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
    }

    // 112. 路径总和
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        // 是叶子节点，而且节点值是 target 返回true
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        } else {
            // 不是叶子节点，判断左树或者右树满不满足
            return hasPathSum(root.right, targetSum - root.val) || hasPathSum(root.left, targetSum - root.val);
        }
    }

    // 129. 求根节点到叶节点数字之和
    static class SumNumbers {
        int sum = 0;

        public int sumNumbers(TreeNode root) {
            dfs(root, 0);
            return sum;
        }

        private void dfs(TreeNode root, int preSum) {
            if (root == null) return;
            preSum = preSum * 10 + root.val;
            if (root.left == null && root.right == null) {
                sum += preSum;
            }
            dfs(root.left, preSum);
            dfs(root.right, preSum);
        }
    }

    // 124. 二叉树中的最大路径和
    static class PathSum {
        int res = Integer.MIN_VALUE;

        public int maxPathSum(TreeNode root) {
            dfs(root);
            return res;
        }

        private int dfs(TreeNode root) {
            // 贡献值为0
            if (root == null) return 0;
            int l = Math.max(dfs(root.left), 0);
            int r = Math.max(dfs(root.right), 0);
            res = Math.max(res, root.val + l + r);
            return Math.max(l, r) + root.val;
        }
    }

    // 173. 二叉搜索树迭代器
    static class BSTIterator {
        ArrayList<Integer> list = new ArrayList<>();
        int cursor = 0;

        public BSTIterator(TreeNode root) {
            inorder(root);
        }

        private void inorder(TreeNode root) {
            if (root == null) return;
            inorder(root.left);
            list.add(root.val);
            inorder(root.right);
        }

        public int next() {
            return list.get(cursor++);
        }

        public boolean hasNext() {
            return cursor != list.size();
        }
    }

    // 222. 完全二叉树的节点个数
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int height = treeHeight(root, 1);
        return countNodeHelp(root, 1, height);
    }

    // 当前节点所在的level， height就是树高
    private int countNodeHelp(TreeNode root, int level, int height) {
        if (level == height) return 1;
        // 右树最左节点到了树高上
        if (treeHeight(root.right, level + 1) == height) {
            // 左树是满二叉树 + 递归右树
            return (1 << (height - level)) + countNodeHelp(root.right, level + 1, height);
        } else {
            // 递归左树， 右树是满二叉树 树高 [height - (level + 1)],节点数就是 2^[height - (level + 1)] - 1
            return countNodeHelp(root.left, level + 1, height) + (1 << (height - level - 1));
        }
    }

    // 走到root的最左节点，返回root的最大层级
    private int treeHeight(TreeNode root, int level) {
        if (root == null) return 0;
        while (root.left != null) {
            root = root.left;
            level++;
        }
        return level;
    }

    // 235 最近公共祖先（二叉搜索树）
    public TreeNode lowestCommonAncestor_(TreeNode root, TreeNode p, TreeNode q) {

        // root 的值不等于两个的值，若等于两个的任何一个，说明这个就是祖先
        while (root.val != p.val && root.val != q.val) {
            //   root的值在两者之间：root就是两者的祖先，跳出循环
            //   Math.min(q.val, p.val) < root.val < Math.max(p.val, q.val)
            if (root.val < Math.max(p.val, q.val) && root.val > Math.min(p.val, q.val)) {
                break;
            }
            // root.val 不在两者之间,root.val < 两者的最小值,root右移，否则root左移
            root = root.val > Math.max(p.val, q.val) ? root.right : root.left;
        }
        return root;
    }

    // 236 最近公共祖先（普通二叉树）
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case
        if (root == null || root == p || root == q) return root;
        // 搜左右
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 左找不到
        if (left == null) return right;
        // 右找不到
        if (right == null) return left;
        // 左右都搜到了，左右都不为空
        return root;
    }

    /** -------------------------【二叉树层次遍历】------------------------- */
    // 199. 二叉树的右视图
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (i == 0) res.add(node.val);
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
            }
        }
        return res;
    }

    // 637. 二叉树的层平均值
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Double> res = new ArrayList<>();
        if (root == null) return res;
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            long levelCount = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                levelCount += node.val;
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(levelCount / (double) size);
        }
        return res;
    }

    // 102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            res.add(level);
        }
        return res;
    }

    // 103. 二叉树的锯齿形层序遍历
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean l2r = true; // 从左到右遍历 left to right
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> levelList = new LinkedList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if (l2r) {
                    levelList.offerLast(node.val);
                } else {
                    levelList.offerFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.add(levelList);
            l2r = !l2r;
        }
        return res;
    }

    /** -------------------------【二叉搜索树】------------------------- */
    // 530. 二叉搜索树的最小绝对差
    static class MinimumDifference {
        TreeNode preNode;
        int result = Integer.MAX_VALUE;

        public int getMinimumDifference(TreeNode root) {
            if (root == null) return 0;
            traversal(root);
            return result;
        }

        private void traversal(TreeNode root) {
            if (root == null) return;
            traversal(root.left);
            if (preNode != null) {
                result = Math.min(result, root.val - preNode.val);
            }
            preNode = root;
            //右
            traversal(root.right);
        }
    }

    // 230. 二叉搜索树中第 K 小的元素
    static class KthSmallest {
        int res;
        int rank;

        public int kthSmallest(TreeNode root, int k) {
            traverse(root, k);
            return res;
        }

        void traverse(TreeNode root, int k) {
            if (root == null) return;
            traverse(root.left, k);
            rank++;
            if (k == rank) {
                res = root.val;
                return;
            }
            traverse(root.right, k);
        }
    }

    // 98. 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (pre != null && pre.val >= root.val) {
                return false;
            }
            pre = root;
            root = root.right;
        }
        return true;
    }
}
