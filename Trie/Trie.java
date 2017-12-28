import java.util.*;

public class Trie {
    private class TrieNode {
        Map<Character, TrieNode> children;
        boolean endOfWord;
        public TrieNode() {
            children = new HashMap<>();
            endOfWord = false;
        }
    }

    private final TrieNode root;
    public Trie() {
        root = new TrieNode();
    }

    // Insert
    // Iterative implementation of insert
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.children.put(ch, node);
            }
            current = node;
        }
        current.endOfWord = true;
    }

    // Recursive implementation of insert
    public void insertRecursive(String word) {
        insertUtil(root, word, 0);
    }

    public void insertUtil(TrieNode current, String word, int index) {
        if (word.length() == index) {
            current.endOfWord = true;
            return;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            node = new TrieNode();
            current.children.put(ch, node);
        }
        insertUtil(node, word, index + 1);
    }



    // Search
    // Iterative implementation of search
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return current.endOfWord;
    }

    // Recursive implementation of search
    public boolean searchRecursive(String word) {
        return searchUtil(root, word, 0);
    }

    public boolean searchUtil(TrieNode current, String word, int index) {
        if (word.length() == index) {
            return current.endOfWord;
        }

        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        return searchUtil(node, word, index + 1);
    }

    // StartWith
    // Iterative implementation of startWith
    public boolean startWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            TrieNode node = current.children.get(ch);
            if (node == null) {
                return false;
            }
            current = node;
        }
        return true;
    }

    // Recursive implementation of startWith
    public boolean startWithRecursive(String prefix) {
        return startWithUtil(root, prefix, 0);
    }

    public boolean startWithUtil(TrieNode current, String prefix, int index) {
        if (prefix.length() == index) {
            return true;
        }
        char ch = prefix.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        return startWithUtil(node, prefix, index + 1);
    }

    // Delete
    public void delete(String word) {
        deleteUtil(root, word, 0);
        return;
    }

    public boolean deleteUtil (TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.endOfWord) {
                return false;
            }
            current.endOfWord = false;
            return current.children.size() == 0;
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = deleteUtil(node, word, index + 1);
        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.size() == 0;
        }
        return false;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("abc");
        t.insertRecursive("abgl");
        t.insertRecursive("cdf");
        t.insert("abcd");
        t.insert("lmn");
        System.out.println(t.searchRecursive("abc"));
        System.out.println(t.search("abgl"));
        System.out.println(t.searchRecursive("cdf"));
        System.out.println(t.startWith("ab"));
        System.out.println(t.startWithRecursive("abg"));
        t.delete("lmn");
        System.out.println(t.search("lmn"));
    }
}