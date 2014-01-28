/*
 * Created on May 31, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.servicedata.bus;
import java.util.*;
import agtc.servicedata.model.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import agtc.util.stat.*;
/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResultFormatter {
	private List sampleIds;
	private List assays;
	private List searchResults;
	private String format;
	private boolean doStat;
	private AssayStator[] assayStats;
	public static int GENOTYPE_A = 1;
	public static int GENOTYPE_C = 2;
	public static int GENOTYPE_G = 3;
	public static int GENOTYPE_T = 4;
	public static int GENOTYPE_NO = 0;
	private Log log = LogFactory.getLog(ResultFormatter.class);
	public ResultFormatter(List pSampleIds,List pAssays,
			List pSearchResults,String pFormat){
		sampleIds = pSampleIds;
		assays = pAssays;
		searchResults = pSearchResults;
		format = pFormat;
	}
	public ResultFormatter(List pSampleIds,List pAssays,
			List pSearchResults,String pFormat,boolean pDoStat){
		sampleIds = pSampleIds;
		assays = pAssays;
		searchResults = pSearchResults;
		format = pFormat;
		doStat = pDoStat;
		assayStats = new AssayStator[assays.size()];
		for(int i=0;i<assays.size();i++){
			assayStats[i] = new AssayStator();
		}
		
	}
	// order the results, the searchResults set the the caller is in order of sampleId, asay
	public List formatResults(){
		List results = new ArrayList();
		Iterator i = searchResults.iterator();
		String lastSampleId = "";
		String preSampleId = "";
		String preAssay = "";
		List oneSampleRows = new ArrayList();
		int dupResult = 0;
		boolean newSample = false;
		while(i.hasNext()){
			Result r = (Result)i.next();
			String sampleId = r.getSampleId();
			String assay = r.getAssay();
			String result = r.getResult();
			//log.debug(" sampleId is " + sampleId + " assay is " + assay);
			
			if(!preSampleId.equals(sampleId)){
				for(int a=0;a<oneSampleRows.size();a++){
					Result[] oneRow = (Result[])oneSampleRows.get(a);
					results.add(oneRow);
				}
				oneSampleRows = new ArrayList();
				Result[] oneRow = new Result[assays.size()];
				oneSampleRows.add(oneRow);
				preSampleId = sampleId;
				newSample = true;
				//log.debug("sample id changed, now the preSampleId is " + preSampleId);
			}else{
				newSample = false;
			}
			
				
			int columnNo = assays.indexOf(assay);
			
			if(doStat){
				assayStats[columnNo].addOneGenoType(result);
			}
			
			if(format.equals("yes")){
				r.setResult(haploviewFormatResult(result));
			}
			
			
			// not a dup result
			if(!preAssay.equals(assay) || newSample){
				dupResult = 0;
				preAssay = assay;
				
			}else{
				dupResult++;
				if(oneSampleRows.size()<= dupResult){
					Result[] oneRow = new Result[assays.size()];
					oneSampleRows.add(oneRow);
					int sampleRowNo = sampleIds.indexOf(sampleId);
					sampleIds.add(sampleRowNo+1,sampleId);
				}
			}
			//log.debug("the dupResult is "+ dupResult);
			Result[] oneRow = (Result[])oneSampleRows.get(dupResult);
			oneRow[columnNo] = r;
			
		
		}
		for(int a=0;a<oneSampleRows.size();a++){
			Result[] oneRow = (Result[])oneSampleRows.get(a);
			results.add(oneRow);
		}
		return results;
	}
	
	public List getFormattedSampleIds(){
		return sampleIds;
	}
	
	public  AssayStator[] getAssayStat(){
		return assayStats;
	}
	
	private int convert2LinkageFormat(String s){
		if(s.compareTo("T") == 0){
			return GENOTYPE_T;
		}else if(s.compareTo("A") == 0){
			return GENOTYPE_A;
		}else if(s.compareTo("C") == 0){
			return GENOTYPE_C;
		}else if(s.compareTo("G") == 0){
			return GENOTYPE_G;
		}else{
			return GENOTYPE_NO;
		}
	}
	
	private String haploviewFormatResult(String originalResult){
		
		String firstS = originalResult.substring(0,1);
		String secondS = originalResult.substring(1);
		
		int first = convert2LinkageFormat(firstS);
		int second = convert2LinkageFormat(secondS);
		if(second < first){
			return (second + " " + first);
		}else{
			return (first + " " + second);
		}
	}
}
