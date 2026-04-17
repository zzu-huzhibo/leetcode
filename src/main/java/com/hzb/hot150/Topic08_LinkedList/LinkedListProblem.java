package com.hzb.hot150.Topic08_LinkedList;

import com.hzb.hot150.dataStructure.ListNode;
import com.hzb.hot150.dataStructure.RandomListNode;

import java.util.HashMap;
import java.util.Map;

public class LinkedListProblem {
    // 141. 环形链表
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    // 2. 两数相加
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode iter = dummy;
        int carry = 0;
        int x = 0;
        int y = 0;
        int sum = 0;
        while (l1 != null || l2 != null) {
            x = l1 == null ? 0 : l1.val;
            y = l2 == null ? 0 : l2.val;
            sum = carry + x + y;
            carry = sum / 10;
            iter.next = new ListNode(sum % 10);
            iter = iter.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) {
            iter.next = new ListNode(carry);
        }
        return dummy.next;
    }

    // 21. 合并两个有序链表
    public ListNode mergeTwoLists_NoSentinel(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) return list1 == null ? list2 : list1;
        ListNode head = list1.val < list2.val ? list1 : list2;
        ListNode c1 = head.next;
        ListNode c2 = head == list1 ? list2 : list1;
        ListNode pre = head;
        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                pre.next = c1;
                c1 = c1.next;
            } else {
                pre.next = c2;
                c2 = c2.next;
            }
            pre = pre.next;
        }
        pre.next = c1 == null ? c2 : c1;
        return head;
    }

    // 138. 随即链表的复制
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cur = head;
        while (cur != null) {
            map.put(cur, new RandomListNode(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            RandomListNode node = map.get(cur);
            node.next = map.get(cur.next);
            node.random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    // 92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode pre = dummyNode;
        // 走到需要反转的前一个节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        // cur指向需要反转的第一个节点
        ListNode cur = pre.next;
        ListNode next;
        // 头插法
        for (int i = left; i < right; i++) {
            next = cur.next;
            cur.next = pre.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }

    // 25. K个一组反转链表
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode next;
        int length = 0;
        // 求出链表长度
        ListNode cur = head;
        while (cur != null) {
            cur = cur.next;
            length++;
        }
        // 遍历指针cur重新指向head
        cur = head;
        // length/k 总共执行length/k次
        for (int i = 1; i <= length / k; i++) {
            // 具体的链表反转逻辑
            for (int j = 1; j < k; j++) {
                next = cur.next;
                cur.next = next.next;
                next.next = prev.next;
                prev.next = next;
            }
            // 翻转完一组更新prev和cur
            prev = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    // 19. 删除链表的倒数第N个节点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = dummy;
        int k = n;
        while (k > 0) {
            fast = fast.next;
            k--;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }

    // 82. 删除排序链表中的重复元素 II
    public ListNode deleteDuplicatesII(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next; // 删除
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    // 61. 旋转链表
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }

        k = k % length;
        int i = length - k;

        node = head;
        while (--i > 0) {
            node = node.next;
        }
        ListNode newHead = node.next;
        node.next = null;
        ListNode tmp = newHead;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = head;
        return newHead;
    }

    // 86. 分隔链表
    public ListNode partition(ListNode head, int x) {
        ListNode dummy = new ListNode(0, head);
        ListNode lessHead = new ListNode(0);
        ListNode lessCur = lessHead;
        ListNode moreHead = new ListNode(0);
        ListNode moreCur = moreHead;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                lessCur.next = cur;
                lessCur = lessCur.next;
            } else {
                moreCur.next = cur;
                moreCur = moreCur.next;
            }
            cur = cur.next;
        }
        moreCur.next = null;
        lessCur.next = moreHead.next;
        return lessHead.next;
    }

    // 146. LRU缓存
    static class LRUCache {
        // 双链表节点
        static class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private final Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
        private int size;
        private final int capacity;
        private final DLinkedNode head;
        private final DLinkedNode tail;

        public LRUCache(int capacity) {
            this.size = 0;
            this.capacity = capacity;
            head = new DLinkedNode();
            tail = new DLinkedNode();
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);
            if (node == null) {
                DLinkedNode newNode = new DLinkedNode(key, value);
                cache.put(key, newNode);
                addToHead(newNode);
                size++;
                if (size > capacity) {
                    DLinkedNode tail = removeTail();
                    cache.remove(tail.key);
                    size--;
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }

        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(DLinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void addToHead(DLinkedNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private DLinkedNode removeTail() {
            DLinkedNode res = tail.prev;
            removeNode(res);
            return res;
        }
    }
}
