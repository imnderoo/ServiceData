/*
 * Created on May 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.dao;

import agtc.servicedata.model.*;
import java.util.*;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface ProjectDAO {
	public List getAllProjects();
	public Project getProject(Integer projectId);
	public Integer saveProject(Project project);
	public void removeProject(int projectId);
}
