package com.pd.system.framework;

/**
 * vams 运行时异常
 * */
public class VamsRTExcaption extends RuntimeException {
	public VamsRTExcaption() {
		super();
	}

	public VamsRTExcaption(String message) {
		super(message);
	}

	public VamsRTExcaption(String message, Throwable cause) {
		super(message, cause);
	}

	public VamsRTExcaption(Throwable cause) {
		super(cause);
	}
}
