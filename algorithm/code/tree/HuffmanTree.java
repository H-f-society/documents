import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Author: root
 * @Date: 2022/4/1 10:07
 * @Description: 哈夫曼编码 - huffman - tree / code / decode
 */
public class HuffmanTree {

    static class TreeNode<T extends Comparable<T>> {
        T value;
        int count;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T value, Integer count) {
            this.value = value;
            this.count = count;
        }

        public TreeNode(TreeNode<T> left, TreeNode<T> right) {
            this.count = (left != null ? left.count : 0) + (right != null ? right.count : 0);
            this.left = left;
            this.right = right;
        }

    }

    /**
     * 构建huffman数
     * @param content 字符串文本内容
     * @return huffman树
     */
    public static TreeNode<Character> createHuffmanTree(String content) {
        // 对content内容中每个字符计数
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            map.put(ch, map.containsKey(ch) ? map.get(ch) + 1 : 1);
        }

        // 优先队列 - 小顶堆
        PriorityQueue<TreeNode<Character>> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.count)
        );
        for (Character key : map.keySet()) {
            priorityQueue.add(new TreeNode<>(key, map.get(key)));
        }

        // 开始构建huffman编码，每两个为一组
        int index = 0;
        TreeNode<Character> left = null;
        TreeNode<Character> right = null;
        while (priorityQueue.size() > 1) {
            index++;
            left = index == 1 ? priorityQueue.remove() : left;
            right = index == 2 ? priorityQueue.remove() : right;
            if (index == 2) {
                priorityQueue.add(new TreeNode<>(left, right));
                index = 0;
                left = null;
                right = null;
            }
        }
        priorityQueue.add(new TreeNode<>(left, priorityQueue.remove()));
        return priorityQueue.remove();
    }

    /**
     * 遍历huffman tree的叶子结点全路径，向左为0，向右为1
     *
     * @param root huffman树
     * @param map  huffman编码表
     * @param path huffman叶子节点全路径
     */
    public static void huffmanCode(TreeNode<Character> root, Map<Character, String> map, String path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            map.put(root.value, path);
        } else {
            huffmanCode(root.left, map, path + "0");
            huffmanCode(root.right, map, path + "1");
        }
    }

    /**
     * huffman编码解码，还原字符串文本
     *
     * @param root huffman树
     * @param code huffman编码
     * @param result huffman编码解码后文本
     */
    public static void huffmanDecode(TreeNode<Character> tree, TreeNode<Character> root, String code, int index, StringBuilder result) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            result.append(root.value);
            huffmanDecode(tree, tree, code, index, result);
        }
        if (index >= code.length()) return;
        if (code.charAt(index) == '0') {
            huffmanDecode(tree, root.left, code, index + 1, result);
        }
        if (code.charAt(index) == '1') {
            huffmanDecode(tree, root.right, code, index + 1, result);
        }
    }

    public static void main(String[] args) {

        String content = "huffmantree";

        TreeNode<Character> huffmanTree = createHuffmanTree(content);

        Map<Character, String> huffmanCodeMap = new HashMap<>();
        huffmanCode(huffmanTree, huffmanCodeMap, "");

        for (Character code : huffmanCodeMap.keySet()) {
            System.out.print("[" + code + ":" + huffmanCodeMap.get(code) + "], ");
        }
        System.out.println();
        StringBuilder hfcode = new StringBuilder();
        for (int i=0; i<content.length(); i++) {
            hfcode.append(huffmanCodeMap.get(content.charAt(i)));
        }
        System.out.println(hfcode);

        StringBuilder hfdecode = new StringBuilder();
        huffmanDecode(huffmanTree, huffmanTree, hfcode.toString(), 0, hfdecode);
        System.out.println(hfdecode);
    }
}
