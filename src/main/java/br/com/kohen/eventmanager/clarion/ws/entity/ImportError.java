package br.com.kohen.eventmanager.clarion.ws.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLARION_IMPORT")
public class ImportError {

	@Id
	@Column(name="ID_COMPANY")
	private Long id;
	
	@Column(name="CAUSE_INTERNAL", columnDefinition="TEXT")
	private String cause;
	
	@Column(name="CAUSE_EXTERNAL", columnDefinition="TEXT")
	private String causeEx;

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCauseEx() {
		return causeEx;
	}

	public void setCauseEx(String causeEx) {
		this.causeEx = causeEx;
	}
	
	public static ImportError internalError(String message) {
		ImportError importError = new ImportError();
		importError.setCause(message);
		
		return importError;
		
	}
	
	public static ImportError exError(String message) {
		ImportError importError = new ImportError();
		importError.setCauseEx(message);
		
		return importError;
		
	}
	
	
	public ImportError companyId(Long id) {
		this.setId(id);
		
		return this;
	}
	
	
	
}
