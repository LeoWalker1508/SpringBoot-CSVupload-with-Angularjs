/**
 * 
 */
package com.everythingisdata.datawarehouse.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author everythingisdata
 *
 */
@Entity
@Table(name = "DEAL_VALID_DATA")
public class DealValidDataEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2293015492430082803L;
	@Id
	@GeneratedValue
	private Integer dealId;

	@NotNull(message = "Deal unique Id is mandatory")
	@NotBlank
	// Deal Unique Id
	private String dealUniqueId;

	@NotNull
	@NotBlank
	@Size(min = 3, max = 3, message = "FROM Currency ISO Code should be 3 Character")
	// From Currency ISO Code "Ordering Currency"
	private String fromCurrency;

	// To Currency ISO Code
	private String toCurrency;

	@NotNull
	private Date dealTimeStamp;

	// Deal Amount in ordering currency
	private String dealAmount;

	@Column(name = "file_Id")
	private Integer fileId;
	// @ManyToOne
	// @JoinColumn(name = "file_Id")
	// private UploadedFile uploadedFile;

	public DealValidDataEntity() {
	}

	public DealValidDataEntity(Integer fileId) {
		super();
		this.fileId = fileId;
	}

	public DealValidDataEntity(Integer dealId, String dealUniqueId, String fromCurrency, String toCurrency,
			Date dealTimeStamp, String dealAmount, Integer fileId) {
		super();
		this.dealId = dealId;
		this.dealUniqueId = dealUniqueId;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.dealTimeStamp = dealTimeStamp;
		this.dealAmount = dealAmount;
		this.fileId = fileId;
	}

	/**
	 * @return the dealId
	 */
	public Integer getDealId() {
		return dealId;
	}

	/**
	 * @param dealId the dealId to set
	 */
	public void setDealId(Integer dealId) {
		this.dealId = dealId;
	}

	/**
	 * @return the dealUniqueId
	 */
	public String getDealUniqueId() {
		return dealUniqueId;
	}

	/**
	 * @param dealUniqueId the dealUniqueId to set
	 */
	public void setDealUniqueId(String dealUniqueId) {
		this.dealUniqueId = dealUniqueId;
	}

	/**
	 * @return the fromCurrency
	 */
	public String getFromCurrency() {
		return fromCurrency;
	}

	/**
	 * @param fromCurrency the fromCurrency to set
	 */
	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	/**
	 * @return the toCurrency
	 */
	public String getToCurrency() {
		return toCurrency;
	}

	/**
	 * @param toCurrency the toCurrency to set
	 */
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	/**
	 * @return the dealTimeStamp
	 */
	public Date getDealTimeStamp() {
		return dealTimeStamp;
	}

	/**
	 * @param dealTimeStamp the dealTimeStamp to set
	 */
	public void setDealTimeStamp(Date dealTimeStamp) {
		this.dealTimeStamp = dealTimeStamp;
	}

	/**
	 * @return the dealAmount
	 */
	public String getDealAmount() {
		return dealAmount;
	}

	/**
	 * @param dealAmount the dealAmount to set
	 */
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DealValidDataEntity [dealId=" + dealId + ", dealUniqueId=" + dealUniqueId + ", fromCurrency="
				+ fromCurrency + ", toCurrency=" + toCurrency + ", dealTimeStamp=" + dealTimeStamp + ", dealAmount="
				+ dealAmount + ", fileId=" + fileId + "]";
	}

}
