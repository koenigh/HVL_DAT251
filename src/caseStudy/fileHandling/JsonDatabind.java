package caseStudy.fileHandling;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import caseStudy.immutable.Invoice;
import caseStudy.immutable.Performance;
import caseStudy.immutable.Play;
/**
 * Transformation pipeline:
 * JSON File <-(Part1)-> Intermediate general format <-(Part2)-> Domain objects 
 */
public class JsonDatabind {
// ============= Part 1 ==========================	
    public static <T> void writeObject(T object, File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(file, object);
    }
    public static <T> T readObject(Class<T> ofType, File into) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(into, ofType);
    }

// ============= Part 2 ===========================	    
/**
 * @param readerResult is the returned structure from a JSON read call
 * @returns a fully fledged map of plays
 */
    public static Map<String, Play> transform(Map<?,?> readerResult){
    	Map<String, Play> result = new HashMap<>();
    	for (Map.Entry<?,?> entry : readerResult.entrySet()) {
			String key = (String)entry.getKey();
			LinkedHashMap<?, ?> val = (LinkedHashMap<?, ?>)entry.getValue();
	    	result.put(key, new Play(val.get("name").toString(), val.get("type").toString()));
    	}	
       	return result;
    }
/**
 * @param readerResult is the returned structure from a JSON read call
 * @returns a fully fledged list of invoice objects
 */
    public static List<Invoice> transform(List<LinkedHashMap<?,?>> readerResult){
    	List<Invoice> result = new ArrayList<>();
    	for (LinkedHashMap<?,?> invoiceData : readerResult) {
    		Invoice invoice = new Invoice(invoiceData.get("customer").toString());
    		result.add(invoice);
    		List<LinkedHashMap<?, ?>> performancesData = (List<LinkedHashMap<?, ?>>)invoiceData.get("performances");
    		for (LinkedHashMap<?, ?> performanceData : performancesData) invoice.addPerformance(transform(performanceData));
		}
    	return result;
    }
/**
 * Transforms raw performance data from JSON structure to a Java Object    
 */
    private static Performance transform(LinkedHashMap<?, ?> performanceData) {
    	return new Performance(performanceData.get("playId").toString(), (Integer)performanceData.get("audience"));
    }
    
    
// ========== Alternative: com.google.GSON =======================     
//    public static <T> String writeObjectWithGSON(T object){
//    	Gson gson = new Gson();
//    	return gson.toJson(object);
//    }
//    public static <T> T readObjectWithGSON(Reader reader, Class<T> clazz){
//    	Gson gson = new Gson();
//    	return gson.fromJson(reader, clazz);
//    }
}
