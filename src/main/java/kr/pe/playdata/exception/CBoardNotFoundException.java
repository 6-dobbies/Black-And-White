package kr.pe.playdata.exception;

public class CBoardNotFoundException extends RuntimeException {
	
    public CBoardNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
    
    public CBoardNotFoundException(String msg) {
        super(msg);
    }
    
    public CBoardNotFoundException() {
        super();
    }
    
}
