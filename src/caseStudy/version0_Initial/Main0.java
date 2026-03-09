package caseStudy.version0_Initial;

import java.io.IOException;

import caseStudy.fileHandling.JSONFileReader;
import caseStudy.fileHandling.Preparation;
import caseStudy.immutable.Invoice;
import caseStudy.immutable.Play;
import caseStudy.immutable.UnknownPlayTypeException;

public class Main0 {

	public static void main(String[] args) throws UnknownPlayTypeException, IOException{
		statementPrint();
	}
	public static void statementPrint() throws IOException, UnknownPlayTypeException {
		JSONFileReader reader = new JSONFileReader();
		
// ======= Only the first invoice for testing ==================
		Statement s = new Statement();
		System.out.println(s.createStatement(reader.readInvoices().get(0), reader.readPlays()));		
	}
/**
 * Initially create the JSON Documents - OVERWRITES the current data!
 */
	private static void prepare() throws IOException {
		Preparation.perform();
	}
}
