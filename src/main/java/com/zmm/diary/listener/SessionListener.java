package com.zmm.diary.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/4/10
 * Email:65489469@qq.com
 */
public class SessionListener implements HttpSessionListener {

    private SessionContext sessionContext = SessionContext.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("创建一个session");

        HttpSession session = httpSessionEvent.getSession();
        String sessionValue = (String) session.getAttribute("diary_session");
        System.out.println("获取验证码sessionValue333 = "+sessionValue);
//        sessionContext.AddSession(session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("销毁一个session");

//        HttpSession session = httpSessionEvent.getSession();
//        sessionContext.DelSession(session);
    }
}
