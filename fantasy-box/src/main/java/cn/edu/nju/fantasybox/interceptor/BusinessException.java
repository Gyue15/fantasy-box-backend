package cn.edu.nju.fantasybox.interceptor;

import cn.edu.nju.fantasybox.model.ResultEnums;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private String code;
    private String msg;

    public BusinessException(ResultEnums resultEnums) {
        this.code = resultEnums.getCode();
        this.msg = resultEnums.getMsg();
    }


}

