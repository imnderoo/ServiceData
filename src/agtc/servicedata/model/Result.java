/*
 * Created on May 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.model;


/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Result {
	private Integer resultId;
	private String result;
	private String sampleId;
	private String assay;
	private Integer runId;
	private String note;
	
	
	/**
	 * @return Returns the note.
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note The note to set.
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return Returns the result.
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result The result to set.
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return Returns the resultId.
	 */
	public Integer getResultId() {
		return resultId;
	}
	/**
	 * @param resultId The resultId to set.
	 */
	public void setResultId(Integer resultId) {
		this.resultId = resultId;
	}
	
	/**
	 * @return Returns the assay.
	 */
	public String getAssay() {
		return assay;
	}
	/**
	 * @param assay The assay to set.
	 */
	public void setAssay(String assay) {
		this.assay = assay;
	}
	
	public Integer getRunId() {
		return runId;
	}
	public void setRunId(Integer runId) {
		this.runId = runId;
	}
	/**
	 * @return Returns the sampleId.
	 */
	public String getSampleId() {
		return sampleId;
	}
	/**
	 * @param sampleId The sampleId to set.
	 */
	public void setSampleId(String sampleId) {
		this.sampleId = sampleId;
	}
	
}
