package com.example.diary.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member not found"),
    MEMBER_EMAIL_EXIST(409, "Member Email already exists"),
    MEMBER_PASSWORD_EXIST(409, "Member Password already exists");

    @Getter
    private int httpStatusCode;
    @Getter
    private String errorMessage;

    ExceptionCode(int httpStatusCode, String errorMessage) {
        this.httpStatusCode = httpStatusCode;
        this.errorMessage = errorMessage;
    }
}
