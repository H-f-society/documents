package sort;

import java.util.Arrays;

/**
 * @Author: root
 * @Date: 2022/3/29 16:50
 * @Description: 快速排序
 */
public class QuickSort<T extends Comparable<T>> {

    public static void main(String[] args) {
        Integer[] nums = new Integer[] {5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
        String[]  strs = new String[] {"Java", "Python", "JavaScript", "C++", "PHP", "C"};

        QuickSort<Integer> numSort = new QuickSort<>();
        numSort.sort(nums, 0, nums.length - 1);

        QuickSort<String> strSort  = new QuickSort<>();
        strSort.sort(strs, 0, strs.length - 1);

        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(strs));
    }
    public void sort(T[] arr, int left, int right) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (left > right) {
            return;
        }
        // 取中间值pivot
        T pivot = arr[left + (right - left) / 2];
        int i = left;
        int j = right;
        while (i <= j) {
            // 找到左边大于pivot的值
            while (pivot.compareTo(arr[i]) > 0) {
                i++;
            }
            // 找到右边小于pivot的值
            while (pivot.compareTo(arr[j]) < 0) {
                j--;
            }
            //把左右两边找到的值交换
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        // 对左边子数组做排序处理
        if (j > left) {
            sort(arr, left, j);
        }
        // 对右边子数组做排序处理
        if (i < right) {
            sort(arr, i, right);
        }

    }
    public void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
