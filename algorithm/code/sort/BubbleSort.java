import java.util.Arrays;

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
		System.out.println(Arrays.toString(nums));
	}
}