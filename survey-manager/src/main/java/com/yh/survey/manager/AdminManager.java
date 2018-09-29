package com.yh.survey.manager;

import java.util.List;

public interface AdminManager {

    Integer updateAdminRoleShip(Long adminId,List<Long> roleIdList,String createUser);
}
