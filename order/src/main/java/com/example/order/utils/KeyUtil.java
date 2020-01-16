package com.example.order.utils;

import java.util.Random;

public class KeyUtil {

    /**
     *
     * 生成唯一的主键 [这个我们只是简单用下，不能做到100%的唯一，真正公司不这么用，公司应该新建一个项目]
     * 格式： 时间+随机数
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
