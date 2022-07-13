package tree;

import java.util.*;

/**
 * @Author: root
 * @Date: 2022/4/1 10:07
 * @Description: 哈夫曼编码 - huffman - tree / code / decode
 */
public class HuffmanTree<T extends Comparable<T>> {

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    private static class TreeNode<T> {
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
     *
     * @param content 字符串文本内容
     * @return huffman树
     */
    public TreeNode<T> createHuffmanTree(T[] content) {
        // 对content内容中每个字符计数
        Map<T, Integer> map = new HashMap<>(16);
        for (T key : content) {
            map.put(key, map.containsKey(key) ? map.get(key) + 1 : 1);
        }

        // 优先队列 - 小顶堆
        PriorityQueue<TreeNode<T>> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.count)
        );
        for (T key : map.keySet()) {
            priorityQueue.add(new TreeNode<>(key, map.get(key)));
        }

        // 开始构建huffman编码，每两个为一组
        int index = 0;
        TreeNode<T> left = null;
        while (priorityQueue.size() > 1) {
            index++;
            left = index == 1 ? priorityQueue.remove() : left;
            if (index == 2) {
                priorityQueue.add(new TreeNode<>(left, priorityQueue.remove()));
                index = 0;
                left = null;
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
    public void huffmanCode(TreeNode<T> root, Map<T, String> map, String path) {
        if (root == null) {
            return;
        }
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
     * @param root   huffman树
     * @param code   huffman编码
     * @param result huffman编码解码后文本
     */
    public void huffmanDecode(TreeNode<T> tree, TreeNode<T> root, List<Byte> code, int index, StringBuilder result) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            result.append(root.value);
            // 从根节点重新开始向下搜索
            huffmanDecode(tree, tree, code, index, result);
        }
        if (index >= code.size()) {
            return;
        }
        if (code.get(index) == LEFT) {
            huffmanDecode(tree, root.left, code, index + 1, result);
        }
        if (code.get(index) == RIGHT) {
            huffmanDecode(tree, root.right, code, index + 1, result);
        }
    }

    public static void main(String[] args) {

        HuffmanTree<Character> huffmanTree = new HuffmanTree<>();

        String content = "HuffmanTree";

        // 创建huffman树
        TreeNode<Character> treeNode = huffmanTree.createHuffmanTree(content.chars().mapToObj(c -> (char) c).toArray(Character[]::new));

        Map<Character, String> huffmanCodeMap = new HashMap<>(16);
        huffmanTree.huffmanCode(treeNode, huffmanCodeMap, "");

        for (Character code : huffmanCodeMap.keySet()) {
            System.out.print("[" + code + ":" + huffmanCodeMap.get(code) + "], ");
        }
        System.out.println();

        // 字符串转huffman编码
        List<Byte> hfCode = new ArrayList<>();
        for (int i = 0; i < content.length(); i++) {
            String code = huffmanCodeMap.get(content.charAt(i));
            for (int j = 0; j < code.length(); j++) {
                hfCode.add((byte) (code.charAt(j) - 48));
            }
        }
        System.out.println(hfCode.toString().replace(", ", "").replace("[", "").replace("]", ""));

        // huffman编码还原字符串
        StringBuilder hfDecode = new StringBuilder();
        huffmanTree.huffmanDecode(treeNode, treeNode, hfCode, 0, hfDecode);
        System.out.println(hfDecode);

        // 转ascii转二进制
        System.out.println(Arrays.toString(content.getBytes()));
        for (int i = 0; i < content.length(); i++) {
            System.out.print(Integer.toBinaryString(content.getBytes()[i]));
        }

    }
}
