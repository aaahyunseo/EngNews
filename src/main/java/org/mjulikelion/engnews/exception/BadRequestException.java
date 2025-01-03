package org.mjulikelion.engnews.exception;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
    public BadRequestException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
