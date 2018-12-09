package com.harish.projA.exceptions;

import com.harish.projA.common.ErrorCodes;

public class TeacherExceptions extends Exception {

	private String error;
	private int code;
	private ErrorCodes errCode;

	public TeacherExceptions(String error, int status) {
		this.error = error;
		this.code = status;
	}
	public TeacherExceptions(ErrorCodes errCode) {
		this.errCode = errCode;
		this.code = errCode.getHttpStatusCode();
		this.error = errCode.getMessage();
	}


	public ErrorCodes getErrCode() {
		return this.errCode;
	}
	public void setErrCode(ErrorCodes errCode) {
		this.errCode = errCode;
	}
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

}
