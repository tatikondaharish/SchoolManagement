package com.harish.projA.exceptions;

import com.harish.projA.common.ErrorCodes;

public class KlassExceptions extends Exception {

	private String error;
	private int code;
	private ErrorCodes errCode;

	public String getError() {
		return this.error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public int getCode() {
		return this.code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public ErrorCodes getErrCode() {
		return errCode;
	}


	public void setErrCode(ErrorCodes errCode) {
		this.errCode = errCode;
	}


	public KlassExceptions(String error, int status) {
		this.error = error;
		this.code = status;
	}
	public KlassExceptions(ErrorCodes errCode) {
		this.errCode = errCode;
		this.code = errCode.getHttpStatusCode();
		this.error = errCode.getMessage();
	}

}
