/*
 * Created on May 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.bus;
import agtc.servicedata.dao.*;
import agtc.servicedata.model.*;
import java.sql.*;



import java.util.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GeneralManager {
	private ProjectDAO projectDAO;
	private ResultDAO resultDAO;
	private RunDAO runDAO;	
	
	
	public RunDAO getRunDAO() {
		return runDAO;
	}

	public void setRunDAO(RunDAO runDAO) {
		this.runDAO = runDAO;
	}

	public Project getProject(Integer projectId){
		return projectDAO.getProject(projectId);
	}
	
	public Integer saveProject(Project project){
		return projectDAO.saveProject(project);
		
	}
	
	public List getAllProjects(){
		return projectDAO.getAllProjects();
	}
	
	public void loadResults(List results){
		resultDAO.loadResults(results);
	}
	
	public Integer saveRun(Run r){
		return runDAO.saveRun(r);
	}

	public void saveResult(Result r){
		resultDAO.saveResult(r);
	}
	
	public List getResults(int projectId){
		return resultDAO.getResults(projectId);
	}
	
	public Result getResult(int resultId){
		return resultDAO.getResult(resultId);
	}
	
	public Run getRun(int runId){
		return runDAO.getRun(new Integer(runId));
	}
	
	public List getResultsByRunDate(int projectId,Timestamp timestamp){
		return resultDAO.getResultsByRunDate(projectId,timestamp);
	}
	
	public List getResultsByRunDate(int projectId,String[] timestamp){
		return resultDAO.getResultsByRunDate(projectId,timestamp);
	}
	
	public void removeResults(int projectId,Timestamp timestamp){
		resultDAO.removeResults(projectId,timestamp);
	}
	
	public void removeProject(int projectId){
		resultDAO.removeResults(projectId);
		projectDAO.removeProject(projectId);
	}
	
	public void removeResults(int runId,String[] assay){
		resultDAO.removeResults(runId,assay);
	}
	
	public List getUniSamples(int projectId){
		return resultDAO.getUniSamples(projectId);
	}
	
	public List getUniAssays(int projectId){
		return resultDAO.getUniAssays(projectId);
	}
	
	public List getUniAssaysByRunDate(int projectId,Timestamp timestamp){
		return resultDAO.getUniAssaysByRunDate(projectId,timestamp);
	}

	public List getUniAssaysByRunDate(int projectId,String[] timestamp){
		return resultDAO.getUniAssaysByRunDate(projectId,timestamp);
	}

	public List getUniAssaysByRunId(int runId){
		return resultDAO.getUniAssaysByRunId(runId);
	}

	public List getUniSamplesByRunDate(int projectId,Timestamp timestamp){
		return resultDAO.getUniSamplesByRunDate(projectId,timestamp);
	}
	
	public List getUniSamplesByRunDate(int projectId,String[] timestamp){
		return resultDAO.getUniSamplesByRunDate(projectId,timestamp);
	}
	
	public List getUniRunDate(int projectId){
		return runDAO.getRunsByProjectId(projectId);
	}
	
	
	
	/**
	 * @return Returns the projectDAO.
	 */
	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}
	/**
	 * @param projectDAO The projectDAO to set.
	 */
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
	
	
	/**
	 * @return Returns the resultDAO.
	 */
	public ResultDAO getResultDAO() {
		return resultDAO;
	}
	/**
	 * @param resultDAO The resultDAO to set.
	 */
	public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}
	
}
