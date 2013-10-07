package br.com.kohen.eventmanager.clarion;

import static org.apache.commons.lang.StringUtils.EMPTY;

public enum WsInfoEnum {

	INTERNATIONAL_CNPJ("140"),
	INTERNATIONAL_UF("EX"),
	INTERNATIONAL_CITY(EMPTY),
	QUANTITY_DAYS_TO_PAY("5"),
	WS_KEY("ABC1000");
	
	private String value;
	
	WsInfoEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
