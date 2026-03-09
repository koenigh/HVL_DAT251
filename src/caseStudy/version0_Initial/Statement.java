package caseStudy.version0_Initial;

import java.util.Map;

import caseStudy.immutable.Invoice;
import caseStudy.immutable.Performance;
import caseStudy.immutable.Play;
import caseStudy.immutable.UnknownPlayTypeException;

public class Statement {
/**
 * @returns a textual representation of an
 * @param invoice , which is based on the set of 
 * @param plays .
 * @throws UnknownPlayTypeException, if an unknown PlayType occured
 */
	public String createStatement(Invoice invoice, Map<String, Play> plays) throws UnknownPlayTypeException {
		int totalAmount = 0;
		int volumeCredits = 0;
		String result = "Statement for " + invoice.getCustomer() + "\n";
		for (Performance currentPerformance : invoice.getPerformances()) {
			Play play = plays.get(currentPerformance.getPlayId());
			int thisAmount = 0;
			switch (play.getType()) {
				case "tragedy":
					thisAmount = 40000;
					if (currentPerformance.getAudience() > 30) 
						thisAmount += 1000 * (currentPerformance.getAudience() - 30);
					break;
				case "comedy":
					thisAmount = 30000;
					if (currentPerformance.getAudience() > 20) 
						thisAmount += 10000 + 500 * (currentPerformance.getAudience() - 20);
					break;	
			default:
				throw new UnknownPlayTypeException("Play Type " + play.getType() + " not known!");
			}
	// Add volume credits 
			volumeCredits += Math.max(currentPerformance.getAudience() - 30, 0);
			if(play.getType().equals("comedy")) volumeCredits += currentPerformance.getAudience() / 5;
	// Print line for this performance
			result 	+= play.getName() + ": " + ((float)thisAmount / 100) 
					+ " €, (" + currentPerformance.getAudience() + " visitors).\n";
			totalAmount += thisAmount;
		}
		result += "Total Claim: " + (float)totalAmount/100 + " €\n";
		result += "You earned " + volumeCredits + " credits";
		return result;
	}
}
