package line;

public class BinarySearch<T extends Comparable<T>> {

    public int search(T[] arr, T val, int begin, int end) {
        int index = -1;
        int mid = ((end - begin) / 2) + begin;
        if (arr.length == 0 || begin > end) {
            return index;
        }
        if (comparaTo(val, arr[mid]) == 0) {
            index = mid;
        }
        if (comparaTo(val, arr[mid]) > 0) {
            index = search(arr, val, mid + 1, end);
        }
        if (comparaTo(val, arr[mid]) < 0) {
            index = search(arr, val, begin, mid);
        }
        return index;
    }

    private int comparaTo(T x, T y) {
        return Integer.compare(x.compareTo(y), 0);
    }

    public static void main(String[] args) {
        Integer[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        BinarySearch<Integer> bs = new BinarySearch<>();

        System.out.println(bs.search(nums, -10, 0, nums.length - 1));
    }

}
