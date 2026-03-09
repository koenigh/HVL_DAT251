package caseStudy.fileHandling;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import caseStudy.immutable.Invoice;
import caseStudy.immutable.Play;

public class JSONFileReader {
	public Map<String, Play> readPlays() throws IOException{
		File filePlays = new File("plays.json");
		Map<?,?> readerResultPlays = JsonDatabind.readObject(Map.class, filePlays);
		return JsonDatabind.transform(readerResultPlays);
	}
	public List<Invoice> readInvoices() throws IOException{
		File fileInvoices = new File("invoices.json");
		List readerResultInvoices = JsonDatabind.readObject(List.class, fileInvoices);
		return JsonDatabind.transform(readerResultInvoices);
	}
}
