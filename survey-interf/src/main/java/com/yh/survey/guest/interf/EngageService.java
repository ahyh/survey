package com.yh.survey.guest.interf;

import java.util.Map;

public interface EngageService {

    /**
     * 解析答案并保存
     *
     * @param allBagMap 答案数据
     * @param surveyId  调查id
     */
    Integer saveByParse(Map<Long, Map<String, String[]>> allBagMap, Long surveyId, String createUser);
}
