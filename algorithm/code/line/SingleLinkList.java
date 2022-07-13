package line;

/**
 * @Author: root
 * @Date: 2022/4/6 9:48
 * @Description: 单链表
 */

import java.util.*;

public class SingleLinkList {
    public class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static int[] nums = {1, 2, 3, 4, 5};
    public static ListNode head = null;

    public static void main(String[] args) {
        SingleLinkList link = new SingleLinkList();
        for (int val : nums) {
            link.addNode(val);
        }
        System.out.println(link.getNode(head));

        link.reverseList();
        System.out.println("翻转链表方法1: " + link.getNode(head));
        link.reverseList2();
        System.out.println("翻转链表方法2: " + link.getNode(head));

        link.delNode(1);
        System.out.println("删除链表节点:  " + link.getNode(head));
        link.updateNode(4, 6);
        System.out.println("更新链表节点:  " + link.getNode(head));
    }

    public void addNode(int val) { // 链表添加节点
        ListNode node = new ListNode(val);
        ListNode h = head;
        if (head == null) {
            head = node;
            return;
        }
        while (h.next != null) {
            h = h.next;
        }
        h.next = node;
    }

    public void delNode(int val) { // 删除链表节点
        ListNode node = new ListNode(0);
        node.next = head;
        ListNode h = node;
        while (h.next != null) {
            if (h.next.val == val) {
                h.next = h.next.next;
                head = node.next;
                return;
            }
            h = h.next;
        }
        head = node.next;
    }

    public void updateNode(int oldVal, int newVal) {
        ListNode h = head;
        while (h != null) {
            if (h.val == oldVal) {
                h.val = newVal;
            }
            h = h.next;
        }
    }

    public void reverseList() { // 翻转链表方法1
        ListNode next = null;
        ListNode p = null;
        while (head != null) {
            next = head.next;
            head.next = p;
            p = head;
            head = next;
        }
        head = p;
    }

    public void reverseList2() { // 翻转链表方法2
        ListNode h = head;
        ListNode p = head.next;
        ListNode t = null;
        h.next = null;
        while (p != null) {
            t = p.next;
            p.next = h;
            h = p;
            p = t;
        }
        head = h;
    }

    public List<Integer> getNode(ListNode head) { //获取链表节点返回List
        List<Integer> result = new ArrayList<>();
        ListNode h = head;
        while (h != null) {
            result.add(h.val);
            h = h.next;
        }
        return result;
    }
}
