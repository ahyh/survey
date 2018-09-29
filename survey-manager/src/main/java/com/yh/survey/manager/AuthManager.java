package com.yh.survey.manager;

import java.util.List;

public interface AuthManager {

    Integer updateAuthResShip(Long authId, List<Long> resIdList, String createUser);

    Integer updateAllCodeArray();
}
