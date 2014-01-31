/*
 * Created on May 30, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.incrementer.*;

import agtc.servicedata.dao.ProjectDAO;
import agtc.servicedata.model.Project;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ProjectDAOJdbcImpl extends JdbcDaoSupport implements ProjectDAO {
	private Log log = LogFactory.getLog(ProjectDAOJdbcImpl.class);
	
	public List getAllProjects() {
		return getJdbcTemplate().query("select project_id,name,note from PROJECT order by name",new QueryProjectHandler());
	}

	/* (non-Javadoc)
	 * @see agtc.servicedata.dao.ProjectDAO#getProject(java.lang.Integer)
	 */
	public Project getProject(Integer projectId) {
		return (Project)getJdbcTemplate().query("select project_id,name,note from PROJECT where project_id="+projectId.toString(),new QueryProjectHandler()).get(0);
		
	}
	
	public class QueryProjectHandler implements RowMapper{
		public Object mapRow(ResultSet rs,int rowNum) throws SQLException{
			int id = rs.getInt("project_id");
			String name = rs.getString("name");
			String note = rs.getString("note");
			
			return new Project(new Integer(id),name,note);
		}
	}

	/* (non-Javadoc)
	 * @see agtc.servicedata.dao.ProjectDAO#saveProject(agtc.servicedata.model.Project)
	 */
	public Integer saveProject(Project project) {
		//log.debug("the project is " + project.getName());
		int projectId = project.getProjectId().intValue();
		String name = project.getName();
		String note = project.getNote();
		String sql = "";
		Object[] param = null;
		Integer result = null;
		if (note.equals(null)) note=" ";
		if(projectId == -1){
			sql = "insert into PROJECT(name,note) values (?,?)";
			param = new Object[]{name,note};
			getJdbcTemplate().update(sql,param);

			result = getMaxProjectId();
		}else{
			sql = " update PROJECT set name = ?, note = ? where project_id = ?";
			param = new Object[]{name,note,new Integer(projectId)};			
			getJdbcTemplate().update(sql,param);

			result = new Integer(projectId);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see agtc.servicedata.dao.ProjectDAO#removeProject(java.lang.Integer)
	 */
	public void removeProject(int projectId) {
		String sql = "delete from PROJECT where project_id="+projectId;
		getJdbcTemplate().update(sql);

	}
		
	private Integer getMaxProjectId() {
		return new Integer(getJdbcTemplate().queryForInt("select max(project_id)from PROJECT"));
	}	

}
