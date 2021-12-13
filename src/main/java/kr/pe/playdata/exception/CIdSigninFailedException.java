package kr.pe.playdata.exception;

public class CIdSigninFailedException extends RuntimeException {
	
    public CIdSigninFailedException(String msg, Throwable t) {
        super(msg, t);
    }

    public CIdSigninFailedException(String msg) {
        super(msg);
    }

    public CIdSigninFailedException() {
        super();
    }
    
}
