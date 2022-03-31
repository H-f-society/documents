import java.util.ArrayList;

/**
 * 优先队列 - 大顶堆 - 堆排序
 */
public class MaxPriorityQueue<T extends Comparable<T>> {

    // 存放堆节点
    private final ArrayList<T> queue;

    public MaxPriorityQueue() {
        this.queue = new ArrayList<>();
    }

    // 当前堆大小
    public int size() {
        return queue.size();
    }

    public String toString() {
        return queue.toString();
    }

    /**
     * 元素添加到堆中，并调整堆序
     * @param val 新节点元素
     */
    public void add(T val) {
        queue.add(val);
        if (size() <= 1) {
            return;
        }
        // 新节点插入队列末尾, 下标为 size() - 1;
        // 左节点的父节点为 (index - 1) / 2, 右节点的父节点为 (index - 2) / 2
        int index = size() - 1;
        int pIndex = (index - (index % 2 != 0 ? 1 : 2)) / 2;
        // 堆末尾节点与父级节点比较，决定是否上移保持堆序
        while (compareTo(val, queue.get(pIndex))) {
            swap(index, pIndex);
            index = pIndex;
            pIndex = (index - (index % 2 != 0 ? 1 : 2)) / 2;
            if (pIndex < 0) {
                break;
            }
        }
    }

    /**
     * 移除堆中最值，并调整堆序
     * @return 堆顶最值
     */
    public T remove() {
        if (size() == 0) {
            return null;
        }
        // 堆顶和最后一个节点交换
        swap(0, size() - 1);
        T result = queue.remove(size() - 1);

        // 堆顶节点向下和子节点比较，决定是否下移保持堆序
        int index = 0;
        while ((index * 2) + 1 < size()) {
            int first;
            int left = (index * 2) + 1;
            int right = (index * 2) + 2;

            if (right < size()) {
                // 如果存在右子节点，取左、右节点最值，与当前节点比较。
                first = compareTo(queue.get(left), queue.get(right)) ? left : right;
                first = compareTo(queue.get(first), queue.get(index)) ? first : index;
            } else {
                // 如果只存在左子节点，与当前节点比较
                first = compareTo(queue.get(left), queue.get(index)) ? left : index;
            }
            // 如果最值为当前节点，则结束堆节点下移
            if (first == index) {
                break;
            }
            swap(index, first);
            index = first;
        }
        return result;
    }

    public boolean compareTo(T x, T y) {
        return x.compareTo(y) > 0;
    }

    private void swap(int x, int y) {
        T temp = queue.get(x);
        queue.set(x, queue.get(y));
        queue.set(y, temp);
    }

    public static void main(String[] args) {
        MaxPriorityQueue<Integer> maxPriorityQueue = new MaxPriorityQueue<>();
        int[] nums = {3, 1, 2, 4, 6, 0, 9, 7, 8, 5};

        for (int num : nums) {
            maxPriorityQueue.add(num);
        }
        System.out.println(maxPriorityQueue);

        while (maxPriorityQueue.size() > 0) {
            System.out.print(maxPriorityQueue.remove() + " ");
        }
        System.out.println();
    }
}
