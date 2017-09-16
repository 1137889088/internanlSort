package com.chen.sort;

/**
 * @author memoryCoderC
 * @version V1.0
 * @package com.chen.sort
 * @project internanlSort
 * @description: TODO 直接插入排序的实现
 * 时间复杂度：O（n^2）.
 * 其他的插入排序有二分插入排序，2-路插入排序。
 * @date 2017/9/15 15:13 2017
 */
public class StraightInsertionSort {
    /**
     * 直接插入排序
     * 思路
     * 将一个元素作为待插入数据
     * 一个个插入到需要插入的位置
     *
     * @param array 待排序的数组
     */
    public void sortArray(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {       //如果第i-1个元素比第i个小,数组无序,移动到有序位置后插入
                int j = i - 1;
                int x = array[i];                //复制为哨兵，即存储待排序元素
                array[i] = array[i - 1];         //移动一个元素
                while (x < array[j]) {          //查找适合x插入的位置
                    array[j + 1] = array[j];     //将元素向前移动
                }
                array[j + 1] = x;                  //插入到正确位置
            }
        }
    }

    /**
     *  二路插入
     *  在直接插入排序中每次排序都需要将俩个元素进行交换
     *  所以可以创建一个额外的排序数组减少交换次数
     *  例如
     *  序列 2 1 3，当把1往前插入时，由于1<2，则应当把1插入到2的前面。
     *  在一般插入排序方法的实现中，都是把1、2位置交换。
     *  于是，我们想有没有可能不进行交换，因为交换总是相当耗时的。
     *  但是1必须要排到2的前面，可2的前面没有位置了啊？
     *  嗯，初看是这样的。试想这是一个循环的数组呢？这就是二路插入最核心的想法。
     *   思路：
     *   构建一相同大小的循环数组b，
     *   把原数组的元素依次插入，最后按合适次序赋值回原数组。
     *   如何实现循环呢？有办法的。可参考约瑟夫问题的数组解法中是如何实现的。
     *   把原数组的第一个值a[0]复制过去，b[0]=a[0]，作为循环数组的第一个数。当然，也可选择其它的数作为第一个数。
     *   若a[i]<b[first]，则变化first：first=(first-1+n)%n，b[first]=a[i]
     *   若a[i]>=b[last]，则变化last：last=(last+1)%n)，b[last]=a[i]
     *   若b[first]<=a[i]<b[last]，则选择适当的策略，插入下图中的一路位置。
     *
     * @param array 待排序数组
     */
    public void BinInsertSort(int[] array) {
        int first, last;
        int n = array.length;
        first = last = 0;
        int[] b = new int[n];//创建出一个相同大小排序的数组)
        b[0] = array[0];//将第一个元素放入排序数组
        for (int i = 1; i < array.length; i++) {
            if (array[i] < b[first]) {//如果
                first = (first - 1 + n) % n;   //first的变化必须这样写
                b[first] = array[i];
            } else if (array[i] >= b[last]) {
                last=(last+1)%n;
                b[last] = array[i];
            } else {
                int k;
                for (k = last + 1; array[i] < b[(k - 1 + n) % n]; k = (k - 1 + n) % n)     // 使用直接插入
                    b[k] = b[(k - 1 + n) % n];
                b[k] = array[i];
                last++;
            }
        }
    }
}
