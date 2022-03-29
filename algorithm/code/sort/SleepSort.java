/* 
* @Author: root
* @Date:   2022-03-11 16:27:35
* @Last Modified by:   root
* @Last Modified time: 2022-03-29 16:51:12
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