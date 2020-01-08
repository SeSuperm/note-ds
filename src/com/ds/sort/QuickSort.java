package com.ds.sort;

import java.util.Arrays;
import java.util.Stack;

/**
 * 快排
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{4,1,3,14,1,3,9,8,1,3,4};

        //quickSort(arr);
        quickSort_noRecu(arr);

        System.out.println(Arrays.toString(arr));
        //[1, 1, 1, 3, 3, 3, 4, 4, 8, 9, 14]
    }

    // 快排思想
    // 分治策略，从数组中找出一个元素，作为基准元素，然后分别从数组两边开始，比基准值小的元素放入到左边，比基准值大的元素放入到右边。
    // 平均情况下，排序n个元素时间复杂度为O(nlogn)，在最坏的情况下需要O(n2)，空间复杂度O(logn)

    //  4,1,3,14,1,3,9,8,1,3,4
    // 以第一个元素4为基准，从最右边元素往左开始，找到第一个小于4的元素3，此时将元素3放入到arr[0]位置
    //  3,1,3,14,1,3,9,8,1,x,4 此时3的位置是空置的，用x表示
    // 从左边第二个元素开始，找出第一个大于4的元素14，将14放入到arr[10]位置
    //  3,1,3,x,1,3,9,8,1,14,4
    // 然后继续从右边第三个元素arr[9]开始，重复上述步骤；然后再从左边第5个元素arr[4]开始，重复上述步骤
    // i == j 后，此时将最开始的基准元素4，放入到arr[i]这个位置

    // 第一次分治，将数组分为两个子数组，分别再对子数组按照上述步骤排序。

    public static void quickSort(int[] arr) {
        if (arr == null) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l < r) {

            int i = l, j = r, temp = arr[l];
            while (i < j) {
                while (i < j && arr[j] >= temp) {
                    j--;
                }
                if (i < j) {
                    arr[i] = arr[j];
                    i++;
                }

                while (i < j && arr[i] <= temp) {
                    i++;
                }
                if (i < j) {
                    arr[j] = arr[i];
                    j--;
                }
            }
            arr[i] = temp;

            quickSort(arr, l, i - 1);
            quickSort(arr, j + 1, r);
        }
    }

    // 每次分治时，记录下此时的索引即可
    private static void quickSort_noRecu(int[] arr) {
        if (arr == null) {
            return;
        }

        int l = 0, r = arr.length - 1;

        if (l < r) {
            Stack<Integer> stack = new Stack<>();
            stack.push(l);
            stack.push(r);

            while (!stack.isEmpty()) {
                int end = stack.pop();
                int start = stack.pop();

                if (end <= start) {
                    break;
                }

                int index = partition(arr, start, end);
                if (start < index - 1) {
                    stack.push(start);
                    stack.push(index - 1);
                }
                if (end > index + 1) {
                    stack.push(index + 1);
                    stack.push(end);
                }
            }
        }
    }

    private static int partition(int[] arr, int l, int r) {
        if (l < r) {
            int temp = arr[l];
            while (l < r) {
                while (l < r && arr[r] >= temp) {
                    r --;
                }
                if (l < r) {
                    arr[l] = arr[r];
                    l ++;
                }

                while (l < r && arr[l] <= temp) {
                    l ++;
                }
                if (l < r) {
                    arr[r] = arr[l];
                    r--;
                }
            }
            arr[l] = temp;
            return l;
        }
        return -1;
    }

}
