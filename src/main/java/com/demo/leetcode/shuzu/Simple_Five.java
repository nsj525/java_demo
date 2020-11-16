package com.demo.leetcode.shuzu;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 *
 * （这里，平面上两点之间的距离是欧几里德距离。）
 *
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *
 * 实例:
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *
 */
public class Simple_Five {

    public int[][] kClosest(int[][] points, int k) {

        Arrays.sort(points, Comparator.comparingInt( (int[] point) -> (point[0] * point[0] + point[1] * point[1]) ));

        return Arrays.copyOfRange(points,0, k);

    }

    /**
     * 力扣大神解题------快排
     * @param points
     * @param K
     * @return
     */
    public static int[][] method1(int[][] points, int K) {
        if (points == null || K <= 0 || points.length < K) {
            return new int[0][0];
        }

        int length = points.length;
        int leftIndex = 0;
        int rightIndex = length - 1;
        int curPosition = -1;

        /*
         *   对 points 进行 快排
         */
        while (curPosition != K - 1) {
            curPosition = partition(points, leftIndex, rightIndex);
            if (curPosition < K - 1) {
                leftIndex = curPosition + 1;
            }
            if (curPosition > K - 1) {
                rightIndex = curPosition - 1;
            }
        }

        /*
            将 排序后的数组 前K个 取出并返回
         */
        return Arrays.copyOf(points, K);
    }

    /**
     * 将 指定数组 的 指定区间 进行 “一轮快排”
     * @param points 指定数组
     * @param left 指定区间 的 左边界
     * @param right 指定区间 的 右边界
     * @return 快排一轮后，当前元素所在位置
     */
    private static int partition(int[][] points, int left, int right) {
        int leftValue = points[left][0] * points[left][0] + points[left][1] * points[left][1];
        while (left < right) {
            while (left < right && (points[right][0] * points[right][0] + points[right][1] * points[right][1] >= leftValue)) {
                right--;
            }
            if (left < right) {
                swapPoints(points, left, right);
            }

            while (left < right && (points[left][0] * points[left][0] + points[left][1] * points[left][1] <= leftValue)) {
                left++;
            }
            if (left < right) {
                swapPoints(points, left, right);
            }
        }
        swapPoints(points, left, right);
        return left;
    }

    /**
     * 交换 指定数组 的 指定下标 两元素
     * @param sourcePoints 指定数组
     * @param index1 要交换的数组元素下标1
     * @param index2 要交换的数组元素下标2
     */
    private static void swapPoints(int[][] sourcePoints, int index1, int index2) {
        int[] temp = sourcePoints[index1];
        sourcePoints[index1] = sourcePoints[index2];
        sourcePoints[index2] = temp;
    }

    public static void main(String[] args) {
    }

}
