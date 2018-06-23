package com.example.api.util;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author haijun
 * @class ${classname}
 * @date 2018/6/20, 15:42
 */
public class MySessionListener implements SessionListener {


    private final AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {
        sessionCount.incrementAndGet();
        System.out.println("登录+1=="+sessionCount.get());
    }

    @Override
    public void onStop(Session session) {
        sessionCount.decrementAndGet();
        System.out.println("登录退出-1=="+sessionCount.get());
    }

    @Override
    public void onExpiration(Session session) {
        sessionCount.decrementAndGet();
        System.out.println("登录过期-1=="+sessionCount.get());

    }

    public int getSessionCount() {
        return sessionCount.get();
    }
}
