package sort;

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 睡眠排序（搞笑）
 */
public class SleepSort {

    public static void main(String[] args) {

        int[] nums = {2, 1, 5, 4, 6, 3, 9, 7, 8};

        for (int num : nums) {
            new Thread(() -> {
                try {
                    Thread.sleep(num * 10);
                    System.out.print(num + ", ");
                } catch (InterruptedException ignored) {}
            }).start();
        }
    }
}
