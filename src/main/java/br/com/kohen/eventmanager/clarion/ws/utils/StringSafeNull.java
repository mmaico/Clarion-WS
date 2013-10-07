package br.com.kohen.eventmanager.clarion.ws.utils;

import org.apache.commons.lang.StringUtils;

public class StringSafeNull {

	public static String safeNull(String value) {
		if (value == null) {
			return StringUtils.EMPTY;
		}
		
		return value;
	}
}
