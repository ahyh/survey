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
}
