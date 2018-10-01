package com.yh.survey.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class LogTableNameUtil {

    /**
     * 获取表名的工具方法
     *
     * @param offset 偏移量
     * @return 表名
     */
    public static String getTableName(int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, offset);
        Date time = calendar.getTime();
        return "auto_log_" + new SimpleDateFormat("yyyy_MM").format(time);
    }
}
