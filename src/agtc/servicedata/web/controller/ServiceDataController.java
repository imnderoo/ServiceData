package agtc.servicedata.web.controller;

import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import agtc.servicedata.model.*;
import agtc.servicedata.bus.*;
/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ServiceDataController extends MultiActionController {
	private GeneralManager generalManager;
	
	private Log log = LogFactory.getLog(ServiceDataController.class);
	
	
	public ModelAndView projectDetailsHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		int projectId = RequestUtils.getIntParameter(request, "projectId", -1);
		Project project = generalManager.getProject(new Integer(projectId));
		List uniRunDates = generalManager.getUniRunDate(projectId);
		if (project == null) {
			return new ModelAndView(new RedirectView("projects.htm"));
		}
		
		//		Store the selected project to session
		org.springframework.web.util.WebUtils.setSessionAttribute(
				request,"SelectedProject",project);
		
		Map models = new HashMap();
		models.put("command",project);
		models.put("uniRunDates",uniRunDates);
		
		String message = RequestUtils.getStringParameter(request, "message","");
		if(!message.equals("")){
			models.put("message",message);
		}
		return new ModelAndView("projectDetails", models);
	}
	
	public ModelAndView deleteResultsHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		int projectId = RequestUtils.getIntParameter(request, "projectId", -1);
		String dateS = RequestUtils.getStringParameter(request, "date","");
		//log.debug("the date is " + dateS);
		Timestamp timestamp = Timestamp.valueOf(dateS);
		
		generalManager.removeResults(projectId,timestamp);
		
		String message = "Have successfully deleted these results !";
		
		Map models = new HashMap();
		
		models.put("message",message);
		
		return new ModelAndView("successComplete", models);
	}
	
	// it is very slow to send the hyperlinked result using JSP, so sent it using output stream
	public ModelAndView runDetailsHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int projectId = RequestUtils.getIntParameter(request, "projectId", -1);
		String dateS = RequestUtils.getStringParameter(request, "runDate","");
		//log.debug("the date is " + dateS);
		dateS=dateS.trim()+".0";
		Timestamp timestamp = Timestamp.valueOf(dateS);
		//log.debug("timestamp is " + timestamp);
		List sampleIds = generalManager.getUniSamplesByRunDate(projectId,timestamp);
		List assays  = generalManager.getUniAssaysByRunDate(projectId,timestamp);
		List searchResults = generalManager.getResultsByRunDate(projectId,timestamp);
		//log.debug("the results size is " + searchResults.size());
		ResultFormatter rm = new ResultFormatter(sampleIds,assays,searchResults,"no");
		List rs = rm.formatResults();
		sampleIds = rm.getFormattedSampleIds();
		Map models = new HashMap();
		
		models.put("assays",assays);
		models.put("sampleIds",sampleIds);
		models.put("results",rs);
		models.put("date",dateS);
		models.put("projectId", (new Integer(projectId)).toString());
		
		return new ModelAndView("runDetails",models);

		/***************************************
		StringBuffer sbFilename = new StringBuffer();
		sbFilename.append("FileName_");
		sbFilename.append(".txt");
		
		response.setHeader("Cache-Control", "max-age=30");
		
		response.setContentType("text/html");
		StringBuffer sbContentDispValue = new StringBuffer();
		sbContentDispValue.append("inline");
		sbContentDispValue.append("; filename=");
		sbContentDispValue.append(sbFilename);
		response.setHeader(
				"Content-disposition",
				sbContentDispValue.toString());		
		// make the table here, as I could not find a easy way to do it in JSP
		StringBuffer tables = new StringBuffer("<html><body>");
		tables.append("<a href=\"deleteResults.htm?projectId=").append(projectId).append("&date=").append(dateS).append("\">Delete These Results</a><br>");
		tables.append("<table border=\"1\" >");
		//the head
		tables.append("<tr><th></th>");
		for(int a=0;a<assays.size();a++){
			String oneAssay = (String)assays.get(a);
			tables.append("<th>").append(oneAssay).append("</th>");
		}
		tables.append("</tr>");
		//now results for each sample
		for(int b=0;b<sampleIds.size();b++){
			tables.append("<tr>");
			String sampleId = (String)sampleIds.get(b);
			tables.append("<td>").append(sampleId).append("</td>");
			Result[] oneRow = (Result[])rs.get(b);
			for(int c=0;c<oneRow.length;c++){
				Result r = oneRow[c];
				String oneR = "";
				if(r!=null){
					oneR = "<a href='editResult.htm?resultId=" + r.getResultId().toString()+ "' target='_blank'> "+ r.getResult()+"</a>";
				}
				tables.append("<td>").append(oneR).append("</td>");
			}
			
			tables.append("</tr>");
		}
		tables.append("</table></body></html>");
		ServletOutputStream sos = response.getOutputStream();
	      sos.print(tables.toString());
	      sos.flush();
		  return null;
		  ********************************/
	}
	
	public ModelAndView archiveProjectHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int projectId = RequestUtils.getIntParameter(request, "projectId", -1);
	
		List searchResults = generalManager.getResults(projectId);
		StringBuffer sbFilename = new StringBuffer();
		sbFilename.append("FileName_");
		sbFilename.append(".txt");
		
		response.setHeader("Cache-Control", "max-age=30");
		
		response.setContentType("text/plain");
		StringBuffer sbContentDispValue = new StringBuffer();
		sbContentDispValue.append("inline");
		sbContentDispValue.append("; filename=");
		sbContentDispValue.append(sbFilename);
		response.setHeader(
				"Content-disposition",
				sbContentDispValue.toString());
					
		
	
		StringBuffer sb = new StringBuffer();
		sb.append("sampleId \t assayName \t reseult \n");
		Iterator i = searchResults.iterator();
		while(i.hasNext()){
			Result r = (Result)i.next();
			String sampleId = r.getSampleId();
			String assay = r.getAssay();
			String result = r.getResult();
			sb.append(sampleId).append("\t").append(assay).append("\t").append(result).append("\n");
		}
		
		 ServletOutputStream sos = response.getOutputStream();
	      sos.print(sb.toString());
	      sos.flush();
		  return null;
	}
	
	public ModelAndView deleteProjectHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		int projectId = RequestUtils.getIntParameter(request, "projectId", -1);
	
		generalManager.removeProject(projectId);
		
		String message = "Have successfully deleted this project and its results !";
		
		Map models = new HashMap();
		
		models.put("message",message);
		
		return new ModelAndView("successComplete", models);
	}
	
	public ModelAndView deleteAssayHandler(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		int runId = RequestUtils.getIntParameter(request, "runId", -1);
		
		String[] values = request.getParameterValues("assay");
		generalManager.removeResults(runId,values);
		
		String message = "Have successfully deleted Assays and it result from this Run !";
		
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
