/**
 * 
 */
package com.progresssoft.datawarehouse.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author s727953
 *
 */
@Entity
@Table(name = "Upload_csv_file")
public class UploadedFileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Field_ID")
	private Integer fileId;

	@NotNull
	@NotBlank
	private String fileName;
	private Integer noOfTotalDeal;
	private Integer noOfInValidDeal;
	@Column(name = "Processing_Time")
	private Long processingTime;

	@Column(name = "uploaded_date", nullable = false, insertable = true, updatable = false)
	private Date uploadedDate = new Date();

	public UploadedFileEntity() {
	}

	public UploadedFileEntity(String fileName) {
		super();
		this.fileName = fileName;
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
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "uploadedFile")
	// private List<DealValidData> dealValidDatas = null;
	//
	// @OneToMany(cascade = CascadeType.ALL, mappedBy = "uploadedFile")
	// private List<DealInValidData> dealInValidDatas = null;

}
