/*
 * Created on May 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.dao;
import agtc.servicedata.model.Result;
import agtc.servicedata.model.Run;

import java.util.*;
import java.sql.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ResultDAO {
	public List getResults(int projectId);
	public List getResultsByRunDate(int projectId,Timestamp timestamp);
	public List getResultsByRunDate(int projectId,String[] timestamp);
	public List getUniAssaysByRunDate(int projectId,Timestamp timestamp);
	public List getUniAssaysByRunDate(int projectId,String[] timestamp);
	public List getUniSamplesByRunDate(int projectId,Timestamp timestamp);
	public List getUniSamplesByRunDate(int projectId,String[] timestamp);
	public void removeResults(int projectId,Timestamp timestamp);
	public void removeResults(int projectId);
	public void removeResults(int runId, String[] assay);
	public List getUniSamples(int projectId);
	public List getUniAssays(int projectId);
	public List getUniAssaysByRunId(int runId);
	public List getUniRunDate(int projectId);
	public void loadResults(List results);
	public Result getResult(int resultId);
	public void saveResult(Result result);
}
