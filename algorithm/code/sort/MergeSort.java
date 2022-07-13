package sort;

import java.util.Arrays;

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 归并排序
 */
public class MergeSort<T extends Comparable<T>> {

    public static void main(String[] args) {
        Integer[] nums   = new Integer[] {5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
        Integer[] result = new Integer[nums.length];

        MergeSort<Integer> mergeSort = new MergeSort<>();
        mergeSort.merge(nums, result, 0, nums.length - 1);
        System.out.println(Arrays.toString(result));


        String[] strs = new String[] {"Java", "Python", "JavaScript", "C++", "PHP", "C"};
        String[] strRes = new String[strs.length];

        MergeSort<String> strSort = new MergeSort<>();
        strSort.merge(strs, strRes, 0, strs.length - 1);
        System.out.println(Arrays.toString(strRes));

    }

    public void merge(T[] nums, T[] result, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = ((end - start) / 2) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        merge(nums, result, start1, end1);
        merge(nums, result, start2, end2);
        int i = start;
        while (start1 <= end1 && start2 <= end2) {
            result[i++] = nums[start1].compareTo(nums[start2]) < 0 ? nums[start1++] : nums[start2++];
        }
        while (start1 <= end1) {
            result[i++] = nums[start1++];
        }
        while (start2 <= end2) {
            result[i++] = nums[start2++];
        }
        for (i = start; i <= end; i++) {
            nums[i] = result[i];
        }
    }
}
