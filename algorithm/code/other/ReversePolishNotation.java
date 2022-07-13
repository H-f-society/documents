package other;

import java.util.*;

/**
 * @Author: root
 * @Date: 2022/4/6 14:36
 * @Description: 逆波兰表达式
 */
public class ReversePolishNotation {
    private static LinkedList<String> nums = new LinkedList<>();
    private static LinkedList<Character> symbol = new LinkedList<>();

    public static void main(String[] args) {
        float result = create("5 * ((1 + 2) / 4.0) - 3");
        System.out.println(result);
    }

    /**
     * 将中缀表达式转换为后缀表达式（构建逆波兰表达式）
     *
     * @param str [运算表达式]
     * @return [description]
     * @DateTime 2021-05-03T19:58:57+0800
     */
    public static float create(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (' ' == str.charAt(i)) {
                continue;
            }
            if (isNumber(str.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                sb.append(str.charAt(i) + "");
                while (i < str.length() - 1 && isNumber(str.charAt(i + 1))) {
                    sb.append(str.charAt(++i));
                }
                nums.add(sb.toString());
            } else {
                // 判断当前运算符优先级是否低于站内运算符
                while (!symbol.isEmpty() && '(' != symbol.peekLast() && compare(str.charAt(i), symbol.peekLast())) {
                    nums.add(symbol.pollLast() + "");
                }
                if (')' != str.charAt(i)) {
                    symbol.add(str.charAt(i));
                } else {
                    while (!symbol.isEmpty() && '(' != symbol.peekLast()) {
                        nums.add(symbol.pollLast() + "");
                    }
                    if (!symbol.isEmpty() && '(' == symbol.peekLast()) {
                        symbol.pollLast();
                    }
                }
            }
        }
        while (!symbol.isEmpty()) {
            nums.add(symbol.pollLast() + "");
        }
        System.out.println(nums);
        return calculate(nums);
    }

    /**
     * 计算逆波兰表达式
     *
     * @param nums [逆波兰表达式]
     * @return [description]
     * @DateTime 2021-05-03T19:59:22+0800
     */
    public static float calculate(LinkedList<String> nums) {
        if (nums.isEmpty()) {
            return 0;
        }
        LinkedList<Float> stack = new LinkedList<>();
        for (String num : nums) {
            if ("+".equals(num)) {
                stack.add(stack.pollLast() + stack.pollLast());
            } else if ("-".equals(num)) {
                float num1 = stack.pollLast();
                float num2 = stack.pollLast();
                stack.add(num2 - num1);
            } else if ("*".equals(num)) {
                stack.add(stack.pollLast() * stack.pollLast());
            } else if ("/".equals(num)) {
                float num1 = stack.pollLast();
                float num2 = stack.pollLast();
                stack.add(num2 / num1);
            } else {
                stack.add(Float.valueOf(num));
            }
        }
        return stack.get(0);
    }

    /**
     * 判断字符为有效数字，可能带小数点
     *
     * @param ch [字符数字]
     * @return [description]
     * @DateTime 2021-05-03T23:53:59+0800
     */
    public static boolean isNumber(char ch) {
        return (ch >= '0' && ch <= '9') || ch == '.';
    }

    /**
     * 运算符优先级
     *
     * @param ch [运算符]
     * @return [description]
     * @DateTime 2021-05-03T22:55:41+0800
     */
    public static int priority(char ch) {
        switch (ch) {
            case '+':
            case '-': return 0;
            case '*':
            case '/': return 1;
            default: break;
        }
        return 0;
    }

    /**
     * 比较优先级
     *
     * @param ch1 [当前运算符]
     * @param ch2 [栈内运算符]
     * @return [description]
     * @DateTime 2021-05-03T23:54:28+0800
     */
    public static boolean compare(char ch1, char ch2) {
        if ('(' == ch1 || ')' == ch2) {
            return false;
        }
        return priority(ch1) <= priority(ch2);
    }
}