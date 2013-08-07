package com.tscp.toolkit.exception;



public class UserToolkitException extends ToolkitException {
	
	  private static final long serialVersionUID = 1L;

	  public UserToolkitException() {
	    super();
	  }

	  public UserToolkitException(String message) {
	    super(message);
	  }

	  public UserToolkitException(Throwable cause) {
	    super(cause);
	  }

	  public UserToolkitException(String message, Throwable cause) {
	    super(message, cause);
	  }
}
