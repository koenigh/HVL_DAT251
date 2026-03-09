package caseStudy.version1_LoopSplitting;

import java.util.Map;

import caseStudy.immutable.Invoice;
import caseStudy.immutable.Performance;
import caseStudy.immutable.Play;
import caseStudy.immutable.UnknownPlayTypeException;

public class Statement {
	private Map<String, Play> plays; 
/**
 * @returns a textual representation of 
 * @param invoice , which is based on the set of 
 * @param plays
 * @throws UnknownPlayTypeException, if an unknown PlayType occured
 */	
	public String createStatement(Invoice invoice, Map<String, Play> plays) throws UnknownPlayTypeException {
		this.plays = plays; 
		String result = "Statement for " + invoice.getCustomer() + "\n"; 
		for (Performance currentPerformance : invoice.getPerformances()) result += this.getStatementFragmentFor(currentPerformance);
		result += "Total Claim: " + (float)determineTotalAmount(invoice)/100 + " €\n";
		result += "You earned " + determineTotalVolumeCredits(invoice) + " credits";
		return result;
	}
/**
 * @returns the fragment in the statement for 
 * @param aPerformance
 * @throws UnknownPlayTypeException, if an unknown play type occured
 */	
	private String getStatementFragmentFor(Performance aPerformance) throws UnknownPlayTypeException {
		return 	this.playOf(aPerformance).getName() + ": " + ((float)this.determineAmountFor(aPerformance) / 100) 
				+ " €, (" + aPerformance.getAudience() + " visitors).\n";
	}
	
/**
 * @returns the total amount for 	
 * @param anInvoice
 * @throws UnknownPlayTypeException, if an unknown play type occured
 */
	private int determineTotalAmount(Invoice anInvoice) throws UnknownPlayTypeException {
		int totalAmount = 0;
		for (Performance currentPerformance : anInvoice.getPerformances()) 
			totalAmount += this.determineAmountFor(currentPerformance);
		return totalAmount;
	}
/**
 * Computes the amount charged for 
 * @param aPerformance 
 * @param of a play
 * @throws UnknownPlayTypeException 
 */
	private int determineAmountFor(Performance aPerformance) throws UnknownPlayTypeException {
		int result = 0;
		switch (this.playOf(aPerformance).getType()) {
			case "tragedy":
				result = 40000;
				if (aPerformance.getAudience() > 30) 
					result += 1000 * (aPerformance.getAudience() - 30);
				break;
			case "comedy":
				result = 30000;
				if (aPerformance.getAudience() > 20) 
					result += 10000 + 500 * (aPerformance.getAudience() - 20);
				break;	
		default:
			throw new UnknownPlayTypeException("Play Type " + this.playOf(aPerformance).getType() + " not known!");
		}
		return result;
	}
	
/**
 * @return the total volume credits for 	
 * @param anInvoice
 */
	private int determineTotalVolumeCredits(Invoice anInvoice) {
		return anInvoice.getPerformances()
					.stream()
					.mapToInt(this::volumeCreditsFor)
					//.mapToInt(aPerformance->volumeCreditsFor(aPerformance))  // Discussion - Readable Code ...
					.sum();
	}
/**
 * @return volume credits for a single 
 * @param aPerformance
 */
	private int volumeCreditsFor(Performance aPerformance) {
		int volumeCredits = Math.max(aPerformance.getAudience() - 30, 0);
		if(this.playOf(aPerformance).getType().equals("comedy")) 
			volumeCredits += aPerformance.getAudience() / 5;
		return volumeCredits;
	}
/**	
 * @return the play of 
 * @param aPerformance
 */
	private Play playOf(Performance aPerformance) {
		return this.plays.get(aPerformance.getPlayId());
	}
}
