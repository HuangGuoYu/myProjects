package com.cqust.blog.common.algorithm;

/**
 * Created by Administrator on 2018/5/22.
 */
public class Fibnacci {
    /**
     * 【程序1】   题目：古典问题：有一对兔子，
     * 从出生后第3个月起每个月都生一对兔子，
     * 小兔子长到第三个月后每个月又生一对兔子，
     * 假如兔子都不死，问每个月的兔子总数为多少？
     * @param month 当前月份
     * @return
     */
    public static Integer compute(Integer month) {
        if (month.equals(1) || month.equals(2)) {
            return 1;
        }
        return compute(month - 1) + compute(month - 2);
    }


    public static void main(String[] args) {
        System.out.println(compute(5));
        System.out.println(compute(10));
    }
}
