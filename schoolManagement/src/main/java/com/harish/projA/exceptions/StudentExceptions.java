package com.harish.projA.exceptions;

import com.harish.projA.common.ErrorCodes;

public class StudentExceptions extends Exception {
	private String error;
	private int code;
	private ErrorCodes errCode;
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	public StudentExceptions(ErrorCodes errCode) {
		this.errCode = errCode;
		this.code = errCode.getHttpStatusCode();
		this.error = errCode.getMessage();
	}

	
	public ErrorCodes getErrCode() {
		return errCode;
	}

	public void setErrCode(ErrorCodes errCode) {
		this.errCode = errCode;
	}

	public StudentExceptions(String error, int status) {
		this.error = error;
		this.code = status;
	}

}
