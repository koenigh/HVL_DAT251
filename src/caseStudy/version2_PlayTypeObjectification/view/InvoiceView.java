package caseStudy.version2_PlayTypeObjectification.view;

import java.util.List;
/**
 * A DTO for invoice data
 */
public abstract class InvoiceView {
	protected String customer;
	protected List<PerformanceView> performances;
	protected float totalAmount;
	protected int totalVolumeCredits;
	public InvoiceView(String customer, List<PerformanceView> performances, float totalAmount, int totalVolumeCredits) {
		super();
		this.customer = customer;
		this.performances = performances;
		this.totalAmount = totalAmount;
		this.totalVolumeCredits = totalVolumeCredits;
	}
	public List<PerformanceView> getPerformances(){
		return this.performances;
	}
/**	
 * @return s a textual representation of the given invoice data 
 */
	public abstract String render();
}
