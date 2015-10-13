package com.nexacro.spring.dao;

public class NexacroFirstRowException extends RuntimeException {

	/**
     * 메시지를 가지는 생성자이다.
     * 
     * @param message
     *            메시지
     */
    public NexacroFirstRowException(String message) {
        super(message);
    }

    /**
     * 메시지와 원천(cause) 예외를 가지는 생성자이다.
     * 
     * @param message
     *            메시지
     * @param cause
     *            원천 예외
     */
    public NexacroFirstRowException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
