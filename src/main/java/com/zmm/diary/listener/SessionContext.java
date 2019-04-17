package com.zmm.diary.listener;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Description:
 * Author:zhangmengmeng
 * Date:2019/4/10
 * Email:65489469@qq.com
 */
public class SessionContext {

    private static SessionContext instance;
    private HashMap mymap;

    private SessionContext() {
        mymap = new HashMap();
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = new SessionContext();
        }
        return instance;
    }

    public synchronized void AddSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }

    public synchronized void DelSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String session_id) {
        if (session_id == null) return null;
        return (HttpSession) mymap.get(session_id);
    }
}
