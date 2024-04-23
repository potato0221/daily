package com.ll.daily.global.exceptions;
import com.ll.daily.standard.base.Empty;
import com.ll.daily.global.rsData.RsData;
import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private final RsData<Empty> rsData;

    public GlobalException(String resultCode, String msg) {
        super("resultCode=" + resultCode + ",msg=" + msg);
        this.rsData = RsData.of(resultCode, msg);
    }

    public GlobalException() {
        this("400-0", "에러");
    }

    public GlobalException(String msg) {
        this("400-0", msg);
    }

}
