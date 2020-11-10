package com.demo.leecode.shuzu;

/**
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 *
 * 剖析：实则就是给定一个数组，用后面的数减去前面的数，取最大值
 *
 * @author nijiejie
 */
public class Simple_Two {

    public static final int[] NUM = new int[]{7,1,5,3,6,4};

    /**
     * 双层for循环，暴力计算最大值
     *
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     *
     */
    public static int myMethod(){

        int max = 0;
        for(int i = 0 ; i < NUM.length-1 ; i++){

            for(int j = i+1 ; j < NUM.length ; j++){

                max = Math.max(max,NUM[j]-NUM[i]);

            }

        }
        return max;

    }

    /**
     * 设置一个最小值minPrice，和最大利润maxProfit，遍历数组
     *
     * 分支1：如果存在比对小值小的，替换掉最小值
     * 分支2：计算当前价格和最小值的差价，如果存在比最大利润大的，替换最大利润
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @return
     */
    public static int maxProfit() {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < NUM.length; i++) {
            if (NUM[i] < minPrice) {
                minPrice = NUM[i];
            } else if (NUM[i] - minPrice > maxProfit) {
                maxProfit = NUM[i] - minPrice;
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {

        System.out.println("-------"+maxProfit());

    }

}
