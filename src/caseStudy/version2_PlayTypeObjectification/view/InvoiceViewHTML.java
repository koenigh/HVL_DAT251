package caseStudy.version2_PlayTypeObjectification.view;

import java.util.List;

public class InvoiceViewHTML extends InvoiceView {

	public InvoiceViewHTML(String customer, List<PerformanceView> performances, float totalAmount,
			int totalVolumeCredits) {
		super(customer, performances, totalAmount, totalVolumeCredits);
	}

	public String render() {
		return "Some HTML text";
	}

}
