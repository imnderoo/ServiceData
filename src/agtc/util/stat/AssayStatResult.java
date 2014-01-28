/*
 * Created on Jun 6, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package agtc.util.stat;

/**
 * @author Hongjing
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AssayStatResult {
	private double obsHet;
	private double preHet;
	private double maf;
	private double hwPValue;
	private double genoPct;
	private boolean noResult;
	/**
	 * @return Returns the noResult.
	 */
	public boolean isNoResult() {
		return noResult;
	}
	/**
	 * @param noResult The noResult to set.
	 */
	public void setNoResult(boolean noResult) {
		this.noResult = noResult;
	}
	public AssayStatResult(){
		
	}
	
	
	/**
	 * @return Returns the genoPct.
	 */
	public double getGenoPct() {
		return genoPct;
	}
	/**
	 * @param genoPct The genoPct to set.
	 */
	public void setGenoPct(double genoPct) {
		this.genoPct = genoPct;
	}
	/**
	 * @return Returns the hwPValue.
	 */
	public double getHwPValue() {
		return hwPValue;
	}
	/**
	 * @param hwPValue The hwPValue to set.
	 */
	public void setHwPValue(double hwPValue) {
		this.hwPValue = hwPValue;
	}
	/**
	 * @return Returns the maf.
	 */
	public double getMaf() {
		return maf;
	}
	/**
	 * @param maf The maf to set.
	 */
	public void setMaf(double maf) {
		this.maf = maf;
	}
	/**
	 * @return Returns the obsHet.
	 */
	public double getObsHet() {
		return obsHet;
	}
	/**
	 * @param obsHet The obsHet to set.
	 */
	public void setObsHet(double obsHet) {
		this.obsHet = obsHet;
	}
	/**
	 * @return Returns the preHet.
	 */
	public double getPreHet() {
		return preHet;
	}
	/**
	 * @param preHet The preHet to set.
	 */
	public void setPreHet(double preHet) {
		this.preHet = preHet;
	}
}
