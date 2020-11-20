package br.edu.ifpb.pweb2.caderneta.business.exception;

public class CadernetaException extends Exception {
	private static final long serialVersionUID = 1L;

	public CadernetaException() {
		super();
	}

	public CadernetaException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public CadernetaException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CadernetaException(String arg0) {
		super(arg0);
	}

	public CadernetaException(Throwable arg0) {
		super(arg0);
	}
	
}