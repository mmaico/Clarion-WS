package br.com.kohen.eventmanager.clarion.ws.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CLARION_PURCHASE_IMPORT")
public class ImportPurchaseError {
	
	@Id
	@Column(name="ID_PURCHASE")
	private Long id;
	
	@Column(name="CAUSE_INTERNAL", columnDefinition="TEXT")
	private String cause;
	
	@Column(name="CAUSE_EXTERNAL",  columnDefinition="TEXT")
	
	private String causeEx;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE")
	private Date date = new Date();
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public static ImportPurchaseError exError(String message) {
		ImportPurchaseError purchaseError = new ImportPurchaseError();
		purchaseError.setCauseEx(message);
		
		return purchaseError;
	}
	
	public static ImportPurchaseError internalError(String message) {
		ImportPurchaseError purchaseError = new ImportPurchaseError();
		purchaseError.setCause(message);
		
		return purchaseError;
	}
	
	public ImportPurchaseError purchaseId(Long id) {
		this.setId(id);
		return this;
	}
	
	
}
