package com.yh.survey.guest.service.impl;

import com.yh.survey.dao.AnswerDao;
import com.yh.survey.guest.interf.AnswerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Resource
    private AnswerDao answerDao;


}
