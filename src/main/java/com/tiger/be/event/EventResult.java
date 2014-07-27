package com.tiger.be.event;

import java.io.Serializable;

public class EventResult implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum ResultType {
		successful, failed, warning
	}
	
	private ResultType result;
	
	private int errorCode;
	
	private String errorMsg;
	
	private Throwable error;
	
	public boolean isSuccessful() {
		return result == ResultType.successful;
	}
	
	public boolean isFailed() {
		return result == ResultType.failed;
	}

	public ResultType getResult() {
		return result;
	}

	public void setResult(ResultType result) {
		this.result = result;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Throwable getError() {
		return error;
	}

	public void setError(Throwable error) {
		this.error = error;
	}
	
}
