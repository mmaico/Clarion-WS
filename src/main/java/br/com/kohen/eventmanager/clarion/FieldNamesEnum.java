package br.com.kohen.eventmanager.clarion;

public enum FieldNamesEnum {

	CENTRO_CUSTO("centrocusto"), 
	ESTADO("estado"), 
	TES("tes"), 
	EMPRESA("empresa"),
	ATIVE("active");
	
	private String value;
	
	private FieldNamesEnum(String value) {
		this.value = value;
	}
	
	public String get() {
		return value;
	}
}
