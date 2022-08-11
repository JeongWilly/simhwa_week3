package com.week05.post_9.exception.heart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    INVALID_JWT_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 JWT 토큰입니다."),
    MISMATCH_JWT_USER(HttpStatus.BAD_REQUEST, "jwt 토큰과 요청 유저가 일치하지 않습니다."),

    /* 401 UNAUTHORIZED : 잘못된 요청 */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "유효한 인증 자격 증명이 없습니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저 정보를 찾을 수 없습니다"),
    TOPIC_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 카테고리를 찾을 수 없습니다."),
    HEART_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 좋아요 정보를 찾을 수 없습니다."),
    CAMPAIGN_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 캠페인 정보를 찾을 수 없습니다."),
    BANNER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 배너 이미지를 찾을 수 없습니다."),



    /* 500 SERVER_ERROR */
    FAIL_ENCODING(HttpStatus.INTERNAL_SERVER_ERROR,"IO 에러로 이미지 URL 인코딩을 실패했습니다."),
    FAIL_SAVE_BANNER(HttpStatus.INTERNAL_SERVER_ERROR,"IO 에러로 배너 이미지 저장을 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String detail;

}
