/**
 * 
 */
package com.progresssoft.datawarehouse.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author s727953
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
	private String id;

	@NotNull(message = "Deal unique Id is compulsory")
	// Deal Unique Id
	private String dealUniqueId;

	@NotNull
	@Size(min = 3, max = 3, message = "FROM Currency ISO Code should be 3 Character")
	// From Currency ISO Code "Ordering Currency"
	private String fromCurrency;

	// To Currency ISO Code
	private String toCurrency;

	@NotNull
	// Deal timestamp
	private Date dealTimeStamp;

	// Deal Amount in ordering currency
	private double dealAmount;

	@Column(name = "file_Id")
	private Integer fileId;
	// @ManyToOne
	// @JoinColumn(name = "file_Id")
	// private UploadedFile uploadedFile;

	public DealValidDataEntity() {
	}

	public DealValidDataEntity(String id, String dealUniqueId, String fromCurrency, String toCurrency,
			Date dealTimeStamp, double dealAmount, Integer fileId) {
		super();
		this.id = id;
		this.dealUniqueId = dealUniqueId;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.dealTimeStamp = dealTimeStamp;
		this.dealAmount = dealAmount;
		this.fileId = fileId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDealUniqueId() {
		return dealUniqueId;
	}

	public void setDealUniqueId(String dealUniqueId) {
		this.dealUniqueId = dealUniqueId;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public Date getDealTimeStamp() {
		return dealTimeStamp;
	}

	public void setDealTimeStamp(Date dealTimeStamp) {
		this.dealTimeStamp = dealTimeStamp;
	}

	public double getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(double dealAmount) {
		this.dealAmount = dealAmount;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

}
