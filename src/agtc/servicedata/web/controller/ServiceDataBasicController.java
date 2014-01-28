/*
 * Created on May 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import java.text.*;
import org.springframework.web.bind.ServletRequestDataBinder;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.web.servlet.mvc.SimpleFormController;



/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceDataBasicController extends SimpleFormController {
	private Log log = LogFactory.getLog(ServiceDataBasicController.class);
	
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat,true));
		binder.registerCustomEditor(Float.class, null, new CustomNumberEditor(Float.class, true));
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, true));
	
	
	
	}	

	
}

