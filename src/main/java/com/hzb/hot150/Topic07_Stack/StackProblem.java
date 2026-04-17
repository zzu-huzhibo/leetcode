package com.hzb.hot150.Topic07_Stack;

import java.util.*;

public class StackProblem {
    // 20. 有效的括号
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != map.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    // 71. 简化路径
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> queue = new ArrayDeque<>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!queue.isEmpty()) {
                    queue.pollLast();
                }
            } else if (!name.isEmpty() && !".".equals(name)) {
                queue.offerLast(name);
            }
        }
        StringBuilder ans = new StringBuilder();
        if (queue.isEmpty()) {
            ans.append("/");
        } else {
            while (!queue.isEmpty()) {
                ans.append('/');
                ans.append(queue.pollFirst());
            }
        }
        return ans.toString();
    }

    // 155. 最小栈
    static class MinStack {

        private final Stack<Integer> minValStack;
        private final Stack<Integer> storeStack;

        public MinStack() {
            minValStack = new Stack<>();
            storeStack = new Stack<>();
            minValStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {

            minValStack.push(Math.min(minValStack.peek(), val));
            storeStack.push(val);
        }

        public void pop() {

            storeStack.pop();
            minValStack.pop();

        }

        public int top() {
            return storeStack.peek();
        }

        public int getMin() {
            return minValStack.peek();
        }
    }

    // 150. 逆波兰表达式求值
    public int evalRPN(String[] tokens) {
        Stack<Integer> valStack = new Stack<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                valStack.push(Integer.parseInt(token));
            } else {
                int num2 = valStack.pop();
                int num1 = valStack.pop();
                switch (token) {
                    case "+":
                        valStack.push(num1 + num2);
                        break;
                    case "-":
                        valStack.push(num1 - num2);
                        break;
                    case "*":
                        valStack.push(num1 * num2);
                        break;
                    case "/":
                        valStack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return valStack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }

    // TODO 224. 基本计算器

}
