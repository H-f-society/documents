package other;

import java.util.*;

/**
 * @Author: root
 * @Date: 2022/4/6 14:56
 * @Description: 全排列
 */
public class Permute {
    public static int[] nums = {1, 2, 3, 4};

    public static void main(String[] args) {
        List<List<Integer>> result = new ArrayList<>();
        Permute1(nums, 0, nums.length - 1, result);
        System.out.println(result);
        System.out.println(result.size());
    }

    public static void Permute1(int[] nums, int start, int end, List<List<Integer>> result) {
        // 递归实现
        List<Integer> list = new ArrayList<>();
        if (start == end) {
            for (int i : nums) {
                list.add(i);
            }
            if (!result.contains(list)) {
                result.add(list);
            }
            return;
        }
        for (int i = start; i <= end; i++) {
            //该条件避免[1, 1]重复
            if (i == start || nums[i] != nums[start]) {
                swap(nums, start, i);
                Permute1(nums, start + 1, end, result);
                swap(nums, start, i);
            }
        }
    }

    public static void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
