package com.example.template.service.impl;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@Slf4j
@RequiredArgsConstructor
@ServerEndpoint("/websocket/test/{username}")
@SuppressWarnings("unused")
public class WebSocketService {
    Session session;
    String username;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    //虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
    //  注：底下WebSocket是当前类名
    private static final CopyOnWriteArraySet<WebSocketService> webSocketServices = new CopyOnWriteArraySet<>();
    // 用来存在线连接用户信息
    private static final ConcurrentHashMap<String, Session> sessionPool = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        try {
            this.session = session;
            this.username = username;
            webSocketServices.add(this);
            sessionPool.put(username, session);
            log.info("[WebSocketService 链接] 当前: " + webSocketServices.size());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @OnClose
    public void onClose() {
        log.info("[WebSocketService 断开] 当前: " + webSocketServices.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("[WebSocketService 消息] 内容: " + message);
    }

    @OnError
    public void onError(Session session, @NotNull Throwable throwable) {
        log.error("[WebSocketService 错误] 内容：" + throwable.getMessage());
    }


    public void sendMessageToAll(String message) {
        log.error("[WebSocketService 群发消息] 内容：" + message);
        for (WebSocketService webSocketService : webSocketServices) {
            try {
                if (webSocketService.session.isOpen()) {
                    webSocketService.session.getAsyncRemote().sendText(message);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public void sendMessageTo(String message, String username) {
        Session session = sessionPool.get(username);
        if (session != null && session.isOpen()) {
            try {
                log.error("[WebSocketService 单点消息] 内容：" + message);
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    public void sendMessageTo(String message, @NotNull String... usernames) {
        for (String username : usernames) {
            Session session = sessionPool.get(username);
            sendMessageTo(message, username);
        }
    }
}
