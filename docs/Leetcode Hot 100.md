
# 动态规划
## 杨辉三角
```java
public List<List<Integer>> generate(int numRows) {
	Integer[][] dp = new Integer[numRows][];
	for (int i = 0; i < numRows; i++) {
		dp[i] = new Integer[i + 1];
		dp[i][0] = dp[i][i] = 1;
		for (int j = 1; j < i; j++) {
			dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
		}
	}
	List<List<Integer>> result = new ArrayList<>();
	for (Integer[] row : dp) {
		result.add(Arrays.asList(row));
	}
	return result;
}
```
## 完全平方数
给你一个整数 `n` ，返回 _和为 `n` 的完全平方数的最少数量_ 。

**完全平方数** 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，`1`、`4`、`9` 和 `16` 都是完全平方数，而 `3` 和 `11` 不是。
```java
public int numSquares(int n) {
	int[] dp = new int[n + 1];
	for (int i = 1; i <= n; i++) {
		int min = Integer.MAX_VALUE;
		for (int j = 1; j * j <= i; j++) {
			min = Math.min(min, dp[i - j * j]);
		}
		dp[i] = min + 1;
	}
	return dp[n];
}
```
## 乘积最大子数组
给你一个整数数组 `nums` ，请你找出数组中乘积最大的非空连续 子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

测试用例的答案是一个 **32位** 整数。

**请注意**，一个只包含一个元素的数组的乘积是这个元素的值。
```java
public int maxProduct(int[] nums) {
	
}
```

# 技巧
## 颜色分类
给定一个包含红色、白色和蓝色、共 `n` 个元素的数组 `nums` ，**[原地](https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95)** 对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 `0`、 `1` 和 `2` 分别表示红色、白色和蓝色。

必须在不使用库内置的 sort 函数的情况下解决这个问题。
```java
public void sortColors(int[] nums) {
	int n = nums.length;
	int less = 0, more = n - 1;
	int cur = 0;
	
	while (cur <= more) {
		if (nums[cur] == 0) {
			swap(nums, less++, cur++);
		} else if (nums[cur] == 2){
			swap(nums, cur, more--);
		} else {
			cur++;
		}
	}
}

private void swap(int[] arr, int i, int j) {
	int tmp = arr[i];
	arr[i] = arr[j];
	arr[j] = tmp;
}
```

## 下一个排列
整数数组的一个 **排列**  就是将其所有成员以序列或线性顺序排列。

- 例如，`arr = [1,2,3]` ，以下这些都可以视作 `arr` 的排列：`[1,2,3]`、`[1,3,2]`、`[3,1,2]`、`[2,3,1]` 。

整数数组的 **下一个排列** 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 **下一个排列** 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

- 例如，`arr = [1,2,3]` 的下一个排列是 `[1,3,2]` 。
- 类似地，`arr = [2,3,1]` 的下一个排列是 `[3,1,2]` 。
- 而 `arr = [3,2,1]` 的下一个排列是 `[1,2,3]` ，因为 `[3,2,1]` 不存在一个字典序更大的排列。

给你一个整数数组 `nums` ，找出 `nums` 的下一个排列。

必须 **[原地]** 修改，只允许使用额外常数空间。

解法

1. 首先从后向前查找第一个顺序对 (i,i+1)，满足 a[i]<a[i+1]。这样「较小数」即为 a[i]。此时 [i+1,n) 必然是下降序列。
2. 如果找到了顺序对，那么在区间 [i+1,n) 中从后向前查找第一个元素 j 满足 a[i]<a[j]。这样「较大数」即为 a[j]。
3. 交换 a[i] 与 a[j]，此时可以证明区间 [i+1,n) 必为降序。我们可以直接使用双指针反转区间 [i+1,n) 使其变为升序，而无需对该区间进行排序。

![[31.gif]]

```java
public void nextPermutation(int[] nums) {
	int i = nums.length - 2;
	// 倒序查找非降序的nums[i]
	while (i >= 0 && nums[i] >= nums[i + 1]) {
		i--;
	}
	if (i >= 0) {
		int j = nums.length - 1;
		// 倒叙查找大于nums[i]的最小值
		while (j >= 0 && nums[i] >= nums[j]) {
			j--;
		}
		// 交换nums[i], nums[j] 的值
		swap(nums, i, j);
	}
	// 反转 i + 1 到数组末尾的数字
	reverse(nums, i + 1);
}

public void swap(int[] nums, int i, int j) {
	int temp = nums[i];
	nums[i] = nums[j];
	nums[j] = temp;
}

public void reverse(int[] nums, int start) {
	int left = start, right = nums.length - 1;
	while (left < right) {
		swap(nums, left, right);
		left++;
		right--;
	}
}
```
## 寻找重复数
给定一个包含 `n + 1` 个整数的数组 `nums` ，其数字都在 `[1, n]` 范围内（包括 `1` 和 `n`），可知至少存在一个重复的整数。

假设 `nums` 只有 **一个重复的整数** ，返回 **这个重复的数** 。

你设计的解决方案必须 **不修改** 数组 `nums` 且只用常量级 `O(1)` 的额外空间。
```java
public int findDuplicate(int[] nums) {
	int slow = 0, fast = 0;
	do {
		slow = nums[slow];
		fast = nums[nums[fast]];
	} while (slow != fast);
	slow = 0;
	while (slow != fast) {
		slow = nums[slow];
		fast = nums[fast];
	}
	return slow;
}
```
# 矩阵
## 搜索二维矩阵 II
编写一个高效的算法来搜索 $m \times n$ 矩阵 `matrix` 中的一个目标值 `target` 。该矩阵具有以下特性：

- 每行的元素从左到右升序排列。
- 每列的元素从上到下升序排列。

Z 字型搜索
```java
public boolean searchMatrix(int[][] matrix, int target) {
	int m = matrix.length, n = matrix[0].length;
	int x = 0, y = n - 1;
	while (x < m && y >= 0) {
		if (matrix[x][y] == target) {
			return true;
		}
		if (matrix[x][y] > target) {
			--y;
		} else {
			++x;
		}
	}
	return false;
}
```

# 链表
## 相交链表
给你两个单链表的头节点 `headA` 和 `headB` ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 `null` 。

![[160_statement.png]]

```java
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	if (headA == null || headB == null) {
		return null;
	}
	ListNode pA = headA, pB = headB;
	while (pA != pB) {
		pA = pA == null ? headB : pA.next;
		pB = pB == null ? headA : pB.next;
	}
	return pA;
}
```
## 反转链表
```java
public ListNode reverseList(ListNode head) {
	if (head == null || head.next == null) return head;
	ListNode cur = head;
	ListNode pre = null;
	while (cur != null) {
		ListNode next = cur.next;
		cur.next = pre;
		pre = cur;

	}
	return pre;
}
```
## 回文链表
要求 `O(n)` 时间复杂度和 `O(1)` 空间复杂度
```java
public boolean isPalindrome(ListNode head) {  
    if (head == null) {  
        return true;  
    }  
  
    // 找到前半部分链表的尾节点并反转后半部分链表  
    ListNode firstHalfEnd = endOfFirstHalf(head);  
    ListNode secondHalfStart = reverseList(firstHalfEnd.next);  
  
    // 判断是否回文  
    ListNode p1 = head;  
    ListNode p2 = secondHalfStart;  
    boolean result = true;  
    while (result && p2 != null) {  
        if (p1.val != p2.val) {  
            result = false;  
        }  
        p1 = p1.next;  
        p2 = p2.next;  
    }  
  
    // 还原链表并返回结果  
    firstHalfEnd.next = reverseList(secondHalfStart);  
    return result;  
}  
  
private ListNode reverseList(ListNode head) {  
    ListNode prev = null;  
    ListNode curr = head;  
    while (curr != null) {  
        ListNode next = curr.next;  
        curr.next = prev;  
        prev = curr;  
        curr = next;  
    }  
    return prev;  
}  
  
private ListNode endOfFirstHalf(ListNode head) {  
    ListNode fast = head;  
    ListNode slow = head;  
    while (fast.next != null && fast.next.next != null) {  
        fast = fast.next.next;  
        slow = slow.next;  
    }  
    return slow;  
}
```

## 环形链表 II
给定一个链表的头节点  `head` ，返回链表开始入环的第一个节点。 _如果链表无环，则返回 `null`。_

如果链表中有某个节点，可以通过连续跟踪 `next` 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 `pos` 来表示链表尾连接到链表中的位置（**索引从 0 开始**）。如果 `pos` 是 `-1`，则在该链表中没有环。**注意：`pos` 不作为参数进行传递**，仅仅是为了标识链表的实际情况。

**不允许修改** 链表。
```java
public ListNode detectCycle(ListNode head) {  
	if (head == null) {  
	    return null;  
	}  
	ListNode slow = head, fast = head;  
	  
	while (true) {  
	    if (fast == null || fast.next == null) {  
	        return null;  
	    }  
	    fast = fast.next.next;  
	    slow = slow.next;  
	    if (fast == slow) {  
	        break;  
	    }  
	}  
	fast = head;  
	while (fast != slow) {  
	    fast = fast.next;  
	    slow = slow.next;  
	}  
	return fast;
}
```
## 两两交换链表中的节点
给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
```java
public ListNode swapPairs(ListNode head) {  
    ListNode dummy = new ListNode();  
    dummy.next = head;  
    ListNode cur = dummy;  
    while (cur.next != null && cur.next.next != null) {  
        ListNode node1 = cur.next;  
        ListNode node2 = cur.next.next;  
        cur.next = node2;  
        node1.next = node2.next;  
        node2.next = node1;  
        cur = node1;  
    }  
    return dummy.next;  
}
```

## 排序链表
给你链表的头结点 `head` ，请将其按 **升序** 排列并返回 **排序后的链表**
要求 `O(n log n)` 时间复杂度和常数级空间复杂度

```java
public ListNode sortList(ListNode head) {  
    if (head == null) {  
        return head;  
    }  
    int length = 0;  
    ListNode node = head;  
    while (node != null) {  
        length++;  
        node = node.next;  
    }  
    ListNode dummyHead = new ListNode(0, head);  
    for (int subLength = 1; subLength < length; subLength <<= 1) {  
        ListNode prev = dummyHead, curr = dummyHead.next;  
        while (curr != null) {  
            ListNode head1 = curr;  
            for (int i = 1; i < subLength && curr.next != null; i++) {  
                curr = curr.next;  
            }  
            ListNode head2 = curr.next;  
            curr.next = null;  
            curr = head2;  
            for (int i = 1; i < subLength && curr != null && curr.next != null; i++) {  
                curr = curr.next;  
            }  
            ListNode next = null;  
            if (curr != null) {  
                next = curr.next;  
                curr.next = null;  
            }  
            ListNode merged = merge(head1, head2);  
            prev.next = merged;  
            while (prev.next != null) {  
                prev = prev.next;  
            }  
            curr = next;  
        }  
    }  
    return dummyHead.next;  
}  
  
public ListNode merge(ListNode head1, ListNode head2) {  
    ListNode dummyHead = new ListNode();  
    ListNode temp = dummyHead, temp1 = head1, temp2 = head2;  
    while (temp1 != null && temp2 != null) {  
        if (temp1.val <= temp2.val) {  
            temp.next = temp1;  
            temp1 = temp1.next;  
        } else {  
            temp.next = temp2;  
            temp2 = temp2.next;  
        }  
        temp = temp.next;  
    }  
    if (temp1 != null) {  
        temp.next = temp1;  
    } else if (temp2 != null) {  
        temp.next = temp2;  
    }  
    return dummyHead.next;  
}
```

## 合并 K 个升序链表
给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。
```java
public ListNode mergeKLists(ListNode[] lists) {  
    return merge(lists, 0, lists.length - 1);  
}  
  
public ListNode merge(ListNode[] lists, int l, int r) {  
    if (l == r) {  
        return lists[l];  
    }  
    if (l > r) {  
        return null;  
    }  
    int mid = (l + r) >> 1;  
    return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));  
}  
  
public ListNode mergeTwoLists(ListNode a, ListNode b) {  
    if (a == null || b == null) {  
        return a != null ? a : b;  
    }  
    ListNode head = new ListNode(0);  
    ListNode tail = head, aPtr = a, bPtr = b;  
    while (aPtr != null && bPtr != null) {  
        if (aPtr.val < bPtr.val) {  
            tail.next = aPtr;  
            aPtr = aPtr.next;  
        } else {  
            tail.next = bPtr;  
            bPtr = bPtr.next;  
        }  
        tail = tail.next;  
    }  
    tail.next = (aPtr != null ? aPtr : bPtr);  
    return head.next;  
}
```