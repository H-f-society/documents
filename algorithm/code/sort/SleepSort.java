/* 
* @Author: root
* @Date:   2022-03-11 16:27:35
* @Last Modified by:   root
* @Last Modified time: 2022-03-11 16:46:01
*/

public class SleepSort {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 5, 4, 6, 3, 9, 7, 8};
        for (int i = 0; i < nums.length; i++) {
            int index = i;
            new Thread() {
                public void run() {
                    try {
                        sleep(nums[index] * 10);
                        System.out.print(nums[index] + ", ");
                    } catch (InterruptedException e) {
                    }
                }
            }.start();
        }
    }
}