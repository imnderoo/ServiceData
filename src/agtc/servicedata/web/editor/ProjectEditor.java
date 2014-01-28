/*
 * Created on May 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.web.editor;

import java.beans.PropertyEditorSupport;

import agtc.servicedata.dao.ProjectDAO;
import agtc.servicedata.model.Project;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProjectEditor extends PropertyEditorSupport {
	private ProjectDAO projectDAO;
	public void setAsText(String projectId) throws IllegalArgumentException { 
		 
		 // text is the string from the form, the projectId is the identifier in 
		 // the database 
    
		 Project project=null; 
		  if(projectId!=null&&!projectId.equals("")){ 
			project=projectDAO.getProject(new Integer(projectId));       
		  }      
		  setValue(project); 
     
	   } 

	   public String getAsText(Object value) { 
	   //return the id value of the object 
    
		   Project project = (Project) value; 
		   return String.valueOf(project.getProjectId()); 
	   }
	
	/**
	 * @return
	 */
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	/**
	 * @param projectDAO
	 */
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}


}
