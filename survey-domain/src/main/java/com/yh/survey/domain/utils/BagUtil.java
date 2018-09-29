package com.yh.survey.domain.utils;

import com.yh.survey.domain.guest.pojo.Bag;
import com.yh.survey.domain.guest.pojo.Question;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.LoopTagStatus;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class BagUtil {

    /**
     * 用于参与调查时radio或checkbox回显
     *
     * @param pageContext
     * @return
     */
    public static String checkedRedisplay(PageContext pageContext) {
        ServletRequest request = pageContext.getRequest();
        HttpSession session = pageContext.getSession();
        //1.从Session域中获取allBagMap
        Map<Long, Map<String, String[]>> allBagMap = (Map<Long, Map<String, String[]>>) session.getAttribute("allBagMap");
        //2.从请求域中获取Bag对象
        Bag bag = (Bag) request.getAttribute("currentBag");
        //3.从Bag对象中获取bagId
        Long bagId = bag.getId();
        //4.从allBagMap中根据bagId获取对应的paramMap
        Map<String, String[]> paramMap = allBagMap.get(bagId);
        //5.由于刚刚从入口进入参与调查页面时，尚未将任何一个paramMap保存到allBagMap中，所以paramMap可能为null
        if (paramMap == null) {
            //如果确实为null，则返回空字符串，让页面上没有回显效果，避免空指针异常
            return "";
        }
        //6.在属性域中按照从小到大的顺序将Question对象取出来
        //JSP隐含对象：pageContext、request、session、application
        //EL表达式隐含对象：pageScope、requestScope.sessionScope、applicationScope
        Question question = (Question) pageContext.findAttribute("question");
        //7.通过Question对象获取questionId
        Long questionId = question.getId();
        //8.拼name属性的字符串
        String paramName = "q" + questionId;
        //9.根据paramName从paramMap中获取paramValueArr
        String[] paramValueArr = paramMap.get(paramName);
        //10.如果第一次进入当前包裹时没有填写任何数据就离开了，那么paramValueArr就不存在，也就是null，
        //所以为避免空指针异常，需要进行判断
        if (paramValueArr == null) {
            return "";
        }
        //11.获取当前标签的value属性值
        //①获取LoopTagStatus对象
        LoopTagStatus status = (LoopTagStatus) pageContext.findAttribute("myStatus");
        //②获取index
        int index = status.getIndex();
        //③将index转换为String类型作为value属性值
        String currentValue = index + "";
        //12.将paramValueArr转换为集合对象
        List<String> paramValueList = Arrays.asList(paramValueArr);
        //13.检查currentValue是否在paramValueList中
        if (paramValueList.contains(currentValue)) {
            //如果在，说明以前来这个包裹时，当前radio或checkbox被选中过，所以现在也需要设置为被选中
            return "checked='checked'";

        } else {
            return "";
        }
    }

    /**
     * 用于参与调查时text回显
     *
     * @param pageContext
     * @return
     */
    public static String valueRedisplay(PageContext pageContext) {
        ServletRequest request = pageContext.getRequest();
        HttpSession session = pageContext.getSession();
        //1.从Session域中获取allBagMap
        Map<Long, Map<String, String[]>> allBagMap = (Map<Long, Map<String, String[]>>) session.getAttribute("allBagMap");
        //2.从请求域中获取Bag对象
        Bag bag = (Bag) request.getAttribute("currentBag");
        //3.从Bag对象中获取bagId
        Long bagId = bag.getId();
        //4.从allBagMap中根据bagId获取对应的paramMap
        Map<String, String[]> paramMap = allBagMap.get(bagId);
        //5.由于刚刚从入口进入参与调查页面时，尚未将任何一个paramMap保存到allBagMap中，所以paramMap可能为null
        if (paramMap == null) {
            //如果确实为null，则返回空字符串，让页面上没有回显效果，避免空指针异常
            return "";
        }
        //6.在属性域中按照从小到大的顺序将Question对象取出来
        //JSP隐含对象：pageContext、request、session、application
        //EL表达式隐含对象：pageScope、requestScope.sessionScope、applicationScope
        Question question = (Question) pageContext.findAttribute("question");
        //7.通过Question对象获取questionId
        Long questionId = question.getId();
        //8.拼name属性的字符串
        String paramName = "q" + questionId;
        //9.根据paramName从paramMap中获取paramValueArr
        String[] paramValueArr = paramMap.get(paramName);
        //10.如果第一次进入当前包裹时没有填写任何数据就离开了，那么paramValueArr就不存在，也就是null，
        //所以为避免空指针异常，需要进行判断
        if (paramValueArr == null || paramValueArr.length == 0) {
            return "";
        }
        //11.对于普通文本框来说，即使以前曾经提交过数据，数组中最多也只有一个元素，
        //所以直接取出唯一的元素用来设置value属性即可
        return paramValueArr[0];
    }
}
