package cn.edu.nju.fantasybox.util;


import cn.edu.nju.fantasybox.model.ResponseData;
import cn.edu.nju.fantasybox.model.ResultEnums;

public class ResponseDataUtil {
    /**
     * 带实体的统一返回
     *
     * @param data 实体
     * @return ResponseData对象
     */
    public static ResponseData buildSuccess(Object data) {
        return new ResponseData(ResultEnums.SUCCESS, data);
    }

    public static ResponseData buildSuccess() {
        return new ResponseData(ResultEnums.SUCCESS);
    }

    public static ResponseData buildSuccess(String msg) {
        return new ResponseData(ResultEnums.SUCCESS.getCode(), msg);
    }

    public static ResponseData buildSuccess(String code, String msg) {
        return new ResponseData(code, msg);
    }

    public static ResponseData buildSuccess(String code, String msg, Object data) {
        return new ResponseData(code, msg, data);
    }

    public static ResponseData buildSuccess(ResultEnums resultEnums) {
        return new ResponseData(resultEnums);
    }

    public static ResponseData buildError(Object data) {
        return new ResponseData(ResultEnums.ERROR, data);
    }

    public static ResponseData buildError() {
        return new ResponseData(ResultEnums.ERROR);
    }

    public static ResponseData buildError(String msg) {
        return new ResponseData(ResultEnums.ERROR.getCode(), msg);
    }

    public static ResponseData buildError(String code, String msg) {
        return new ResponseData(code, msg);
    }

    public static ResponseData buildError(String code, String msg, Object data) {
        return new ResponseData(code, msg, data);
    }

    public static ResponseData buildError(ResultEnums resultEnums) {
        return new ResponseData(resultEnums);
    }
}
