import java.util.List;
import java.util.ArrayList;

public class MergeSort {

	public static void main(String[] args) {
		int[] nums   = {5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
		int[] result = new int[nums.length];

		merge(nums, result, 0, nums.length - 1);
		printArr(result);
	}

	public static void merge(int[] nums, int[] result, int start, int end) {
		if (start >= end) return;

		int mid = ((end - start) / 2) + start;
		int start1 = start, end1 = mid;
		int start2 = mid + 1, end2 = end;
		merge(nums, result, start1, end1);
		merge(nums, result, start2, end2);
		int i = start;
		while (start1 <= end1 && start2 <= end2) {
			result[i++] = nums[start1] < nums[start2] ? nums[start1++] : nums[start2++];
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

	public static void printArr(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			list.add(nums[i]);
		}
		System.out.println(list);
	}
}