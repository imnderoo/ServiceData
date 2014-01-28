
package agtc.servicedata.web.controller;

import agtc.servicedata.model.*;
import agtc.servicedata.bus.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteResultsController extends ServiceDataBasicController {
	
	
	private Log log = LogFactory.getLog(DeleteResultsController.class);
	private GeneralManager generalManager;
	/**

	public DeleteResultsController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new Object();
	}
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, java.lang.Object command,BindException errors) throws Exception {
		
		int geneChipId = RequestUtils.getIntParameter(request, "geneChipId", -1);
		int projectId = RequestUtils.getIntParameter(request, "projectId", -1);
		String sampleUserId = RequestUtils.getStringParameter(request, "sampleUserId", "");
		if(!generalManager.containsSample(new Integer(projectId),sampleUserId)){
			String message = sampleUserId + " not exists ! <br>";
			Map myModel = new HashMap();
			myModel.put("message",message);
		
			return new ModelAndView("errorPage",myModel);
		}
		
		Sample sample = generalManager.getSampleByUniKey(new Integer(projectId),sampleUserId);
		
		
	   String message = "";
		try{
	   		generalManager.removeResults(new Integer(projectId),sampleUserId,new Integer(geneChipId));
	   		message = " Have successfully removed the results! <br>";
	   		return new ModelAndView("successComplete","message",message);
	   }catch(Exception e){
		   	message = " Could not remove the results! <br>";
		   	message += e.getMessage();
	   		return new ModelAndView("errorPage","message",message);
		}
		  
		
	}
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
			  java.lang.Object command,Errors errors)
	   throws java.lang.Exception
	{
		Map models = new HashMap();
		
		List allProjects = generalManager.getAllProjects();
		List allGeneChips = generalManager.getAllGeneChips();
		
		
		models.put("projectList",allProjects);
		models.put("geneChipList",allGeneChips);
		log.debug("referenceData is called");
		return models;
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
