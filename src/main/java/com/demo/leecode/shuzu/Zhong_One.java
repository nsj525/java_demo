package com.demo.leecode.shuzu;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 个人理解：根据两个数值A、B下标差值，和数值A、B中的较小值的，计算乘积，得出最大的乘积
 *
 */
public class Zhong_One {

    public static final int[] NUM = new int[]{1,8,6,2,5,4,8,3,7};

    public static void main(String[] args) {
        // System.out.println("获取到的最大值为："+myMethod(NUM));
        System.out.println("获取到的最大值为："+doublePoint(NUM));
    }

    //遍历计算，并排序，得出最大值
    public static int myMethod(int[] arrays){

        //暴力解决
        int i;
        int j;
        int max = 0 ;
        for (i = 0;i<arrays.length -1;i++){
            for (j = i+1;j<arrays.length;j++){
                max = Math.max((j-i) * Math.min(arrays[i],arrays[j]),max);
            }
        }

        return max;

    }

    /**
     * 双指针方式
     *
     * 思路：类似于木桶原理，决定最大容积的，永远是最小的数，所以，在双指针方案中，我们永远是移动值较小的指针的位置（起码这样才有机会）
     *
     * @param arrays
     * @return
     */
    public static int doublePoint(int[] arrays){

        int left = 0;
        int right = arrays.length-1;
        int max = 0;

        while (left < right){

            max = Math.max(max,(right-left) * Math.min(arrays[left],arrays[right]));
            if (arrays[left] <= arrays[right]){
                left++;
            }else{
                right--;
            }

        }
        return max;
    }

}
