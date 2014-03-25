package br.com.kohen.eventmanager.clarion.ws.utils;

import static br.com.kohen.eventmanager.clarion.ws.utils.StringSafeNull.safeNull;
import static org.apache.commons.lang.math.NumberUtils.isNumber;

import org.apache.commons.lang.StringUtils;

public class CodeHandlerUtils {

	
	public static String haveAProtheusCode(String value) {
		
		String result = extractValueFromResponde(value);
		
		if (isNumber(result)) {
			return result;
		}
		
		result = extractValueFromRespondeOtherFormat(value);
		
		if (isNumber(result)) {
			return result;
		}
		
		return StringUtils.EMPTY;
	}
	
	public static Boolean isValidProtheusCode(String value) {
		return isNumber(haveAProtheusCode(value));
	}
	
	static String extractValueFromResponde(String valueReturned) {
		
		String[] split = valueReturned.split("\\/");
		
		if (split.length != 7) {
			return StringUtils.EMPTY; 
		}
		
		
		return split[5].trim();
	}
	
	static String extractValueFromRespondeOtherFormat(String valueReturned) {
		return safeNull(valueReturned).replaceAll("[:-]", "").trim();
		
	}
}
