package com.yh.survey.aspects;

import com.yh.survey.domain.guest.pojo.User;
import com.yh.survey.domain.log.pojo.Log;
import com.yh.survey.domain.manager.pojo.Admin;
import com.yh.survey.log.RequestBinder;
import com.yh.survey.log.interf.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;

public class LogAspect {

    @Resource
    private LogService logService;

    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String typeName = signature.getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();
        String inputData = Arrays.asList(args).toString();
        Object targetReturnValue = null;
        String exceptionType = null, exceptionMessage = null;
        try {
            //核心操作：调用目标对象的目标方法
            targetReturnValue = joinPoint.proceed(args);
        } catch (Throwable e) {
            exceptionType = e.getClass().getName();
            exceptionMessage = e.getMessage();
            //考虑到当前捕获到的异常有可能有更深层次的原因，而记录日志时又最好保存根本原因，所以进行如下操作
            //i.尝试获取当前异常的原因
            Throwable cause = e.getCause();
            //ii.判断cause是否为null
            while(cause != null) {
                //iii.获取原因的异常信息
                exceptionType = cause.getClass().getName();
                exceptionMessage = cause.getMessage();
                //iv.进一步尝试获取原因的原因
                cause = cause.getCause();
            }
            throw e;
        } finally {
            //从当前线程上获取request对象
            HttpServletRequest request = RequestBinder.getRequest();
            //通过request对象获取session对象
            HttpSession session = request.getSession();
            //从session对象中获取登录的Admin和User
            User user = (User) session.getAttribute("loginUser");
            Admin admin = (Admin) session.getAttribute("loginAdmin");
            String userPart = (user == null)? "user未登录" : user.getUsername();
            String adminPart = (admin == null)? "admin未登录" : admin.getAdminName();
            String operator = userPart + "/" + adminPart;
            String outputData = (targetReturnValue == null)? "无返回值" : targetReturnValue.toString();
            Log log = new Log();
            log.setOperator(operator);
            log.setOperatorTime(new Date());
            log.setMethodName(methodName);
            log.setTypeName(typeName);
            log.setInputData(inputData);
            log.setOutputData(outputData);
            log.setExceptionType(exceptionType);
            log.setExceptionMessage(exceptionMessage);
            logService.saveLog(log);
        }
        //通知方法需要将目标方法的返回值继续返回给外部调用的对象
        return targetReturnValue;
    }

}
