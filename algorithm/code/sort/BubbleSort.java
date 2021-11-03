import java.util.List;
import java.util.ArrayList;

public class BubbleSort {

	public static void main(String[] args) {
		int[] nums = {5, 8, 3, 9, 1, 7, 0, 2, 4, 6};
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = 0; j < nums.length - 1 - i; j++) {
				if (nums[j] > nums[j + 1]) {
					int temp  = nums[j + 1];
					nums[j + 1] = nums[j];
					nums[j]   = temp;
				}
			}
		}
		printArr(nums);
	}

	public static void printArr(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			list.add(nums[i]);
		}
		System.out.println(list);
	}
}