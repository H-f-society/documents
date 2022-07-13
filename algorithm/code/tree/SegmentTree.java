package tree;

import java.util.Arrays;

/**
 * @Author: root
 * @Date: 2022/4/2 16:08
 * @Description: 线段树
 */
public class SegmentTree {

    private static final int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
    private static int[] segTree = new int[2 * nums.length - 1];

    public static void main(String[] args) {

        SegmentTree st = new SegmentTree();

        for (int i=0; i<nums.length; i++) {
            st.updateNodeVal(i, nums[i]);
        }

        st.updateNodeVal(6, 2);

        System.out.println(Arrays.toString(segTree));
    }

    public void updateNodeVal(int index, int val) {
        int tempIndex = index + (nums.length - 1);
        segTree[tempIndex] += val;
        int parentIndex = getParentIndex(tempIndex);

        while (parentIndex >= 0) {
            segTree[parentIndex] += val;
            tempIndex = parentIndex;
            parentIndex = getParentIndex(tempIndex);
        }
    }

    public int getParentIndex(int index) {
        return (index - (index % 2 == 0 ? 2 : 1)) / 2;
    }

}
