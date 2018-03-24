package com.cqust.blog.common.utils;

import com.cqust.blog.common.common.ConstantCode;
import com.cqust.blog.common.resp.GeneralResult;

import javax.persistence.Column;
import java.lang.reflect.Field;
import java.util.Random;

/**
 * 数据服务工具类
 */
public final class DataUtils {

    private static final Random RANDOM = new Random();

    public static String test() {
        return "hello world";
    }

    /**
     * 根据实体注解检测实体是否符合数据库设计要求
     * @param obj 带检测对象
     * @return true 不符合  false 符合要求
     */
    public static GeneralResult<Boolean> checkFieldByAnnotaion(Object obj) throws IllegalAccessException {
        GeneralResult<Boolean> result = new GeneralResult<Boolean>();
        //如果对象为空直接不符合
        if (obj == null) {
            result.setCode(ConstantCode.PARAM_ERROR);
            result.setData(true);
            return result;
        }
        Class<?> classObj = obj.getClass();
        Field[] declaredFields = classObj.getDeclaredFields();
        for (Field item : declaredFields) {
            item.setAccessible(true);
            Column annotation = item.getAnnotation(Column.class);
            if (annotation == null)
                continue;
            Object fieldData = item.get(obj);
            if (!annotation.nullable()) {
                if (fieldData == null) {
                    result.setCode(ConstantCode.PARAM_ERROR);
                    result.setMsg("字段不可为空");
                    result.setData(true);
                    return result;
                }
            }
            if (fieldData != null && fieldData.toString().length() >  annotation.length()) {
                result.setCode(ConstantCode.PARAM_ERROR);
                result.setMsg("字段长度受限");
                result.setData(true);
                return result;
            }
            if (fieldData != null && item.getType() == String.class && strIsNullOrEmpty(fieldData.toString())) {
                result.setCode(ConstantCode.PARAM_ERROR);
                result.setMsg("参数不可为空格");
                result.setData(true);
                return result;
            }
        }
        result.setData(false);
        return result;
    }

    /**
     * 检测字符串是否为空
     * @param params 参数
     * @return 处理结果  true 为空  false 不为空
     */
    public static boolean strIsNullOrEmpty(String ...params) {
        for (String param : params) {
            if (param == null || "".equals(param.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 产生n为随机数
     * @param n 位数
     * @return 字符串
     */
    public static String genNnumber(int n) {
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            builder.append(RANDOM.nextInt(10));
        }
        return builder.toString();
    }
}
