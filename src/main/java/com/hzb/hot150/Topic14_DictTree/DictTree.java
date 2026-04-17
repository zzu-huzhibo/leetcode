package com.hzb.hot150.Topic14_DictTree;

public class DictTree {
    // 208. 实现 Trie (前缀树)
    static class Trie {
        private final Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }
    }

    // 211. 添加与搜索单词 - 数据结构设计
    static class WordDictionary {

        private Trie dict;

        public WordDictionary() {
            dict = new Trie();
        }

        public void addWord(String word) {
            dict.insert(word);
        }

        public boolean search(String word) {
            return dfs(word, 0, dict);
        }

        public boolean dfs(String word, int index, Trie dict) {
            if (index == word.length()) {
                return dict.isEnd;
            }
            char ch = word.charAt(index);
            if (Character.isLetter(ch)) {
                int childIndex = ch - 'a';
                Trie child = dict.getChildren()[childIndex];
                return child != null && dfs(word, index + 1, child);
            } else {
                for (int i = 0; i < 26; i++) {
                    Trie child = dict.getChildren()[i];
                    if (child != null && dfs(word, index + 1, child)) {
                        return true;
                    }
                }
            }
            return false;
        }

        class Trie {
            private Trie[] children;
            private boolean isEnd;

            public Trie() {
                this.children = new Trie[26];
                this.isEnd = false;
            }

            public void insert(String word) {
                Trie node = this;
                for (int i = 0; i < word.length(); i++) {
                    char ch = word.charAt(i);
                    int index = ch - 'a';
                    if (node.children[index] == null) { //没有当前节点
                        node.children[index] = new Trie();
                    }
                    node = node.children[index];
                }
                node.isEnd = true;
            }

            public Trie[] getChildren() {
                return children;
            }

            public boolean isEnd() {
                return isEnd;
            }
        }
    }

    // TODO 212. 单词搜索 II
}
