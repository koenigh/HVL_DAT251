package caseStudy.version2_PlayTypeObjectification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import caseStudy.immutable.Invoice;
import caseStudy.immutable.Performance;
import caseStudy.immutable.Play;
import caseStudy.immutable.UnknownPlayIdException;
import caseStudy.immutable.UnknownPlayTypeException;
import caseStudy.version2_PlayTypeObjectification.playStructure.Comedy;
import caseStudy.version2_PlayTypeObjectification.playStructure.PlayType;
import caseStudy.version2_PlayTypeObjectification.playStructure.Tragedy;
import caseStudy.version2_PlayTypeObjectification.view.InvoiceView;
import caseStudy.version2_PlayTypeObjectification.view.InvoiceViewPlainText;
import caseStudy.version2_PlayTypeObjectification.view.PerformanceView;
import caseStudy.version2_PlayTypeObjectification.view.PerformanceViewPlainText;
/**
 * Creates a textual statement for an invoice based on a set of plays via the method createStatement()
 */
public class Statement {
	private Map<String, Play> plays; // Attention: This variable is determined occasionally by methods!
	private Map<String, PlayType> playTypeObjects;   // Maps a play type to the corresponding object. 	
/**
 * @returns a textual representation of 
 * @param invoice, which is based on the set of 
 * @param plays
 * @throws UnknownPlayTypeException, if an unknown PlayType occurred
 * @throws UnknownPlayIdException 
 */	
	public String createStatement(Invoice invoice, Map<String, Play> plays) throws UnknownPlayTypeException, UnknownPlayIdException {
		this.plays = plays;
		this.playTypeObjects = new HashMap<>();
		this.playTypeObjects.put("tragedy", new Tragedy());
		this.playTypeObjects.put("comedy", new Comedy());
		return this.createStatementData(invoice).render();
	}
/**	
 * @return s a View Object for an
 * @param invoice 
 * @throws UnknownPlayIdException 
 */
	private InvoiceView createStatementData(Invoice invoice) throws UnknownPlayTypeException, UnknownPlayIdException {
		List<PerformanceView> performanceStatements = new ArrayList<>();
		for (Performance currentPerformance : invoice.getPerformances()) 
			performanceStatements.add(this.createPerformanceData(currentPerformance));
		return new InvoiceViewPlainText(
				invoice.getCustomer(), 
				performanceStatements, 
				(float)determineTotalAmount(invoice)/100, 
				this.determineTotalVolumeCredits(invoice));
	}
/**	
 * @return s a View Object for
 * @param aPerformance 
 * @throws UnknownPlayIdException 
 */	
	private PerformanceView createPerformanceData(Performance aPerformance) throws UnknownPlayTypeException, UnknownPlayIdException {
		if(!this.playTypeObjects.containsKey(this.playOf(aPerformance).getType())) 
			throw new UnknownPlayTypeException(this.playOf(aPerformance).getType());
		return new PerformanceViewPlainText(
				this.playOf(aPerformance).getName(),
				this.playTypeObjects.get(this.playOf(aPerformance).getType()),
				((float)this.playTypeObjects.get(this.playOf(aPerformance).getType()).determineAmount(aPerformance.getAudience()) / 100), 
				aPerformance.getAudience());	
	}
// ============== Calculating amounts =================================	
/**
 * @return s the total amount for 	
 * @param anInvoice
 * @throws UnknownPlayTypeException, if an unknown play type occured
 * @throws UnknownPlayIdException 
 */
	private int determineTotalAmount(Invoice anInvoice) throws UnknownPlayTypeException, UnknownPlayIdException {
		int totalAmount = 0;
		for (Performance currentPerformance : anInvoice.getPerformances())
			totalAmount += this.getTypeObjectFor(currentPerformance).determineAmount(currentPerformance.getAudience()); 
		return totalAmount;
	}
/**
 * @return the total volume credits for 	
 * @param anInvoice
 * @throws UnknownPlayTypeException 
 * @throws UnknownPlayIdException 
 */
	private int determineTotalVolumeCredits(Invoice anInvoice) throws UnknownPlayTypeException, UnknownPlayIdException {
		int totalCredits = 0;
		for (Performance currentPerformance : anInvoice.getPerformances())
			totalCredits += this.getTypeObjectFor(currentPerformance).volumeCredits(currentPerformance.getAudience());
		return totalCredits;
	}

// ============== Auxiliaries =================================		
/**	
 * Encapsulates access to the calculator for a given performance. 
 */
	private PlayType getTypeObjectFor(Performance aPerformance) throws UnknownPlayTypeException {
		if (!this.playTypeObjects.containsKey(this.playOf(aPerformance).getType())) throw new UnknownPlayTypeException(this.playOf(aPerformance).getType());
		return this.playTypeObjects.get(this.playOf(aPerformance).getType());
	}
/**	
 * @return the play of 
 * @param aPerformance
 */
	private Play playOf(Performance aPerformance) {
		return this.plays.get(aPerformance.getPlayId());
	}
}
