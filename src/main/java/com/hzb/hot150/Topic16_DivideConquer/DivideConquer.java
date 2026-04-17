package com.hzb.hot150.Topic16_DivideConquer;

import com.hzb.hot150.dataStructure.ListNode;
import com.hzb.hot150.dataStructure.TreeNode;

public class DivideConquer {
    /** -------------------------【分治】------------------------- */
    // 108. 将有序数组转换为二叉搜索树
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private static TreeNode dfs(int[] nums, int l, int r) {
        if (l > r) return null;
        int mid = (l + r) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = dfs(nums, l, mid - 1);
        root.right = dfs(nums, mid + 1, r);
        return root;
    }

    // 148. 排序链表
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        ListNode left = sortList(head);
        ListNode right = sortList(mid);
        return mergeTwoLists(left, right);
    }

    private ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode next = slow.next;
        slow.next = null;
        return next;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode cur_1 = list1, cur_2 = list2;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (cur_1 != null && cur_2 != null) {
            if (cur_1.val < cur_2.val) {
                cur.next = cur_1;
                cur = cur.next;
                cur_1 = cur_1.next;
            } else {
                cur.next = cur_2;
                cur = cur.next;
                cur_2 = cur_2.next;
            }
        }
        if (cur_1 != null) {
            cur.next = cur_1;
        }
        if (cur_2 != null) {
            cur.next = cur_2;
        }
        return dummy.next;
    }

    // 427. 建立四叉树
    static class Construct {
        class Node {
            public boolean val;
            public boolean isLeaf;
            public Node topLeft;
            public Node topRight;
            public Node bottomLeft;
            public Node bottomRight;


            public Node() {
                this.val = false;
                this.isLeaf = false;
                this.topLeft = null;
                this.topRight = null;
                this.bottomLeft = null;
                this.bottomRight = null;
            }

            public Node(boolean val, boolean isLeaf) {
                this.val = val;
                this.isLeaf = isLeaf;
                this.topLeft = null;
                this.topRight = null;
                this.bottomLeft = null;
                this.bottomRight = null;
            }

            public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
                this.val = val;
                this.isLeaf = isLeaf;
                this.topLeft = topLeft;
                this.topRight = topRight;
                this.bottomLeft = bottomLeft;
                this.bottomRight = bottomRight;
            }
        }

        public Node construct(int[][] grid) {
            int n = grid.length;
            return dfs(grid, 0, n, 0, n);
        }

        private Node dfs(int[][] grid, int r0, int r1, int c0, int c1) {
            int num = grid[r0][c0];
            boolean flag = true;
            for (int i = r0; i < r1; i++) {
                for (int j = c0; j < c1; j++) {
                    if (grid[i][j] != num) {
                        flag = false;
                        break;
                    }
                }
                if (!flag) {
                    break;
                }
            }
            if (!flag) {
                return new Node(true, false, dfs(grid, r0, (r0 + r1) / 2, c0, (c0 + c1) / 2), dfs(grid, r0, (r0 + r1) / 2, (c0 + c1) / 2, c1), dfs(grid, (r0 + r1) / 2, r1, c0, (c0 + c1) / 2), dfs(grid, (r0 + r1) / 2, r1, (c0 + c1) / 2, c1));
            }
            return new Node(num == 1, true);
        }
    }

    // 23. 合并 K 个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeLists(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        if (l < r) {
            int mid = (l + r) / 2;
            ListNode left = mergeLists(lists, l, mid);
            ListNode right = mergeLists(lists, mid + 1, r);
            return merge(left, right);
        }
        return null;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode l1_cur = l1;
        ListNode l2_cur = l2;
        while (l1_cur != null && l2_cur != null) {
            if (l1_cur.val < l2_cur.val) {
                cur.next = l1_cur;
                l1_cur = l1_cur.next;
                cur = cur.next;
            } else {
                cur.next = l2_cur;
                l2_cur = l2_cur.next;
                cur = cur.next;
            }
        }
        if (l1_cur != null) {
            cur.next = l1_cur;
        }
        if (l2_cur != null) {
            cur.next = l2_cur;
        }
        return dummy.next;
    }
}
