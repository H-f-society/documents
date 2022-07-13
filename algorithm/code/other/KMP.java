package other;


/**
 * @Author: root
 * @Date: 2022/4/2 9:24
 * @Description: KMP算法 - 串的模式匹配
 */
public class KMP {

    public static void main(String[] args) {

        String content = "BBC ABCDAB ABCDABCDABDE";
        String key = "ABCDABDc";

        System.out.println(kmp(content, key, getNext(key)));

    }

    public static int[] getNext(String key) {
        int[] next = new int[key.length()];
        next[0] = -1;
        int i = 0, j = -1;
        while (i < key.length() - 1) {
            if (j == -1 || key.charAt(i) == key.charAt(j)) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }

        return next;
    }

    public static boolean kmp(String content, String key, int[] next) {
        int i = 0, j = -1;
        while (i < content.length()) {
            if (j == -1 || content.charAt(i) == key.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (j == key.length() - 1) {
                return true;
            }
        }
        return false;
    }
}
