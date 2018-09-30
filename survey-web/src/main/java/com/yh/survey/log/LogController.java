package com.yh.survey.log;

import com.yh.survey.log.interf.LogService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class LogController {

    @Resource
    private LogService logService;


}
