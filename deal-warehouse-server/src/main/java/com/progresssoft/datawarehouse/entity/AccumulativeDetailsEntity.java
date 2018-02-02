package com.progresssoft.datawarehouse.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCUMULATIVE_DETAILS")
public class AccumulativeDetailsEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2914423773582885341L;
	/**
	 * 
	 */
	@Id
	@GeneratedValue
	private Integer id;
	private String currencyIsoCode;
	private Long dealsCount;

	public AccumulativeDetailsEntity() {
	}

	/**
	 * @param currencyIsoCode
	 * @param dealsCount
	 */
	public AccumulativeDetailsEntity(String currencyIsoCode, Long dealsCount) {
		super();
		this.currencyIsoCode = currencyIsoCode;
		this.dealsCount = dealsCount;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the currencyIsoCode
	 */
	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}

	/**
	 * @param currencyIsoCode
	 *            the currencyIsoCode to set
	 */
	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}

	/**
	 * @return the dealsCount
	 */
	public Long getDealsCount() {
		return dealsCount;
	}

	/**
	 * @param dealsCount
	 *            the dealsCount to set
	 */
	public void setDealsCount(Long dealsCount) {
		this.dealsCount = dealsCount;
	}
}
