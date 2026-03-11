import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import caseStudy.fileHandling.JSONFileReader;
import caseStudy.immutable.Invoice;
import caseStudy.immutable.Play;
import caseStudy.immutable.UnknownPlayIdException;
import caseStudy.immutable.UnknownPlayTypeException;
import caseStudy.version1_LoopSplitting.*;

class StatementTests {
	private static JSONFileReader reader;
	private static Map<String, Play> plays;
	private static List<Invoice> invoices; 
	private static Statement statement;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		reader = new JSONFileReader();
		plays = reader.readPlays();
		invoices = reader.readInvoices();
		statement = new Statement();
	}

	@Test
	void test0() throws Exception {
		String expected = 
				"Statement for BigCo\n"
						+ "Hamlet: 650.0 €, (55 visitors).\n"
						+ "As you like it: 475.0 €, (35 visitors).\n"
						+ "Othello: 500.0 €, (40 visitors).\n"
						+ "Total Claim: 1625.0 €\n"
						+ "You earned 47 credits";
		assertEquals(expected, statement.createStatement(invoices.get(0), plays));	
	}
	@Test
	void test1() throws Exception {
		String expected = 
				"Statement for Pub22\n"
				+ "Hamlet: 800.0 €, (70 visitors).\n"
				+ "As you like it: 300.0 €, (0 visitors).\n"
				+ "Othello: 400.0 €, (0 visitors).\n"
				+ "Total Claim: 1500.0 €\n"
				+ "You earned 40 credits";
		assertEquals(expected, statement.createStatement(invoices.get(1), plays));	
	}
	@Test
	void test2() throws Exception {
		String expected = 
				"Statement for Pub23\n"
				+ "Hamlet: 400.0 €, (0 visitors).\n"
				+ "As you like it: 525.0 €, (45 visitors).\n"
				+ "Othello: 400.0 €, (0 visitors).\n"
				+ "Total Claim: 1325.0 €\n"
				+ "You earned 24 credits";
		assertEquals(expected, statement.createStatement(invoices.get(2), plays));	
	}
	@Test
	void test3() throws Exception {
		String expected = 
				"Statement for Pub24\n"
				+ "Hamlet: 400.0 €, (0 visitors).\n"
				+ "As you like it: 300.0 €, (0 visitors).\n"
				+ "Othello: 410.0 €, (31 visitors).\n"
				+ "Total Claim: 1110.0 €\n"
				+ "You earned 1 credits";
		assertEquals(expected, statement.createStatement(invoices.get(3), plays));	
	}
	@Test
	void test4() throws Exception {
		String expected = 
				"Statement for Pierre's\n"
				+ "Hamlet: 400.0 €, (0 visitors).\n"
				+ "As you like it: 300.0 €, (0 visitors).\n"
				+ "Othello: 400.0 €, (0 visitors).\n"
				+ "Total Claim: 1100.0 €\n"
				+ "You earned 0 credits";
		assertEquals(expected, statement.createStatement(invoices.get(4), plays));	
	}
	@Test
	void test5() throws Exception {
		String expected = 
				"Statement for Pierre's\n"
				+ "Total Claim: 0.0 €\n"
				+ "You earned 0 credits";
		assertEquals(expected, statement.createStatement(invoices.get(5), plays));	
	}
// An unexpected case: 
//	@Test
	void test6() throws Exception{
		assertTrue(!(statement.createStatement(invoices.get(7), plays).isEmpty())); // We did not recognize the typo in the JSON file
	}
/*========= Negative tests =========================== */	
	@Test
	void test100(){
		assertThrows(UnknownPlayTypeException.class, ()->statement.createStatement(invoices.get(6), plays));
	}
// After adding new subclass for new play type	
//	@Test
	void test101() throws UnknownPlayTypeException, UnknownPlayIdException {
		System.out.println(statement.createStatement(invoices.get(6), plays));
	}
	
}
