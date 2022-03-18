/*  
*     树状数组 / 二叉索引数 / binary indexed tree
*---------------------------------------------------------
* 将 [1, 2, 3, 4, 5, 6, 7, 8] 维护成下图所示的树形结构数组
* --------------------------------------------------------
*    len = 8 |                          36                                              
*    len = 4 |             10              
*    len = 2 |      3            11        
*    len = 1 |   1     3     5       7       
* --------------------------------------------------------
*  下标          0  1  2  3  4   5   6   7
* --------------------------------------------------------
*/
/*
       ---------36   
   ----10   |    |
   3   |    11   |
   |   |    |    |
   1   3    5    7

*/

public class BinaryIndexedTree {

    private static int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
    private static int[] bitTree = new int[nums.length];

    public static void main(String[] args) {
        BinaryIndexedTree bit = new BinaryIndexedTree();

        bit.createBitTree(nums);

        bit.print(bitTree);

        bit.updateNodeVal(4, 6);
        bit.updateNodeVal(0, 2);
        bit.updateNodeVal(0, 9);
        System.out.println(bit.sumRange(4, 4));
        bit.updateNodeVal(3, 8);
        System.out.println(bit.sumRange(0, 4));
        bit.updateNodeVal(4, 1);
        System.out.println(bit.sumRange(0, 3));
        System.out.println(bit.sumRange(0, 4));

        bit.print(bitTree);
    }

    /**
     * 构建树状数组
     * Tree[x] 的父节点为 Tree[x + lowBit(i)]
     * 父节点值 = 父节点+ 所有子节点
     */
    public void createBitTree(int[] nums) {
        bitTree = nums.clone();
        for (int i=0; i<nums.length - 1; i++) {
            int tmp = i + lowBit(i + 1);
            if (tmp < bitTree.length) {
                bitTree[tmp] += bitTree[i];
            }
        }
    }

    /**
     * 修改某个节点的值，并向上更新其父节点的值
     * 时间复杂度: O(logn)
     */
    public void updateNodeVal(int index, int val) {
        int tmpVal = nums[index] - val;
        nums[index] = val;
        while (index < bitTree.length) {
            bitTree[index] -= tmpVal;
            index = index + lowBit(index + 1);
        }
    }

    /**
     * 下标 0 ~ index 的和
     * 时间复杂度: O(logn)
     */
    public int sumRange(int right) {
        int sum = 0;
        right++;
        while (right > 0) {
            sum += bitTree[right - 1];
            right -= lowBit(right);
        }
        return sum;
    }

    /**
     * 给定某区间下标，计算该区间内的和
     * 时间复杂度: O(logn)
     */
    public int sumRange(int left, int right) {
        return sumRange(right) - sumRange(left - 1);
    }

    /**
     * lowBit(x)是x的二进制表达式中最低位的1所对应的值
     * -x = x取反 + 1
     */
    public int lowBit(int n) {
        return n & -n;
    }

    public void print(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            System.out.print( i == 0 ? "[ " +nums[i] : ", " + nums[i] + (i == nums.length - 1 ? " ]" : "") );
        }
        System.out.println();
    }
}