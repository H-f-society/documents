/* 
* @Author: root
* @Date:   2022-03-20 01:14:12
* @Last Modified by:   root
* @Last Modified time: 2022-03-20 02:50:48
*/

public class TrieTree {

    static class TreeNode {
        private boolean isEnd;
        private TreeNode[] next;

        public TreeNode() {
            isEnd = false;
            next = new TreeNode[26];
        }
    }

    public static TreeNode root = new TreeNode();

    public static void main(String[] args) {

        TrieTree trieTree = new TrieTree();

        trieTree.insert(root, "hellojava");
        trieTree.insert(root, "helloworld");

        System.out.println(
            trieTree.startsWith(root, "helloj")
            + "\n" + trieTree.startsWith(root, "hellow")
            + "\n" + trieTree.startsWith(root, "hellod")
            + "\n" + trieTree.search(root, "helloworld")
            + "\n" + trieTree.search(root, "hello")
        );

    }

    public void insert(TreeNode root, String word) {
        TreeNode node = root;
        for (int i=0; i<word.length(); i++) {
            int ch = word.charAt(i) - 'a';
            if (node.next[ch] == null) {
                node.next[ch] = new TreeNode();
            }
            node = node.next[ch];
        }
        node.isEnd = true;
    }

    /**
     * 搜索完整字符串
     */
    public boolean search(TreeNode root, String word) {
        TreeNode node = root;
        for (int i = 0, len = word.length(); i < len; i++) {
            int ch = word.charAt(i) - 'a';
            if (node.next[ch] == null)
                return false;
            node = node.next[ch];
        }
        return node.isEnd;
    }

    /**
     * 搜索字符串前缀
     */
    public boolean startsWith(TreeNode root, String prefix) {
        TreeNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int ch = prefix.charAt(i) - 'a';
            if (node.next[ch] == null)
                return false;
            node = node.next[ch];
        }
        return true;
    }
}