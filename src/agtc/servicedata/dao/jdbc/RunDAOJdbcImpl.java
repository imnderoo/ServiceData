package agtc.servicedata.dao.jdbc;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.OracleSequenceMaxValueIncrementer;
import agtc.servicedata.dao.RunDAO;
import agtc.servicedata.model.Run;

public class RunDAOJdbcImpl extends JdbcDaoSupport implements RunDAO {
	private Log	log	= LogFactory.getLog(RunDAOJdbcImpl.class);

	public List getAllRuns() {
		return getJdbcTemplate().query("select RUN_ID,PROJECT_ID,RUN_DATE,NOTE from RUN", new QueryRunHandler());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see agtc.servicedata.dao.RunDAO#getRun(java.lang.Integer)
	 */
	public Run getRun(Integer RunId) {
		return (Run) getJdbcTemplate().query(
				"select RUN_ID,PROJECT_ID,RUN_DATE,NOTE from RUN where RUN_ID=" + RunId.toString(),
				new QueryRunHandler()).get(0);

	}

	public List getRunsByProjectId(int projectid) {
		return getJdbcTemplate().query("select RUN_ID,PROJECT_ID,RUN_DATE,NOTE from RUN where PROJECT_ID=" + projectid,
				new QueryRunHandler());
	}

	public class QueryRunHandler implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			int runid = rs.getInt("RUN_ID");
			int projectid = rs.getInt("PROJECT_ID");
			// This is the way to keep the time of "java.sql.Date"
			// If use rs.getDate will lost time
			Date rundate = new Date(rs.getTimestamp("RUN_DATE").getTime());
			String note = rs.getString("NOTE");
			return new Run(new Integer(runid), new Integer(projectid), rundate, note);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see agtc.servicedata.dao.RunDAO#saveRun(agtc.servicedata.model.Run)
	 */
	public Integer saveRun(Run run) {
		// log.debug("the Run is " + Run.getName());
		int RunId = run.getRunId().intValue();
		Date runDate = run.getRunDate();
		// SimpleDateFormat formatter = new
		// SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String note = run.getNote();

		System.out.println(note);

		String sql = "";
		Object[] param = null;
		Integer result = null;
		if (RunId == -1) {
			sql = "insert into RUN(project_id,RUN_DATE,note)" + " values (?,STR_TO_DATE('" + run.getDate()
					+ "','%Y-%m-%d %H:%i:%s'),?)";

			param = new Object[] { run.getProjectId(), note };
			getJdbcTemplate().update(sql, param);

			result = getMaxRunId();
		} else {
			sql = " update RUN set project_id = ?,RUN_DATE=STR_TO_DATE('" + run.getDate()
					+ "','%Y-%m-%d %H:%i:%s'), note = ? where run_id = ?";
			param = new Object[] { run.getProjectId(), note, new Integer(RunId) };
			System.out.println(param);
			getJdbcTemplate().update(sql, param);
			result = new Integer(RunId);
		}
		// System.out.println(sql);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see agtc.servicedata.dao.RunDAO#removeRun(java.lang.Integer)
	 */
	public void removeRun(int RunId) {
		String sql = "delete from RUN where run_id=" + RunId;
		getJdbcTemplate().update(sql);
	}

	private Integer getMaxRunId() {
		return new Integer(getJdbcTemplate().queryForInt("select max(run_id)from RUN"));
	}

}
