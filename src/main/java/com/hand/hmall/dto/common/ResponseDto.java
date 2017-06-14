package com.hand.hmall.dto.common;/**
 * Created by Administrator on 2017/6/13.
 */

/**
 * @author ChenMingjian
 * @create 2017-06-13 18:38
 **/
public class ResponseDto {
    private boolean result = false;
    private String msg = "";

    public ResponseDto() {
    }

    public ResponseDto(String msg, boolean result) {
        this.msg = msg;
        this.result = result;

    }
}
