/*
 * Created on May 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.dao.jdbc;

import agtc.servicedata.dao.ResultDAO;
import agtc.servicedata.model.Result;
import agtc.servicedata.model.Run;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.core.*;
import java.util.*;
import java.sql.*;
 

public class ResultDAOJdbcImpl  extends JdbcDaoSupport implements ResultDAO{
	public List getResults(int projectId){
		return getJdbcTemplate().query(
				"select result_id,sample_id,assay,result as genoType "
				+" from RESULT,RUN where RESULT.run_id=RUN.run_id and RUN.project_id="
				+projectId+" order by sample_id,assay",new QueryResultHandler());
	}
	
	public Result getResult(int resultId){
		return (Result)getJdbcTemplate().query(
				"select result_id,sample_id,assay,result as genoType"
				+" from RESULT where result_id="
				+resultId,new QueryResultHandler()).get(0);
	}
	
	public void saveResult(Result result){
		String sql = " update RESULT set result = ? where result_id = ?";
		String res = result.getResult();
		//String note = result.getNote();
		Integer resultId = result.getResultId();
		Object[] param = new Object[]{res,resultId};
		getJdbcTemplate().update(sql,param);
	}
	
	public List getResultsByRunDate(int projectId,Timestamp timestamp){
		String sql = "select result_id,sample_id,assay,result as genoType"
			+" from RESULT,RUN where RESULT.run_id=RUN.run_id and RUN.project_id="
			+projectId+" and RUN.RUN_DATE=? order by sample_id,assay";
		PreparedStatementSetter pss = new GetByRunDatePSSetter(timestamp);
		return getJdbcTemplate().query(sql,pss,new QueryResultHandler());
	}

	public List getResultsByRunDate(int projectId,String[] timestamp){
		String sql = "select result_id,sample_id,assay,result as genoType"
			+" from RESULT,RUN where RESULT.run_id=RUN.run_id and RUN.project_id="
			+projectId+" and RUN.RUN_DATE in "
			+ArrayToString(timestamp)+ " order by sample_id,assay";
		//PreparedStatementSetter pss = new GetByRunDatePSSetter(timestamp);
		return getJdbcTemplate().query(sql,new QueryResultHandler());
	}

	public List getUniSamplesByRunDate(int projectId,Timestamp timestamp){
		String sql = "select distinct RESULT.sample_id from RESULT,RUN"
			+" where RESULT.run_id=RUN.run_id and RUN.project_id="
			+projectId+" and RUN.RUN_DATE=? order by sample_id";
		
		return getJdbcTemplate().queryForList(sql,new Object[]{timestamp},String.class);
	}
	
	public static String ArrayToString(String [] ss){
		String s=" ( ";
		for (int i=0;i<ss.length;i++){
			if(i>0) s+=",";
			s+= " timestamp'"+ss[i]+"' ";
		}
		s+=") ";
		return s;
	}
	public List getUniSamplesByRunDate(int projectId,String[] timestamp){
		String sql = "select distinct sample_id from RESULT,RUN "
			+" where RESULT.run_id=RUN.run_id and RUN.project_id="
			+projectId+" and RUN.RUN_DATE in"
			+ArrayToString(timestamp)+" order by sample_id";
		
		return getJdbcTemplate().queryForList(sql,String.class);
	}

	public List getUniAssaysByRunDate(int projectId,Timestamp timestamp){
		String sql = "select distinct assay from RESULT,RUN "
			+" where RESULT.run_id=RUN.run_id and RUN.project_id="
			+projectId+" and RUN.RUN_DATE=?";
		
		return getJdbcTemplate().queryForList(sql,new Object[]{timestamp},String.class);
	}

	public List getUniAssaysByRunDate(int projectId,String[] timestamp){
		String sql = "select distinct assay from RESULT,RUN "
			+" where RESULT.run_id=RUN.run_id and RUN.project_id="
			+projectId+" and RUN.RUN_DATE in "+ArrayToString(timestamp);
		
		return getJdbcTemplate().queryForList(sql,String.class);
	}

	public List getUniAssaysByRunId(int runId){
		String sql = "select distinct assay from RESULT "
			+" where run_id="+runId;
		
		return getJdbcTemplate().queryForList(sql,String.class);
	}

	public List getUniSamples(int projectId){
		return getJdbcTemplate().queryForList("select distinct sample_id from RESULT,RUN"
				+" where RESULT.run_id=RUN.run_id and RUN.project_id="
				+projectId+" order by sample_id",String.class);
	}
	public List getUniAssays(int projectId){
		return getJdbcTemplate().queryForList("select distinct assay from RESULT,RUN "
				+" where RESULT.run_id=RUN.run_id and RUN.project_id="
				+projectId,String.class);
	}
	public List getUniRunDate(int projectId){
		return getJdbcTemplate().queryForList("select RUN_DATE from RUN where project_id="
				+projectId,java.sql.Timestamp.class);
	}
	
	public void loadResults(List results){
		//insert "RUN" first
		
		String sql = "insert into RESULT(sample_id,assay,result,run_id)"
			+" values (?,?,?,?)" ;
	
		int maxResultId = getMaxResultId().intValue();
		
		BatchPreparedStatementSetter setter = new MyBatchPreparedStatementSetter(results);
		
		getJdbcTemplate().batchUpdate(sql,setter);
	}
	
	public void removeResults(int projectId,Timestamp timestamp){
		String sql = "delete from RESULT where run_id in"
					+"(select run_id from RUN where project_id="
					+projectId+" and RUN_DATE=?)";
		getJdbcTemplate().update(sql,new Object[]{timestamp});
		sql = " delete from RUN where project_id="+projectId+" and RUN_DATE=?";
		getJdbcTemplate().update(sql,new Object[]{timestamp});
	}
	
	public void removeResults(int projectId){
		String sql = "delete from RESULT where run_id in "
						+"( select run_id from RUN where project_id="
						+projectId+")";
		getJdbcTemplate().update(sql);
		sql ="delete from RUN where project_id="+projectId;
		getJdbcTemplate().update(sql);
	}
	
	public void removeResults(int runId, String[] assay){
		String sql = "delete from RESULT where run_id = "+runId
						+" and assay in (";
		for ( int i=0;i< assay.length;i++){
			if (i==0) 
				sql +="'" +assay[i]+"'";
			else
				sql += ",'"+assay[i]+"'";
		}
		sql+=")";
		getJdbcTemplate().update(sql);
	}

	public Integer getMaxResultId() {
		return new Integer(getJdbcTemplate().queryForInt("select max(result_id)from RESULT"));
	}	
	
	public class QueryResultHandler implements RowMapper{
		public Object mapRow(ResultSet rs,int rowNum) throws SQLException{
			String sampleId = rs.getString("sample_id");
			String assay = rs.getString("assay");
			String result = rs.getString("genoType");
			//String note = rs.getString("note");
			int resultId = rs.getInt("result_id");
			Result r = new Result();
			r.setResultId(new Integer(resultId));
			r.setSampleId(sampleId);
			r.setAssay(assay);
			r.setResult(result);
			//r.setNote(note);
			return r;
		}
	}
	
	public class GetByRunDatePSSetter implements PreparedStatementSetter {
		private Timestamp timestamp;
		public GetByRunDatePSSetter(){
			
		}
		public GetByRunDatePSSetter(Timestamp pTimestamp){
			timestamp = pTimestamp;
		}
		public void setValues(java.sql.PreparedStatement ps)
        throws java.sql.SQLException{
			ps.setTimestamp(1,timestamp);
		}
	}
	
	public class MyBatchPreparedStatementSetter implements BatchPreparedStatementSetter{
		private List results;
		private int maxResultId;
//		private Log log = LogFactory.getLog(MyBatchPreparedStatementSetter.class);
		//private java.sql.Timestamp timeStamp;
		
		public int getBatchSize(){
			return results.size();
		}
		
		public MyBatchPreparedStatementSetter(){
			
		}
		
		public MyBatchPreparedStatementSetter(List l){
			results = l;
			//timeStamp = new Timestamp(System.currentTimeMillis());
			
		}
		
		public void setValues(PreparedStatement ps,int index) throws SQLException{
			
			Result  r = (Result)results.get(index);
			//log.debug(index + "  "+r.getCompanySnpId());
			//ps.setInt(1,maxResultId+index);
			ps.setString(1,r.getSampleId());
			ps.setString(2,r.getAssay());
			ps.setString(3,r.getResult());
			ps.setInt(4,r.getRunId().intValue());
			//ps.setTimestamp(6,timeStamp);
		}
		
	}
/*	
	public class ResultPreparedStatementSetter implements PreparedStatementSetter{
		private Result result;
		private int resultId;
		
		private java.sql.Timestamp timeStamp;
		
		
		public ResultPreparedStatementSetter(){
			
		}
		
		public ResultPreparedStatementSetter(Result r,int i){
			result = r;
			resultId = i;
			timeStamp = new Timestamp(System.currentTimeMillis());
			
		}
		
		public void setValues(PreparedStatement ps) throws SQLException{
			
			
			ps.setInt(1,resultId);
			ps.setString(2,result.getSampleId());
			ps.setString(3,result.getAssay());
			ps.setString(4,result.getResult());
			//ps.setInt(5,result.getProjectId().intValue());
			ps.setTimestamp(6,timeStamp);
		}
		
	}
	*/
}
