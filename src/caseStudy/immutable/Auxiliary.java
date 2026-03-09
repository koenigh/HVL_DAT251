package caseStudy.immutable;

import java.util.function.Function;

public class Auxiliary {
	public static <T,R,E extends Exception> Function<T,R> throwingFunctionWrapper(ThrowingExceptionFunction<T, R, E> f){
		return x-> { 
					try{return f.perform(x);}catch(Exception e) {throw new RuntimeException(e.getMessage());}
		};
	}
	public static String capitalize(String s) {
		if(s.isEmpty()) return s;
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
}
