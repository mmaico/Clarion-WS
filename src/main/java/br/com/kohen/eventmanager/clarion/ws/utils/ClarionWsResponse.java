package br.com.kohen.eventmanager.clarion.ws.utils;

import static br.com.kohen.eventmanager.clarion.ws.utils.StringSafeNull.safeNull;
import static org.apache.commons.lang.StringUtils.EMPTY;

public class ClarionWsResponse {

	private String valueReturned = EMPTY;
	
	private String error = EMPTY;

	public String getValueReturned() {
		return safeNull(valueReturned).replaceAll(":", EMPTY).trim();
	}

	public void setValueReturned(String valueReturned) {
		this.valueReturned = valueReturned;
	}

	public String getError() {
		return error + " - " +  this.valueReturned;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public Boolean isValid() {
		return error.isEmpty() && valueReturnedIsNumber();
	}
	
	public static ClarionWsResponse build() {
		return new ClarionWsResponse();
	}
	
	public ClarionWsResponse withValueReturned(String value) {
		this.valueReturned = value;
		return this;
	}
	
	public ClarionWsResponse withError(String error) {
		this.error = error;
		return this;
	}
	
	private Boolean valueReturnedIsNumber() {
		
		try {
			Long.valueOf(safeNull(this.valueReturned).replaceAll(":", "").trim());
			return Boolean.TRUE;
		} catch (NumberFormatException e) {
			return Boolean.FALSE;
		}
	}
}
