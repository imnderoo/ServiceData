/*
 * Created on Jun 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.util.stat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Hongjing
 *
 *to do statistical calculation for a assay
 */
public class AssayStator {
	// the het,hom, and missing genotype
	private int het;
	private int hom;
	private  int missing;
	// the number of allele of each A,G,T,C,0
	private int[] alleleCount;
	private int[] homCount;
	public static int GENOTYPE_A = 1;
	public static int GENOTYPE_C = 2;
	public static int GENOTYPE_G = 3;
	public static int GENOTYPE_T = 4;
	public static int GENOTYPE_NO = 0;
	private AssayStatResult result = null;
	private Log log = LogFactory.getLog(AssayStator.class);
	
	// just calculate two allele type, 
	private boolean isCalculable = true;
	
	public AssayStator (){
		alleleCount = new int[5];
		homCount = new int[5];
		for(int i=0;i<5;i++){
			alleleCount[i] = 0;
			homCount[i] = 0;
		}
		
	}
	
	public void addOneGenoType(String genoType){
		//log.debug("genotype is " + genoType);
		if(genoType.length()>2){
			isCalculable = false;
		}
		String allele1 = genoType.substring(0,1);
		String allele2 = genoType.substring(1);
		//log.debug("allele1 is " + allele1);
		//log.debug("allele2 is " + allele2);
		
		
		if(genoType.equals("00")){
			missing++;
		}else{
			int a1 = convert2No(allele1);
			int a2 = convert2No(allele2);
			if(allele1.equals(allele2)){
				hom++;
				homCount[a1]++;
			}else{
				het++;
			}
			
			alleleCount[a1]++;
			alleleCount[a2]++;
		}
	}
	
	
	
	/**
	 * @return Returns the result.
	 */
	public AssayStatResult getResult() throws NotCalculableException {
		if(result == null){
			result = new AssayStatResult();
			try{
				doStat();
			}catch(Exception e){
				log.debug(e.getMessage());
				result.setNoResult(true);
			}
		}
		return result;
	}
	/**
	 * @param result The result to set.
	 */
	public void setResult(AssayStatResult result) {
		this.result = result;
	}
	private void doStat() throws NotCalculableException{
		result.setGenoPct(getGenoPercent());
		if(!isCalculable){
			throw new NotCalculableException();
		}
		result.setObsHet(getObsHET());
		//log.debug("het is " + het + " hom is " + hom);
		int sumsq=0, sum=0, num=0, mincount = -1;
        int numberOfAlleles = 0;
        int obsAA = 0;
        int obsBB = 0;
        double preHet = 0;
        double maf = 0;
        double pValue = 0;
        for(int i=0;i<5;i++){
            //log.debug("i is " + i + " and this allele no is" + alleleCount[i]);
        	if(alleleCount[i] != 0){
                numberOfAlleles++;
                num = alleleCount[i];
                sumsq += num*num;
                sum += num;
                if (mincount < 0 || mincount > num){
                    mincount = num;
                }
             if(homCount[i] !=0){
                if(obsAA>0) obsBB = homCount[i];
                else obsAA = homCount[i];
             }
            }
        }

        if (numberOfAlleles > 2){
            throw new NotCalculableException("More than two alleles!");
        }

        if (sum == 0){
        	preHet = 0;
        	maf = 0;
        }else{
        	preHet = 1.0 - (sumsq/((sum*sum)+0.0));
            if (mincount/(sum+0.0) == 1){
            	maf = 0.0;
            }else{
            	maf = mincount/(sum+0.0);
            }
        }
        
     
        if (obsAA + het + obsBB <= 0){
            pValue=0;
        }else{
            pValue = hwCalculate(obsAA, het, obsBB);
        }
        
        result.setHwPValue(pValue);
        result.setMaf(maf);
        
        result.setPreHet(preHet);
     	
	}
	private double getObsHET(){
      
		double obsHET;
        if (het+hom == 0){
            obsHET = 0;
        }else{
            obsHET = het/(het+hom+0.0);
        }
        return obsHET;
        
    }
	
	private double getGenoPercent(){
        if (het+hom+missing == 0){
            return 0;
        }else{
            return 100.0*(het+hom)/(het+hom+missing);
        }
    }
	
	private double hwCalculate(int obsAA, int obsAB, int obsBB) throws NotCalculableException{
        //Calculates exact two-sided hardy-weinberg p-value. Parameters
        //are number of genotypes, number of rare alleles observed and
        //number of heterozygotes observed.
        //
        // (c) 2003 Jan Wigginton, Goncalo Abecasis
        int diplotypes =  obsAA + obsAB + obsBB;
        int rare = (obsAA*2) + obsAB;
        int hets = obsAB;


        //make sure "rare" allele is really the rare allele
        if (rare > diplotypes){
            rare = 2*diplotypes-rare;
        }

        //make sure numbers aren't screwy
        if (hets > rare){
            throw new NotCalculableException("HW test: " + hets + "heterozygotes but only " + rare + "rare alleles.");
        }
        double[] tailProbs = new double[rare+1];
        for (int z = 0; z < tailProbs.length; z++){
            tailProbs[z] = 0;
        }

        //start at midpoint
        int mid = rare * (2 * diplotypes - rare) / (2 * diplotypes);

        //check to ensure that midpoint and rare alleles have same parity
        if (((rare & 1) ^ (mid & 1)) != 0){
            mid++;
        }
        int hett = mid;
        int hom_r = (rare - mid) / 2;
        int hom_c = diplotypes - hett - hom_r;

        //Calculate probability for each possible observed heterozygote
        //count up to a scaling constant, to avoid underflow and overflow
        tailProbs[mid] = 1.0;
        double sum = tailProbs[mid];
        for (hett = mid; hett > 1; hett -=2){
            tailProbs[hett-2] = (tailProbs[hett] * hett * (hett-1.0))/(4.0*(hom_r + 1.0) * (hom_c + 1.0));
            sum += tailProbs[hett-2];
            //2 fewer hets for next iteration -> add one rare and one common homozygote
            hom_r++;
            hom_c++;
        }

        hett = mid;
        hom_r = (rare - mid) / 2;
        hom_c = diplotypes - hett - hom_r;
        for (hett = mid; hett <= rare - 2; hett += 2){
            tailProbs[hett+2] = (tailProbs[hett] * 4.0 * hom_r * hom_c) / ((hett+2.0)*(hett+1.0));
            sum += tailProbs[hett+2];
            //2 more hets for next iteration -> subtract one rare and one common homozygote
            hom_r--;
            hom_c--;
        }

        for (int z = 0; z < tailProbs.length; z++){
            tailProbs[z] /= sum;
        }

        double top = tailProbs[hets];
        for (int i = hets+1; i <= rare; i++){
            top += tailProbs[i];
        }
        double otherSide = tailProbs[hets];
        for (int i = hets-1; i >= 0; i--){
            otherSide += tailProbs[i];
        }

        if (top > 0.5 && otherSide > 0.5){
            return 1.0;
        }else{
            if (top < otherSide){
                return top * 2;
            }else{
                return otherSide * 2;
            }
        }
    }
	
	private int convert2No(String s){
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
	
}
