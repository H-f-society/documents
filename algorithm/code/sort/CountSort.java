package sort;

import java.util.Arrays;

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 计数排序
 */
public class CountSort {

    public static void main(String[] args) {
        int[] nums = {6, 7, 4, 9, 6, 1, 5, 3, 0, 1, 2, 9, 6, 8};
        int[] temp = new int[10];
        for (int num : nums) {
            temp[num]++;
        }
        int k = 0;
        for (int i = 0; i < temp.length; i++) {
            while ((temp[i]--) > 0) {
                nums[k++] = i;
            }
        }
        System.out.println(Arrays.toString(nums));
    }
}
