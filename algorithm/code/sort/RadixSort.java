import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] nums = {3, 44, 38, 5, 47, 35, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        List<LinkedList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new LinkedList<>());
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == 0) list.get(nums[j] % 10).offer(nums[j]);
                if (i == 1) list.get(nums[j] / 10).offer(nums[j]);
            }
            int n = 0;
            for (int j = 0; j < 10; j++) {
                //System.out.println(list.get(j));
                while (!list.get(j).isEmpty()) {
                    nums[n++] = list.get(j).poll();
                }
            }
            //System.out.println("------------");
        }
        System.out.println(Arrays.toString(nums));
    }
}
