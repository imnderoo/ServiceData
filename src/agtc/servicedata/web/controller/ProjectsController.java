/*
 * Created on May 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.web.controller;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.servicedata.bus.*;
import agtc.servicedata.model.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.*;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.validation.*;

import java.util.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProjectsController extends ServiceDataBasicController {
	private GeneralManager generalManager;
	
	private Log log = LogFactory.getLog(ProjectsController.class);
	/* (non-Javadoc)
	 * @see agtc.sampletracking.web.controller.BasicController#showFormAfterAllowed(null, null, org.springframework.validation.BindException)
	 */
	public ProjectsController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) 
		throws ServletException {
		// get the Owner referred to by id in the request
		//log.debug("project name is " + projectManager.getProject(new Integer(RequestUtils.getRequiredIntParameter(request, "projectId"))).getName());
		int i = RequestUtils.getIntParameter(request, "projectId",-1);
		if(i==-1){
			Project project = new Project();
			project.setProjectId(new Integer(-1));
			return project;
		}else{
			//Project project = projectManager.getProject(new Integer(i));
			return generalManager.getProject(new Integer(i));
		}
	}

	protected ModelAndView onSubmit(
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response, 
			java.lang.Object command,BindException errors) 
			throws Exception {
		Project project = (Project) command;
		String name = project.getName();
		if(name == null || name.trim().length()== 0){
			errors.rejectValue("name","error.empty","Can not be blank");
			return showForm(request, response, errors);
		}
		Integer projectId = null;
		try{
			projectId = generalManager.saveProject(project);
		}catch(Exception e){
			log.debug(e.getMessage());
			errors.rejectValue( "name","error.notUnique",
					new String[]{project.getName()},"Not unique");
			return showForm(request, response, errors);
		}
		
/*		//Store the selected project to session
		org.springframework.web.util.WebUtils.setSessionAttribute(
				request,"SelectedProject",project);
*/
		
		ModelAndView view = new ModelAndView(new RedirectView(getSuccessView()));
		Map myModel = view.getModel();
		myModel.put("message","Have successfully saved this project !");
		myModel.put("projectId",projectId);
		return view;
	}
	
	protected java.util.Map referenceData(
			javax.servlet.http.HttpServletRequest request,
			java.lang.Object command,Errors errors)
			 throws java.lang.Exception
	{
		Map models = new HashMap();
		List allProjects = generalManager.getAllProjects();
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		models.put("allProjects",allProjects);
//		log.debug("referenceData is called");
		return models;
	}
	

	/**
	protected ModelAndView processFormSubmission(javax.servlet.http.HttpServletRequest request,
												 javax.servlet.http.HttpServletResponse response,
												 java.lang.Object command,
												 BindException errors)
										  throws java.lang.Exception
	{
		Project project = (Project) command;
		log.debug(project);
		log.debug(errors);
		return null;
	}
	*/
	
	

	/**
	 * @return Returns the generalManager.
	 */
	public GeneralManager getGeneralManager() {
		return generalManager;
	}
	/**
	 * @param generalManager The generalManager to set.
	 */
	public void setGeneralManager(GeneralManager generalManager) {
		this.generalManager = generalManager;
	}
}
