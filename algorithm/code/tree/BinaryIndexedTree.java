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

    public static void main(String[] args) {
        BinaryIndexedTree bit = new BinaryIndexedTree();

        bit.createBIT(nums);

        System.out.println(bit.ask(nums, 5));
        System.out.println(bit.ask(nums, 3, 6));

        bit.print(nums);

        bit.addVal(nums, 2, 7);

        System.out.println(bit.ask(nums, 5));
        System.out.println(bit.ask(nums, 3, 6));

        bit.print(nums);
    }

    /**
     * 构建树状数组
     * Tree[x] 的父节点为 Tree[x + lowbit(i)]
     * 父节点值 = 父节点+ 所有子节点
     */
    public void createBIT(int[] nums) {
        for (int i=0; i<nums.length - 1; i++) {
            nums[i + lowbit(i + 1)] += nums[i];
        }
    }

    /**
     * 对某个节点追加值，并向上更新其父节点的值
     * 时间复杂度: O(logn)
     */
    public void addVal(int[] nums, int index, int val) {
        if (index >= nums.length) {
            return;
        }
        nums[index] += val;
        int pNodeIndex = index + lowbit(index + 1);
        addVal(nums, pNodeIndex, val);
    }

    /**
     * 下标 0 ~ index 的和
     * 时间复杂度: O(logn)
     */
    public int ask(int[] nums, int index) {
        int result = 0;
        index += 1;
        while(index > 0) {
            result += nums[index - 1];
            int temp = lowbit(index);
            index -= temp;
        }
        return result;
    }

    /**
     * 给定某区间下标，计算该区间内的和
     * 时间复杂度: O(logn)
     */
    public int ask(int[] nums, int beginIndex, int endIndex) {
        return ask(nums, endIndex) - ask(nums, beginIndex - 1);
    }

    /**
     * lowbit(x)是x的二进制表达式中最低位的1所对应的值
     * -x = x取反 + 1
     */
    public int lowbit(int num) {
        return num & -num;
    }

    public void print(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            System.out.print( i == 0 ? "[ " +nums[i] : ", " + nums[i] + (i == nums.length - 1 ? " ]" : "") );
        }
        System.out.println();
    }
}