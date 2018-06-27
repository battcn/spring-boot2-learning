package com.battcn.utils;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Levin
 * @since 2018/6/26 0026
 */
public final class WebSocketUtils {

    /**
     * 模拟存储 websocket session 使用
     */
    public static final Map<String, Session> LIVING_SESSIONS_CACHE = new ConcurrentHashMap<>();

    public static void sendMessageAll(String message) {
        LIVING_SESSIONS_CACHE.forEach((sessionId, session) -> sendMessage(session, message));
    }

    /**
     * 发送给指定用户消息
     *
     * @param session 用户 session
     * @param message 发送内容
     */
    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
