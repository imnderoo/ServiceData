/*
 * Created on May 11, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.model;
import java.util.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Project {
	private Integer projectId;
	private String name;
	private String note;
	
	public Project(){
		
	}
	
	
	public Project(Integer i,String s1,String s2){
		projectId = i;
		name = s1;
		note = s2;
	}
	
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
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
	 * @return Returns the projectId.
	 */
	public Integer getProjectId() {
		return projectId;
	}
	/**
	 * @param projectId The projectId to set.
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
}
