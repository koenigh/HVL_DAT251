package caseStudy.immutable;

public class UnknownPlayIdException extends Exception {
	private static final long serialVersionUID = 1L;

	public UnknownPlayIdException(String message) {
		super(message);
	}
}
