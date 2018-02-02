/**
 * 
 */
package com.progresssoft.datawarehouse.to;

import java.io.Serializable;
import java.util.Date;

/**
 * @author s727953
 *
 */
public class ResponseWSTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer fileId;
	private String fileName;
	private Integer noOfTotalDeal;
	private Integer noOfInValidDeal;
	private Long processingTime;
	private Date uploadedDate;

	public ResponseWSTO() {
	}

	public ResponseWSTO(Integer fileId, String fileName, Integer noOfTotalDeal, Integer noOfInValidDeal,
			Long processingTime) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.noOfTotalDeal = noOfTotalDeal;
		this.noOfInValidDeal = noOfInValidDeal;
		this.processingTime = processingTime;
	}

	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the noOfTotalDeal
	 */
	public Integer getNoOfTotalDeal() {
		return noOfTotalDeal;
	}

	/**
	 * @param noOfTotalDeal
	 *            the noOfTotalDeal to set
	 */
	public void setNoOfTotalDeal(Integer noOfTotalDeal) {
		this.noOfTotalDeal = noOfTotalDeal;
	}

	/**
	 * @return the noOfInValidDeal
	 */
	public Integer getNoOfInValidDeal() {
		return noOfInValidDeal;
	}

	/**
	 * @param noOfInValidDeal
	 *            the noOfInValidDeal to set
	 */
	public void setNoOfInValidDeal(Integer noOfInValidDeal) {
		this.noOfInValidDeal = noOfInValidDeal;
	}

	/**
	 * @return the processingTime
	 */
	public Long getProcessingTime() {
		return processingTime;
	}

	/**
	 * @param processingTime
	 *            the processingTime to set
	 */
	public void setProcessingTime(Long processingTime) {
		this.processingTime = processingTime;
	}

	/**
	 * @return the uploadedDate
	 */
	public Date getUploadedDate() {
		return uploadedDate;
	}

	/**
	 * @param uploadedDate
	 *            the uploadedDate to set
	 */
	public void setUploadedDate(Date uploadedDate ) {
		this.uploadedDate = uploadedDate;
	}

}
