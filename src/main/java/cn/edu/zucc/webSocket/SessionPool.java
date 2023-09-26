package cn.edu.zucc.webSocket;

/**
 * @program: finalWorkDemo0208
 * @description:
 * @author: chen hang
 * @create: 2022-03-25 23:46
 */
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionPool {
    //key-value : userId - 会话（系统创建）
    public static Map<String, Session> sessions = new ConcurrentHashMap<>();//避免多线程问题

    public static void close(String sessionId) {
        //sessionId是在session中添加了一个标识，准确定位某条session
        for (String userId : SessionPool.sessions.keySet()) {
            Session session = SessionPool.sessions.get(userId);
            if (session.getId().equals(sessionId)) {
                sessions.remove(userId);
                break;
            }
        }
    }

    public static void sendMessage(String userId, String message) {
        sessions.get(userId).getAsyncRemote().sendText(message);
    }


    //消息的群发，业务逻辑的群发
    public static void sendMessage(String message) {
        for (String sessionId : SessionPool.sessions.keySet()) {
            SessionPool.sessions.get(sessionId).getAsyncRemote().sendText(message);
        }
    }

    //点对点的消息推送
    public static void sendMessage(Map<String, Object> params) {
        String userId = params.get("formUserId").toString();
        String toUserId = params.get("toUserId").toString();
        String msg = params.get("message").toString();
        //获取用户session
        Session session = sessions.get(toUserId);

        //session不为空的情况下进行点对点推送
        if (session != null) {
            session.getAsyncRemote().sendText(msg);
        }
    }
}