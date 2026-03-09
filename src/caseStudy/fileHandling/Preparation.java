package caseStudy.fileHandling;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import caseStudy.immutable.Invoice;
import caseStudy.immutable.Performance;
import caseStudy.immutable.Play;

public class Preparation {
	public static void perform() throws IOException {
		Map<String, Play> plays = new HashMap<>();
		plays.put("hamlet", new Play("Hamlet", "tragedy"));
		plays.put("as-like", new Play("As you like it", "comedy"));
		plays.put("othello", new Play("Othello", "tragedy"));

		File filePlays = new File("plays.json");
		File fileInvoices = new File("invoices.json");
		
		JsonDatabind.writeObject(plays, filePlays);
		
		Invoice invoice = new Invoice("BigCo");
		invoice.addPerformance(new Performance("hamlet", 55));
		invoice.addPerformance(new Performance("as-like", 35));
		invoice.addPerformance(new Performance("othello", 40));
		List<Invoice> invoices = new ArrayList<>();
		invoices.add(invoice);
		
		JsonDatabind.writeObject(invoices, fileInvoices);
		
		System.out.println("Data saved to JSON files!");
	}
}
