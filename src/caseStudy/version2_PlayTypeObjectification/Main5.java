package caseStudy.version2_PlayTypeObjectification;

import java.io.IOException;

import caseStudy.fileHandling.JSONFileReader;
import caseStudy.fileHandling.Preparation;
import caseStudy.immutable.UnknownPlayIdException;
import caseStudy.immutable.UnknownPlayTypeException;

public class Main5 {

	public static void main(String[] args) throws UnknownPlayTypeException, IOException, UnknownPlayIdException{
		// prepare(); // Uncomment to create JSON Documents
		statementPrint();
	}
	public static void statementPrint() throws IOException, UnknownPlayTypeException, UnknownPlayIdException {
		JSONFileReader reader = new JSONFileReader();
		System.out.println(new Statement().createStatement(reader.readInvoices().get(0), reader.readPlays()));		
	}
/**
 * Initially create the JSON Documents - OVERWRITES  current data!
 */
	private static void prepare() throws IOException {
		Preparation.perform();
	}
}
