package com.baggage.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class BaggageException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3576388054291862136L;
	
	public BaggageException() {
	}
	
	public BaggageException(String errorMessage) {
		super(errorMessage);
	}
	
	public BaggageException(Throwable ex) {
		super(ex);
	}

	public static String getStackTrace(Exception ex) {
		StringWriter sWriter = new StringWriter();
		PrintWriter pWriter = new PrintWriter(sWriter);
		ex.printStackTrace(pWriter);
		return sWriter.toString();
	}
}
