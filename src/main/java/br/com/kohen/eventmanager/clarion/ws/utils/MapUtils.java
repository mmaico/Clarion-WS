package br.com.kohen.eventmanager.clarion.ws.utils;

import java.util.Map;

import br.com.kohen.eventmanager.clarion.FieldNamesEnum;

public class MapUtils {

	public static Boolean isValid(Map<String, String> settings) {
		
		if (settings == null) {
			return Boolean.FALSE;
		}
		
		if (settings.isEmpty()) {
			return Boolean.FALSE;
		}
		
		if (settings.size() < FieldNamesEnum.values().length) {
			return Boolean.FALSE;
		}
		
		return Boolean.TRUE;
	}
}
