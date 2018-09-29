package com.yh.survey.domain.utils;

import com.google.common.base.Preconditions;
import com.yh.survey.domain.manager.pojo.Auth;
import com.yh.survey.domain.manager.pojo.Res;
import com.yh.survey.domain.manager.pojo.Role;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Set;

/**
 * 权限相关的工具类
 */
public final class AuthUtil {

    /**
     * 计算用户权限码方法
     *
     * @param roleSet 角色集合
     * @param maxPos  最大权限位
     * @return 用户权限码数组
     */
    public static String getCodeArray(Set<Role> roleSet, Integer maxPos) {
        Preconditions.checkArgument(CollectionUtils.isNotEmpty(roleSet), "roleSet cannot empty");
        Preconditions.checkNotNull(maxPos);
        int[] codeArray = new int[maxPos + 1];
        for (Role role : roleSet) {
            Set<Auth> authSet = role.getAuthSet();
            for (Auth auth : authSet) {
                Set<Res> resSet = auth.getResSet();
                for (Res res : resSet) {
                    Integer resCode = res.getResCode();
                    Integer resPos = res.getResPos();
                    int oldValue = codeArray[resPos];
                    int newValue = resCode | oldValue;
                    codeArray[resPos] = newValue;
                }
            }
        }
        return StringUtils.join(codeArray, ',');
    }

    /**
     * 校验权限
     *
     * @param codeArray 用户的权限码
     * @param res       资源
     * @return 用户是否具有访问res的权限
     */
    public static boolean checkAuthority(String codeArray, Res res) {
        //1.从Res对象中获取权限码和权限位
        Integer resCode = res.getResCode();
        Integer resPos = res.getResPos();
        //2.将codeArr转换为数组
        String[] split = codeArray.split(",");
        //3.以权限位为下标从数组中取出对应的元素
        String codeStr = split[resPos];
        int code = Integer.parseInt(codeStr);
        //4.将用户在当前权限位的权限码和Res对象的权限码进行与运算
        int result = resCode & code;
        //5.查看结果是否为0
        return result != 0;
    }

    public static String getPath(String servletPath) {
        Preconditions.checkArgument(StringUtils.isNotBlank(servletPath), "servletPath cannot blank");
        String[] splitStrs = servletPath.split("/");
        if (splitStrs.length <= 4) {
            return servletPath;
        }
        return "/" + splitStrs[1] + "/" + splitStrs[2] + "/" + splitStrs[3];
    }
}
