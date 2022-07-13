package bit;

/**
 * @Author: root
 * @Date: 2022/6/20 14:44
 * @Description: low bit - 二进制的最低位
 */
public class LowBit {

    public static void main(String[] args) {
        int num = 233;

        while (num > 0) {
            int rst = lowbit(num);
            num -= rst;
            System.out.print(rst + ", ");
        }
    }

    public static int lowbit(int num) {
        return num & -num;
    }
}
