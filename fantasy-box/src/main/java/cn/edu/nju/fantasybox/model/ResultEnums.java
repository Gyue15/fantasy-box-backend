package cn.edu.nju.fantasybox.model;


import org.springframework.http.HttpStatus;

public enum ResultEnums  {


    SUCCESS("200", "请求成功"),
    ACTIVATE_EMAIL_SEND("207", "账户激活邮件已发送"),
    ACTIVATE_SUCCESS("208", "账户激活成功"),

    UNAUTHORIZED("401","未授权"),
    LOGIN_ERROR("418","用户名或密码错误"),
    EMAIL_OCCUPIED("419","邮箱已被注册"),
    FILE_NOT_FOUND("420","文件不存在"),
    USER_NOT_FOUND("421","用户不存在"),
    TOKEN_NOT_FOUND("422","token不存在，请重新登录"),
    USERNAME_OCCUPIED("423","用户名已存在"),
    ACTIVATE_FAIL("424","激活失败(token错误)"),
    TOKEN_PARSE_FAIL("424","token解析失败，请重新登录"),
    TOKEN_EXPIRE("425","token过期，请重新登录"),
    TOKEN_WRONG("426","token错误"),
    NOT_ACTIVATED("427","账户尚未激活"),
    ERROR("500", "服务器出错"),
    FILE_UPLOAD_ERROR("506","文件上传出错"),

    ;

    private String code;
    private String msg;

    ResultEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
