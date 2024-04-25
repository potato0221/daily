package com.ll.daily.global.exceptions;


public enum ExceptionMsg {
    E401_1_UN_AUTHORIZED("400-3", "해당 유저가 존재하지 않습니다."),
    E401_2_NOT_CORRECT_PASSWORD("400-4", "비밀번호가 일치하지 않습니다."),
    E401_3_NOT_EXIST_REFRESHTOKEN("400-5",  "존재하지 않는 리프레시 토큰입니다."),
    E403_1_FORBIDDEN("403-1", "권한이 없습니다."),
    E404_1_DATA_NOT_FOUND("404-1", "해당 데이터를 찾을 수 없습니다.");

    private  final String code;
    private final String msg;

    // enum 생성자
    ExceptionMsg(String code, String message){
        this.code = code;
        this.msg = message;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
