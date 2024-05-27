package kr.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringUtil {
	// HTML 태그를 허용하면서 줄바꿈
	public static String useBrHtml(String str) {
		if (str == null)
			return null;

		return str.replaceAll("\r\n", "<br>").replaceAll("\r", "<br>").replaceAll("\n", "<br>");
	}

	// HTML 태그를 허용하지 않으면서 줄바꿈
	public static String useBrNoHTML(String str) {
		if (str == null)
			return null;

		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>").replaceAll("\r", "<br>")
				.replaceAll("\n", "<br>");
	}

	// HTML를 허용하지 않음
	public static String useNoHTML(String str) {
		if (str == null)
			return null;

		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	// 큰 따옴표 처리
	public static String parseQuot(String str) {
		if (str == null)
			return null;

		return str.replaceAll("\"", "&quot;");
	}

	// String으로 받아온 날짜값 number로 변환
	public static int convertDateToNumber(String dateString) {
		// 문자열에서 "-"를 제거하고 숫자로 변환
		return Integer.parseInt(dateString.replace("-", ""));
	}

	// String으로 받아온 날짜값을 Date로 변환
	public static Date convertStringToDate(String dateString) {
		// SimpleDateFormat을 사용하여 문자열을 Date로 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			// 문자열을 java.util.Date로 변환한 뒤, 이를 java.sql.Date로 변환
			java.util.Date utilDate = sdf.parse(dateString);
			date = new Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
			// 예외 처리: 필요에 따라 로그를 남기거나 다른 처리를 할 수 있음
		}
		return date;
	}

}
