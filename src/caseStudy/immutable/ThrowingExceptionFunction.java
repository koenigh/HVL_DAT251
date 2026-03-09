package caseStudy.immutable;
@FunctionalInterface
public interface ThrowingExceptionFunction<T,R, E extends Exception> {
	public R perform(T t) throws E;
}
