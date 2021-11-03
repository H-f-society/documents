import java.util.List;
import java.util.ArrayList;

public class QuickSort {

	public static void main(String[] args) {
		int[] nums = {5, 8, 3, 9, 1, 7, 0, 2, 4, 6};

		sort(nums, 0, nums.length - 1);

		printArr(nums);
	}
	public static void sort(int[] nums, int left, int right) {
		if (nums == null || nums.length == 0) {
			return;
		}
		if (left > right) {
			return;
		}
		// 取中间值pivot
		int pivot = nums[left + (right - left) / 2];
		int i = left;
		int j = right;
		while (i <= j) {
			// 找到左边大于pivot的值
			while (nums[i] < pivot) {
				i++;
			}
			// 找到右边小于pivot的值
			while (nums[j] > pivot) {
				j--;
			}
			//把左右两边找到的值交换
			if (i <= j) {
				swap(nums, i, j);
				i++;
				j--;
			}
		}
		// 对左边子数组做排序处理
		if (j > left) {
			sort(nums, left, j);
		}
		// 对右边子数组做排序处理
		if (i < right) {
			sort(nums, i, right);
		}

	}
	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}

	public static void printArr(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			list.add(nums[i]);
		}
		System.out.println(list);
	}
}