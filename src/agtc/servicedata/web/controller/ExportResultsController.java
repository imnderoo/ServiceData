/*
 * Created on May 16, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.web.controller;

import agtc.servicedata.model.*;
import agtc.servicedata.bus.*;
import javax.servlet.*;
import javax.servlet.http.*;
import agtc.util.stat.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

import java.sql.Timestamp;
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
public class ExportResultsController extends ServiceDataBasicController {
	
	private Log log = LogFactory.getLog(ExportResultsController.class);
	private GeneralManager generalManager;
	
	

	public ExportResultsController(){
		//initialize the form from the formBackingObject
		 setBindOnNewForm(true);
		
	}
	
	protected Object formBackingObject(HttpServletRequest request) throws ServletException {
		return new Object();
	}
	
	protected ModelAndView onSubmit(
			javax.servlet.http.HttpServletRequest request, 
			javax.servlet.http.HttpServletResponse response, 
			java.lang.Object command,BindException errors) 
			throws Exception {
		
		int projectId = RequestUtils.getIntParameter(request, "projectId", -1);
		String format = 
			RequestUtils.getStringParameter(request, "linkageFormat","");
		
		MultipartHttpServletRequest  mrequest = 
			(MultipartHttpServletRequest)request;
		MultipartFile sampleFile = mrequest.getFile("file");
		
		List sampleIds = null;
		List searchResults = null;
		List assays = null;
		if(!sampleFile.isEmpty()){
			//Get sample ids from file system
			sampleIds = new ArrayList();
			InputStream is = sampleFile.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String aLine = "";
			while((aLine = br.readLine()) != null) {
				
			    String sampleUserId = aLine.trim();
			    sampleIds.add(sampleUserId);
			}
			is.close();

			String rd[] = 
				RequestUtils.getStringParameters(request,"SelectedRD");
			if(rd==null || rd.length<1)//If not selected Run date, get all result of this project
			{	 
				searchResults = generalManager.getResults(projectId);
				assays = generalManager.getUniAssays(projectId);
			} else {
				//Get sample Ids by project Ids and Run dates (Load date time)
				searchResults = generalManager.getResultsByRunDate(projectId,rd);
				assays = generalManager.getUniAssaysByRunDate(projectId,rd);
			}
 		
		}else{
			String rd[] = 
				RequestUtils.getStringParameters(request,"SelectedRD");
			if(rd==null || rd.length<1)//If not selected Run date, get all result of this project
			{	sampleIds = generalManager.getUniSamples(projectId);
				searchResults = generalManager.getResults(projectId);
				assays = generalManager.getUniAssays(projectId);
			} else {
				//Get sample Ids by project Ids and Run dates (Load date time)
				sampleIds = generalManager.getUniSamplesByRunDate(projectId,rd);
				searchResults = generalManager.getResultsByRunDate(projectId,rd);
				assays = generalManager.getUniAssaysByRunDate(projectId,rd);
			}	
		}
		
		ResultFormatter rm = 
			new ResultFormatter(sampleIds,assays,searchResults,format,true);
		List rs = rm.formatResults();
		sampleIds = rm.getFormattedSampleIds();
		AssayStator[] assayStatResults = rm.getAssayStat();
		//log.debug("the sampleId size is " + sampleIds.size());
		if(searchResults.size()>1000000){
			String message = "The amount of retrieved genotype results"
				+"is too large to be handled, please give more"
				+"specified search criteria ! <br>";
			return new ModelAndView("errorPage","message",message);
		}

		StringBuffer sbFilename = new StringBuffer();
		sbFilename.append("ResultOf"+projectId);
		sbFilename.append(".xls");
		
		//Set response Header
		response.setHeader("Cache-Control", "max-age=30");
		response.setContentType("application/xls");
		StringBuffer sbContentDispValue = new StringBuffer();
		sbContentDispValue.append("inline");
		sbContentDispValue.append("; filename=");
		sbContentDispValue.append(sbFilename);
		response.setHeader(
				"Content-disposition",
				sbContentDispValue.toString());
					
		
		//Write data into file
		ServletOutputStream sos = response.getOutputStream();

		StringBuffer sb = new StringBuffer();
		sos.print(" \t"); // \t is TAB 
		for(int i=0;i<assays.size();i++){
			sos.print((String)assays.get(i)+"\t");
		}
		sos.print("\r\n"); //end of line
		
		// append the obsHet for each assay
		sos.print("ObsHet\t");
		AssayStatResult statResult = null;
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			if(statResult.isNoResult()){
				s = "N/A";
				
			}else{
				s = Double2String(statResult.getObsHet());
			}
			sos.print(s+"\t");
		}
		sos.print("\r\n"); //end of line
		
		//		 append the predicted Het for each assay
		sos.print("PredHet\t");
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			if(statResult.isNoResult()){
				s = "N/A";
				
			}else{
				s = Double2String(statResult.getPreHet());
			}
			sos.print(s+"\t");
		}
		sos.print("\r\n"); //end of line
		
		//		 append the HWPval for each assay
		sos.print("HWPval\t");
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			if(statResult.isNoResult()){
				s = "N/A";
				
			}else{
				s = Double2String(statResult.getHwPValue());
			}
			sos.print(s+"\t");
		}
		sos.print("\r\n");
		
		//		 append the Geno for each assay
		sos.print("%Geno\t");
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			s = Double2String(statResult.getGenoPct());		
			sos.print(s+"\t");
		}
		sos.print("\r\n");
		
		//		 append the MAF for each assay
		sos.print("MAF\t");
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			if(statResult.isNoResult()){
				s = "N/A";				
			}else{
				s = Double2String(statResult.getMaf());
			}
			sos.print(s+"\t");
		}
		sos.print("\r\n");
		
		//How to reduce memroy used here?
		for(int b=0;b<rs.size();b++){
			//first column is sampleId
			sos.print((String)sampleIds.get(b)+"\t");
			Result[] oneRow = (Result[])rs.get(b);
			//System.out.println("Row number is"+b);
			for(int c=0;c<oneRow.length;c++){
				Result r = oneRow[c];
				String s = null;
				if(r!=null){
					s=r.getResult();
				}else{
					if(format.equals("yes")){
						s="0 0";
					}else{
						s="0";
					}
				}
				sos.print(s+"\t");
			} 
			sos.print("\r\n");
		}
		
      sos.close();
	  return null;
	
/*********************************************************
 * Excel with POI, have limitation on memory heap
 * 
 * 
 * 		//Write data into file
//		ServletOutputStream sos = response.getOutputStream();

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet	sheet = wb.createSheet(""+projectId);
		int rowNumber = 0;
		StringBuffer sb = new StringBuffer();
		
		//the title, first row is assay name, first column is sampleId
		HSSFRow row = sheet.createRow(++rowNumber);
		row.createCell((short)0).setCellValue(" ");
		for(int i=0;i<assays.size();i++){
			row.createCell((short)(i+1)).
				setCellValue((String)assays.get(i));
		}
		
		// append the obsHet for each assay
		row = sheet.createRow(++rowNumber);
		row.createCell((short)0).setCellValue("ObsHet");
		AssayStatResult statResult = null;
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			if(statResult.isNoResult()){
				s = "N/A";
				
			}else{
				s = Double2String(statResult.getObsHet());
			}
			row.createCell((short)(i+1)).setCellValue(s);
		}
		
		//		 append the predicted Het for each assay
		row = sheet.createRow(++rowNumber);
		row.createCell((short)0).setCellValue("PredHet");
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			if(statResult.isNoResult()){
				s = "N/A";
				
			}else{
				s = Double2String(statResult.getPreHet());
			}
			row.createCell((short)(i+1)).setCellValue(s);
		}
		
//		 append the HWPval for each assay
		row = sheet.createRow(++rowNumber);
		row.createCell((short)0).setCellValue("HWPval");
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			if(statResult.isNoResult()){
				s = "N/A";
				
			}else{
				s = Double2String(statResult.getHwPValue());
			}
			row.createCell((short)(i+1)).setCellValue(s);
		}
		
//		 append the Geno for each assay
		row = sheet.createRow(++rowNumber);
		row.createCell((short)0).setCellValue("%Geno");
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			s = Double2String(statResult.getGenoPct());		
			row.createCell((short)(i+1)).setCellValue(s);
		}
		
//		 append the MAF for each assay
		row = sheet.createRow(++rowNumber);
		row.createCell((short)0).setCellValue("MAF");
		for(int i=0;i<assayStatResults.length;i++){
			String s = "";
			statResult =  assayStatResults[i].getResult();
			if(statResult.isNoResult()){
				s = "N/A";				
			}else{
				s = Double2String(statResult.getMaf());
			}
			row.createCell((short)(i+1)).setCellValue(s);
		}

		//How to reduce memroy used here?
		for(int b=0;b<rs.size();b++){
			//first column is sampleId
			row = sheet.createRow(++rowNumber);
			row.createCell((short)0).
				setCellValue((String)sampleIds.get(b));
			Result[] oneRow = (Result[])rs.get(b);
			//System.out.println("Row number is"+b);
			for(int c=0;c<oneRow.length;c++){
				Result r = oneRow[c];
				String s = null;
				if(r!=null){
					s=r.getResult();
				}else{
					if(format.equals("yes")){
						s="0 0";
					}else{
						s="";
					}
				}
				row.createCell((short)(c+1)).setCellValue(s);
			} 
		}
		
			
	  ServletOutputStream sos = response.getOutputStream();
      //sos.print(sb.toString());
	  wb.write(sos);
	
      sos.flush();
 */	  
	}
	
	//Keep 3 digital after "."
	private static String Double2String(double d){
		String s=Double.toString(d+0.0005);
		
		if (Math.abs(d)<0.001) return s;
		
		System.out.println(s);
		
		int i = s.indexOf('.');
		if (i<1) return s;

		int l = s.length();
		if ((i+4)<l) l=i+4;
		s = s.substring(0,l);
		
		return s;
	}
	
	
	
	
	protected java.util.Map referenceData(javax.servlet.http.HttpServletRequest request,
			  java.lang.Object command,Errors errors)
	   throws java.lang.Exception
	{
		Map models = new HashMap();
		
		//List allProjects = generalManager.getAllProjects();
		//Project p=(Project) WebUtils.getSessionAttribute(request,
			//	"SelectedProject");
		int projectId = RequestUtils.getIntParameter(request, "projectId", -1);
		List uniRunDates = 
			generalManager.getUniRunDate(projectId);
		models.put("uniRunDates",uniRunDates);
		//models.put("projectList",allProjects);
		
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
