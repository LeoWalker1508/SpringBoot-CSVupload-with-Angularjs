/**
 * 
 */
package com.progresssoft.datawarehouse.to;

import java.io.Serializable;

/**
 * @author s727953
 *
 */
public class DealInValidDataWSTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5348055813277594272L;

	private String dealUniqueId;
	private String fromCurrency;
	private String toCurrency;
	private String dealTimeStamp;
	private String dealAmount;

	private Integer fileId;

	public DealInValidDataWSTO() {
	}

	public DealInValidDataWSTO(Integer fileId) {
		super();
		this.fileId = fileId;
	}

	public DealInValidDataWSTO(String dealUniqueId, String fromCurrency, String toCurrency, String dealTimeStamp,
			String dealAmount) {
		super();
		this.dealUniqueId = dealUniqueId;
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.dealTimeStamp = dealTimeStamp;
		this.dealAmount = dealAmount;
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

	/**
	 * @param dealAmount
	 *            the dealAmount to set
	 */
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@Override
	public String toString() {
		return "DealInValidDataWSTO [dealUniqueId=" + dealUniqueId + ", fromCurrency=" + fromCurrency + ", toCurrency="
				+ toCurrency + ", dealTimeStamp=" + dealTimeStamp + ", dealAmount=" + dealAmount + "]";
	}

}
