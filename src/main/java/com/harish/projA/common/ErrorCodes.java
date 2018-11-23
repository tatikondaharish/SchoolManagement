package com.harish.projA.common;

public enum ErrorCodes {
	INVALID_HEADER(1001,400,"Bad Request"),
	INVALID_FORMAT(1002,400,"Invalid Data"),
	BAD_REQUEST(1003,400, "Bad Request"),
    NOT_ACCEPTABLE(1004,406, "Not Acceptable"),
    PRECONDITION_FAILED(1005,412, "Precondition Failed"),
    REQUEST_ENTITY_TOO_LARGE(1006,413, "Request Entity Too Large"),
    REQUEST_URI_TOO_LONG(1007,414, "Request-URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(1008,415, "Unsupported Media Type"),
    COLUMN_NOT_FOUND_IN_DB(1009,500, "Internal Server Error"),
    DB_CONNECTION_FAILE(1011,500,"Internal Server Error"),
    SERVICE_UNAVAILABLE(1010,503, "Service Unavailable");
	
	
	private int code;
	private int httpStatusCode;
	private String message;
   public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setHttpStausCode(int httpStatusCode) {
		this.httpStatusCode=httpStatusCode;
	}
	public int getHttpStatusCode() {
		return httpStatusCode;
	}
	ErrorCodes(int code,int httpStatusCode,String message){
	   this.code=code;
	   this.httpStatusCode = httpStatusCode;
	   this.message=message;
   }
	    
	
	    
}
