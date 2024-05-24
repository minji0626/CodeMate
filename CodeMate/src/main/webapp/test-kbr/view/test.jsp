<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 목록</title>
</head>
<body>
	<h1>이미지 목록</h1>
	<%
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
		// DB 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection("jdbc:oracle:thin:@211.63.89.70:1521:xe", "sgroup03", "s1234");
		stmt = conn.createStatement();

		// 이미지 파일 이름 가져오기
		String query = "SELECT hs_name,hs_photo FROM hard_skill";
		rs = stmt.executeQuery(query);

		// 이미지 파일들을 출력
		while (rs.next()) {
			String hardSkillName = rs.getString("hs_name");
			String imageName = rs.getString("hs_photo");
	%>
	<div>
		<span><%=hardSkillName%></span> <img
			src="${pageContext.request.contextPath}/images/hard_skill_logo/<%= imageName %>"
			alt="<%= imageName %>" width="20px">
	</div>
	<%
	}
	} catch (SQLException e) {
	e.printStackTrace();
	} finally {
	// DB 연결 해제
	try {
	if (rs != null)
		rs.close();
	if (stmt != null)
		stmt.close();
	if (conn != null)
		conn.close();
	} catch (SQLException e) {
	e.printStackTrace();
	}
	}
	%>
</body>
</html>
