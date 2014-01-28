/*
 * Created on May 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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
public class LoadResultsController extends ServiceDataBasicController {
	
	
	private Log log = LogFactory.getLog(LoadResultsController.class);
	private GeneralManager generalManager;
	
	
	public LoadResultsController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) 
			throws ServletException {
		return new Object();
	}
	
	protected ModelAndView onSubmit(javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response, 
			java.lang.Object command,BindException errors) 
			throws Exception {
	
		int projectId = RequestUtils.getIntParameter(request, 
							"projectId",-1);
		String note = RequestUtils.getStringParameter(request,"note","");
		java.sql.Date date = new java.sql.Date((new Date()).getTime());
		
		Run run = new Run();
		run.setNote(note);
		run.setProjectId(new Integer(projectId));
		run.setRunDate(date);
		run.setRunId(new Integer(-1));
		// the following column no is 1 based, for the convience of user, will change to 0 based one 
		// construct a ResultParse
		int sampleIdColumnNo = RequestUtils.getIntParameter(request, 
				"sampleIdColumnNo",-1);
		int assayNameColumnNo = RequestUtils.getIntParameter(request, 
				"assayNameColumnNo",-1);
		int resultColumnNo = RequestUtils.getIntParameter(request, 
				"resultColumnNo",-1);
		String MutiAssay = RequestUtils.getStringParameter(request,
				"MutiAssay","");
		
		MultipartHttpServletRequest  
				mrequest =(MultipartHttpServletRequest)request;
		
		MultipartFile aFile = mrequest.getFile("file");
		if(aFile.isEmpty()){
			errors.reject( "error.emptyFile","Uploaded file is empty");
			return showForm(request, response, errors);
		}
		//log.debug("the content type is " + aFile.getContentType());
		//log.debug("the file name is " + aFile.getOriginalFilename());
		List results = new ArrayList();
		
		InputStream is = aFile.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
		//Handle mutiple assay result
		if(MutiAssay!=null && MutiAssay.equals("yes")){
			String aLine="";
			ArrayList assayArray= new ArrayList();
			while((aLine = br.readLine()) != null) {				
				String[] st = aLine.split("\\t");
				//First line is the names of assay
				if(assayArray.isEmpty()){
					for(int i=1;i<st.length;i++) {//ignore first column
						String temp=st[i].trim().toUpperCase();
						assayArray.add(temp);
					}
					continue;
				}
				//read sample id
				String sampleId=st[0].trim().toUpperCase();
				//read result
				for(int i=1;i<st.length;i++){
					String result=st[i].trim().toUpperCase();
					if(result==null || 
							result.length() == 0 ||
							result.equals("0")){
						result = "00";
					}
					Result r = new Result();
					
					r.setSampleId(sampleId);
					r.setAssay((String)assayArray.get(i-1));
					r.setResult(result);
					//r.setProjectId(new Integer(projectId));
					//r.setRunDate(date);
					r.setNote(note);
					results.add(r);
				}	
			}
		}
		else {
			br.readLine();	//ignore the first line
			String aLine = "";
			//handle normal one line result
			while((aLine = br.readLine()) != null) {
			
				String[] st = aLine.split("\\t");
		   
				/*String sampleId = st[sampleIdColumnNo-1].trim();
				sampleId = sampleId.replaceAll(" ","");
				String assay = st[assayNameColumnNo-1].trim();
				assay = assay.toUpperCase();
				String result = st[resultColumnNo-1].trim();
				result = result.replaceAll("\\.","");
				result = result.replaceAll("/","");
				result = result.replaceAll(" ","");
				result = result.toUpperCase();*/
				String sampleId=TydyHolder(st[sampleIdColumnNo-1]);
				String assay=TydyHolder(st[assayNameColumnNo-1]);
				String result=TydyHolder(st[resultColumnNo-1]);
				if(result.length() == 1){
					result = result+result;
				}
				if(result.indexOf("NO")>=0){
					result = "00";
				}
				if(result==null || result.length() == 0){
					result = "00";
				}
				if(validSampleId(sampleId)){
					Result r = new Result();
					
					r.setSampleId(sampleId);
					r.setAssay(assay);
					r.setResult(result);
					//r.setProjectId(new Integer(projectId));
					//r.setRunDate(date);
					r.setNote(note);
					results.add(r);
				}
		    
			}
		}
		is.close();
		
	   String message = "";
		try{
			Integer runid = generalManager.saveRun(run);
			for(int i=0;i<results.size();i++){
				((Result) results.get(i)).setRunId(runid);
			}
			generalManager.loadResults(results);
	   		message = " Have successfully loaded the results! <br>";
	   		return new ModelAndView("successComplete","message",message);
	   }catch(Exception e){
		   	message = " Could not load the results! <br>";
		   	message += e.getMessage();
		   	e.printStackTrace();

	   		return new ModelAndView("errorMessage","message",message);
		}
		  
		
	}
	
	protected java.util.Map referenceData(
			  	javax.servlet.http.HttpServletRequest request,
			  	java.lang.Object command,Errors errors)
	   			throws java.lang.Exception
	{
		Map models = new HashMap();
		
		List allProjects = generalManager.getAllProjects();
		
		models.put("projectList",allProjects);
		
		List allColumnNumbers = new ArrayList();
		for(int i = 1; i<21;i++){
			allColumnNumbers.add(new Integer(i));
		}
		models.put("allColumnNumbers",allColumnNumbers);
	
		
		return models;
	}
	

	
	public GeneralManager getGeneralManager() {
		return generalManager;
	}
	
	public void setGeneralManager(GeneralManager generalManager) {
		this.generalManager = generalManager;
	}

	public static String TydyHolder(String strIntended)
	{
		String holder=strIntended;
		holder=holder.replaceAll(" "," ");
		holder=holder.replaceAll("\\.","");
		holder=holder.replaceAll("/","");
		holder=holder.trim();
		holder=holder.toUpperCase();
		return holder;
	}
	
	private boolean validSampleId(String s){
		if(s.equalsIgnoreCase("BLK")){
			return false;
		}else if(s.equalsIgnoreCase("blank")){
			return false;
		}else if(s.equalsIgnoreCase("water")){
			return false;
		}else if(s.equalsIgnoreCase("unknown")){
			return false;
		}else if(s.equalsIgnoreCase("*")){
			return false;
		}else if(s.equalsIgnoreCase("control")){
			return false;
		}else if(s.equalsIgnoreCase("c")){
			return false;
		}else if(s.equalsIgnoreCase("empty")){
			return false;
		}else if(s.length()== 0){
			return false;
		}else {
			return true;
		}
	}
}
