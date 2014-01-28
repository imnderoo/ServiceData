package agtc.servicedata.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Run {
	private Integer runId;
	private Integer projectId;
	private Date    runDate;
	private String  note;
	private String date;
	
	public Run(){
		this.runId= new Integer(-1);
	}
	
	public Run(Integer runId,Integer projectId, Date runDate, String note){
		this.runId=runId;
		this.projectId=projectId;
		this.runDate=runDate;
		this.note=note;
		this.date = DateToISOString(runDate);	 
	}
	
	public static String DateToISOString(Date d){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Date getRunDate() {
		return runDate;
	}
	public void setRunDate(Date runDate) {
		this.date = DateToISOString(runDate);
		this.runDate = runDate;
	}
	public Integer getRunId() {
		return runId;
	}
	public void setRunId(Integer runId) {
		this.runId = runId;
	}

	public String getDate() {
		return date;
	}
	 
}
