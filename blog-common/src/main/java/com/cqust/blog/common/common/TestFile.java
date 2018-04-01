package com.cqust.blog.common.common;
import static com.cqust.blog.common.utils.DataUtils.*;

/**
 * Created by Administrator on 2018/3/30.
 */
public class TestFile {
    int age = 12;
    public static void main(String[] args) throws IllegalAccessException {
        switch (ConstantEnum.FIRST) {
            case FIRST:
                System.out.println(ConstantEnum.FIRST.getRealName()); break;
            case SECONED:
                break;
        }

        ConstantEnum[] values = ConstantEnum.values();
        for (ConstantEnum value : values) {
            System.out.println(value);
            checkFieldByAnnotaion(new Object());
        }
    }
}
