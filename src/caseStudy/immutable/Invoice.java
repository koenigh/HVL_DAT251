package caseStudy.immutable;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
	private String customer;
	private List<Performance> performances;
	public Invoice(String customer) {
		super();
		this.customer = customer;
		this.performances = new ArrayList<>();
	}
	public void addPerformance(Performance p) {
		this.performances.add(p);
	}
	public String getCustomer() {
		return this.customer;
	}
	public List<Performance> getPerformances() {
		return this.performances;
	}

}
