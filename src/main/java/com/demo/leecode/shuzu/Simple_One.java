package com.demo.leecode.shuzu;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 *
 * 输入: [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 *
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 *
 * @author nijiejie
 */
public class Simple_One {

    public static int[] NUM = new  int[]{-2,1,-3,4,-1,2,1,-5,4};

    public static int myMethod(){
        int max = 0;
        for(int i = 0 ; i < NUM.length-1 ; i++){
            //第一位元素赋值到max上
            if (max < NUM[i]){
                max = NUM[i];
            }
            int sum = NUM[i];
            for (int j = i+1 ;j<NUM.length ; j++){
                sum += NUM[j];
                if (sum >max){
                    max = sum;
                }
            }
        }
        return max;

    }

    /**
     * 官方给出的方法1（动态规划）
     * 逻辑：遍历相加，去较大值，并比较最大值，得出最大值
     *
     * 时间复杂度：O(n)，其中n为 nums 数组的长度。我们只需要遍历一遍数组即可求得答案
     * 空间复杂度：O(1)，我们只需要常数空间存放若干变量
     *
     */
    public static int method1(){
        int pre = 0, maxAns = NUM[0];
        for (int x : NUM) {
            pre = Math.max(pre + x, x);
            System.out.println("--------pre>>>>>>>>"+pre);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    /**
     * 大佬的实现（动态规划）
     *
     * 逻辑：
     * 这道题用动态规划的思路并不难解决，比较难的是后文提出的用分治法求解，但由于其不是最优解法，所以先不列出来
     * 动态规划的是首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans
     * 如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
     * 如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
     * 每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
     * 时间复杂度：O(n)
     *
     */
    public static int method2(){

        int ans = NUM[0];
        int sum = 0;
        for(int num: NUM) {
            if(sum > 0) {
                sum += num;
            } else {
                sum = num;
            }
            ans = Math.max(ans, sum);
        }
        return ans;

    }


    public static void main(String[] args) {
        // System.out.println("----和最大值为----"+myMethod());
        // System.out.println("----和最大值为----"+method1());
        System.out.println("----和最大值为----"+method2());

    }

}
