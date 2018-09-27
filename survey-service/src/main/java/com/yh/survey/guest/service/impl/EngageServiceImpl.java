package com.yh.survey.guest.service.impl;

import com.google.common.collect.Lists;
import com.yh.survey.dao.AnswerDao;
import com.yh.survey.domain.pojo.Answer;
import com.yh.survey.guest.interf.EngageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * EngageService
 *
 * @author yanhuan
 */
@Service
public class EngageServiceImpl implements EngageService {

    @Resource
    private AnswerDao answerDao;

    @Override
    public Integer saveByParse(Map<Long, Map<String, String[]>> allBagMap, Long surveyId, String createUser) {
        //1.生成一个随机UUID值
        String uuid = UUID.randomUUID().toString();
        //2.创建一个List集合用来保存Answer对象
        List<Answer> answerList = Lists.newArrayList();
        //3.忽略bagId，从allBagMap中取出所有的paramMap
        Collection<Map<String, String[]>> paramMaps = allBagMap.values();
        Date creareTime = new Date();
        for (Map<String, String[]> paramMap : paramMaps) {
            Set<Map.Entry<String, String[]>> entrySet = paramMap.entrySet();
            for (Map.Entry<String, String[]> entry : entrySet) {
                String paramName = entry.getKey();
                if (!paramName.startsWith("q")) {
                    continue;
                }
                //7.从paramName中解析questionId
                Long questionId = Long.valueOf(paramName.substring(1));
                //8.将请求参数值转换成answerContent
                String[] value = entry.getValue();
                String answerContent = convertValue2Content(value);
                //9.创建Answer对象
                Answer answer = new Answer();
                answer.setAnswerContent(answerContent);
                answer.setAnswerUuid(uuid);
                answer.setSurveyId(surveyId);
                answer.setQuestionId(questionId);
                answer.setCreateUser(createUser);
                answer.setCreateTime(creareTime);
                //10.将Answer对象保存到List集合中
                answerList.add(answer);
            }
        }
        return answerDao.insertBatch(answerList);
    }

    /**
     * 将请求参数数组转换为逗号分隔的字符串
     *
     * @param value
     * @return
     */
    private String convertValue2Content(String[] value) {
        if (value == null || value.length == 0) {
            return "";
        }
        return StringUtils.join(value, ",");
    }
}
