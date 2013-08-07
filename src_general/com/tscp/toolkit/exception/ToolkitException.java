package com.tscp.toolkit.exception;



public class ToolkitException extends RuntimeException {
	
	  private static final long serialVersionUID = 1L;

	  private String message;
	  
	  public ToolkitException() {
	    super();
	  }

	  public ToolkitException(String message) {
	    super(message);
        this.message = message;
	  }

	  public ToolkitException(Throwable cause) {
	    super(cause);
	    this.message = cause.getMessage();
	  }

	  public ToolkitException(String message, Throwable cause) {
	    super(message, cause);
	    this.message = message;
	  }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}	  	  
}
