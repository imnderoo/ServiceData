package agtc.servicedata.dao;

import java.util.List;

import agtc.servicedata.model.*;

public interface RunDAO {

	public List getAllRuns();
	public Run getRun(Integer runId);
	public Integer saveRun(Run run);
	public void removeRun(int runId);
	public List getRunsByProjectId(int projectid);
}
