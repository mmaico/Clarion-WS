package br.com.kohen.eventmanager.clarion;

public enum CountryEnum {

	BRAZIL_CODE(31l);
	
	private Long code;
	
	CountryEnum(Long code) {
		this.code = code;
	}

	public Long getCode() {
		return code;
	}
	
}
