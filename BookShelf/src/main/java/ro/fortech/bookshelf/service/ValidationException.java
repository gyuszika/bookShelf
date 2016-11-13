package ro.fortech.bookshelf.service;

import java.util.Arrays;

public class ValidationException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1404211109844747157L;
	private String[] causes;

	public ValidationException(String... causes) {
		super();
		this.causes = causes;
	}

	@Override
	public String getMessage() {

		return causes != null ? Arrays.toString(causes) : "No CAUSE!";
	}

	public String[] getCauses() {
		return causes;
	}

	public void setCauses(String[] causes) {
		this.causes = causes;
	}
}
