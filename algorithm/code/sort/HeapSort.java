public class HeapSort {
	private int[] data;
	private int size;
	private int capacity;

	public HeapSort(int capacity) {
		this.data = new int[capacity + 1];
		this.size = 0;
		this.capacity = capacity;
	}
	private void insert(int num) {
		if (size == capacity) return;
		data[++size] = num;
		shiftUp(size);
	}
	private void shiftUp(int index) {
		while (index > 1 && data[index] > data[index / 2]) {
			int tmp = data[index];
			data[index] = data[index / 2];
			data[index / 2] = tmp;
			index = index / 2;
		}
	}
	private int delete() {
		if (size == 0) return -1;
		int tmp = data[1];
		data[1] = data[size--];
		shiftDown(1);
		return tmp;
	}
	private void shiftDown(int index) {
		while (2 * index <= size) {
			int i = 2 * index;
			if (i + 1 <= size && data[i] < data[i + 1]) i = i + 1;
			if (data[index] > data[i]) break;
			int tmp = data[index];
			data[index] = data[i];
			data[i] = tmp;
			index = i;
		}
	}

	public static void main(String[] args) {
		int[] nums = {2, 4, 1, 6, 5, 3, 9, 0, 8, 7};
		HeapSort heap = new HeapSort(10);
		for (int i = 0; i < nums.length; i++) {
			heap.insert(nums[i]);
		}
		for (int i = nums.length - 1; i >= 0; i--) {
			nums[i] = heap.delete();
		}

		for (int i = 0; i < nums.length; i++) {
			System.out.print(nums[i] + ", ");
		}
	}
}