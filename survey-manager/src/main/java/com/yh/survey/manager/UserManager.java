package com.yh.survey.manager;

import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.domain.manager.pojo.Role;

public interface UserManager {

    Integer saveUserWithCodeArray(User user, Role role);
}
