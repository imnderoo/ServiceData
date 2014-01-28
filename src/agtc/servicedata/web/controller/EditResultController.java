/*
 * Created on Jun 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.web.controller;

import agtc.servicedata.model.*;
import agtc.servicedata.bus.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.util.*;
import org.springframework.web.util.WebUtils;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class EditResultController extends ServiceDataBasicController {
	
	
	private Log log = LogFactory.getLog(EditResultController.class);
	private GeneralManager generalManager;
	
	
	public EditResultController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) 
	throws ServletException {
		int resultId = RequestUtils.getIntParameter(request, "resultId",-1);
		Result result = generalManager.getResult(resultId);
		return result;
	}
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, 
			java.lang.Object command,BindException errors) throws Exception {
		Result result = (Result) command;
		generalManager.saveResult(result);
		
		String message = "Have successfully saved this result !";
		
		Map models = new HashMap();
		
		models.put("message",message);
		
		return new ModelAndView("successComplete", models);
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
