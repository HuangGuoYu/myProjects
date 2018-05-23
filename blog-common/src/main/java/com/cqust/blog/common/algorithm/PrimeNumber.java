package com.cqust.blog.common.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 【程序2】   题目：判断101-200之间有多少个素数，并输出所有素数。
 1.程序分析：判断素数的方法：用一个数分别去除2到sqrt(这个数)，如果能被整除，则表明此数不是素数，反之是素数。
 */

public class PrimeNumber {

    public static List<Integer> compute(Integer num) {
        List<Integer> res = new ArrayList<>();

        for (int i = 2; i <= num; i++) {
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if ((i % j) == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                res.add(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }

}
