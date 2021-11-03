public class TrieTree {
	class TrieNode {
		private boolean isEnd;
		private TrieNode[] next;
		public TrieNode() {
			isEnd = false;
			next = new TrieNode[26];
		}
	}
	private TrieNode root = new TrieNode();
	public static void main(String[] args) {
		TrieTree trieTree = new TrieTree();

		trieTree.insert("helloworld");
		System.out.println(
		    trieTree.search("helloworld") + ", " +
		    trieTree.startsWith("hellow") + ", " +
		    trieTree.search("hello")
		);
	}
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0, len = word.length(); i < len; i++) {
			int ch = word.charAt(i) - 'a';
			if (node.next[ch] == null) {
				node.next[ch] = new TrieNode();
			}
			node = node.next[ch];
		}
		node.isEnd = true;
	}
	public boolean search(String word) {
		TrieNode node = root;
		for (int i = 0, len = word.length(); i < len; i++) {
			int ch = word.charAt(i) - 'a';
			if (node.next[ch] == null)
				return false;
			node = node.next[ch];
		}
		return node.isEnd;
	}
	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (int i = 0, len = prefix.length(); i < len; i++) {
			int ch = prefix.charAt(i) - 'a';
			if (node.next[ch] == null)
				return false;
			node = node.next[ch];
		}
		return true;
	}
}