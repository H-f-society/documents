package math;

/**
 * @Author: root
 * @Date: 2022/4/6 15:02
 * @Description: 数字1的个数
 */
public class NumberOfDigitOne {

    public static void main(String[] args) {
        System.out.println(countDigitOne(101));
    }

    public static int countDigitOne(int n) {
        int count = 0;
        int num = n;
        long i = 1;
        while(num != 0) {
            if(num % 10 == 0) {
                count += (num / 10) * i;
            }
            if(num % 10 == 1) {
                count += (num / 10) * i + (n % i) + 1;
            }
            if(num % 10 > 1) {
                count += Math.ceil(num / 10.0) * i;
            }
            num /= 10;
            i *= 10;
        }
        return count;
    }
}
