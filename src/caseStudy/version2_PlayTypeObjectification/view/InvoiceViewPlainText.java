package caseStudy.version2_PlayTypeObjectification.view;

import java.util.List;

public class InvoiceViewPlainText extends InvoiceView {
	public InvoiceViewPlainText(String customer, List<PerformanceView> performances, float totalAmount,
			int totalVolumeCredits) {
		super(customer, performances, totalAmount, totalVolumeCredits);
	}
	public String render() {
		String result = "Statement for " + customer + "\n"; 
		for (PerformanceView currentPerformance : this.getPerformances()) result += currentPerformance.render();
		result += "Total Claim: " + this.totalAmount + " €\n";
		result += "You earned " + this.totalVolumeCredits + " credits";
		return result;
	}
}
