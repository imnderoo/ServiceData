package agtc.servicedata.web.controller;

import agtc.servicedata.model.*;
import agtc.servicedata.bus.*;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jianan Xiao      2005-11-16
 *
 *  Used to modify one result's note
 */

public class EditRunDateController extends ServiceDataBasicController {
	
	private GeneralManager generalManager;
	
	public EditRunDateController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);		
	}
	
	protected Object formBackingObject(HttpServletRequest request) 
						throws ServletException 
	{
		int runId = RequestUtils.getIntParameter(request, "runId",-1);
		Run run = generalManager.getRun(runId);
		return run;
	}
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request,
								javax.servlet.http.HttpServletResponse response, 
								java.lang.Object command,BindException errors) 
								throws Exception {
		Run run = (Run) command;
		generalManager.saveRun(run);
		
		String message = "Have successfully saved this result's note !";
		
		Map models = new HashMap();
		
		models.put("message",message);
		
		return new ModelAndView("successComplete", models);
	}
	
	protected java.util.Map referenceData(
			javax.servlet.http.HttpServletRequest request,
			java.lang.Object command,Errors errors)
			 throws java.lang.Exception
	{
		Map models = new HashMap();
		int runId = RequestUtils.getIntParameter(request, "runId",-1);
		List uniassay = generalManager.getUniAssaysByRunId(runId);
		models.put("uniAssayList",uniassay);
		models.put("runId",(new Integer(runId)).toString());
//		log.debug("referenceData is called");
		return models;
	}

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
