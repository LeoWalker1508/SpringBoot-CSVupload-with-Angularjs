/**
 * 
 */
package com.progresssoft.datawarehouse.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author s727953
 *
 */
@Entity
@Table(name = "DEAL_INVALID_DATA")
public class DealInValidDataEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5348055813277594272L;
	@Id
	@GeneratedValue
	private String id;
	private String dealUniqueId;
	private String fromCurrency;
	private String toCurrency;
	private String dealTimeStamp;
	private String dealAmount;
	// @ManyToOne
	// @JoinColumn(name = "file_id")
	// private UploadedFile uploadedFile;

	@Column(name = "file_Id")
	private Integer fileId;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dealUniqueId
	 */
	public String getDealUniqueId() {
		return dealUniqueId;
	}

	/**
	 * @param dealUniqueId
	 *            the dealUniqueId to set
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
	 * @param fromCurrency
	 *            the fromCurrency to set
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
	 * @param toCurrency
	 *            the toCurrency to set
	 */
	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	/**
	 * @return the dealTimeStamp
	 */
	public String getDealTimeStamp() {
		return dealTimeStamp;
	}

	/**
	 * @param dealTimeStamp
	 *            the dealTimeStamp to set
	 */
	public void setDealTimeStamp(String dealTimeStamp) {
		this.dealTimeStamp = dealTimeStamp;
	}

	/**
	 * @return the dealAmount
	 */
	public String getDealAmount() {
		return dealAmount;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return "DealInValidData [Id=" + id + ", dealUniqueId=" + dealUniqueId + ", fromCurrency=" + fromCurrency
				+ ", toCurrency=" + toCurrency + ", dealTimeStamp=" + dealTimeStamp + ", dealAmount=" + dealAmount
				+ ", uploadedFile=" + fileId + "]";
	}

	/**
	 * @param dealAmount
	 *            the dealAmount to set
	 */
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

}
