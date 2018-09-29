package com.yh.survey.manager;

import java.util.List;

public interface RoleManager {

    Integer updateRoleAuthShip(Long roleId, List<Long> authIdList, String createUser);
}
