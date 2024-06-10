//package kr.util;
//
//import javax.servlet.http.HttpSession;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.Map;
//
//public class SessionManager {
//	// Thread-safe map to store user sessions
//	private static Map<Integer, HttpSession> userSessions = new ConcurrentHashMap<>();
//
//	public static void addUserSession(int mem_num, HttpSession session) { // 로그인시
//		userSessions.put(mem_num, session);
//	}
//
//	public static void removeUserSession(int mem_num) { // 로그아웃시
//		userSessions.remove(mem_num);
//	}
//
//	public static void invalidateUserSession(int mem_num) {
//		HttpSession userSession = userSessions.get(mem_num);
//		if (userSession != null) {
//			removeUserSession(mem_num);
//			userSession.invalidate();
//		}
//	}
//
//}