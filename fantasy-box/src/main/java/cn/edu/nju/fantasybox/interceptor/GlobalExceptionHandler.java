package cn.edu.nju.fantasybox.interceptor;

import cn.edu.nju.fantasybox.model.ResponseData;
import cn.edu.nju.fantasybox.util.ResponseDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理 Exception 异常
     *
     * @param e 异常
     * @return ResponseData对象
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseData exceptionHandler(Exception e) {
        logger.error("服务器出错", e);
        return ResponseDataUtil.buildError();
    }

    /**
     * 处理 BusinessException 异常
     *
     * @param e 异常
     * @return ResponseData对象
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseData businessExceptionHandler(BusinessException e) {
        logger.error("业务异常  code:" + e.getCode() + "  msg:" + e.getMsg());
        return ResponseDataUtil.buildError(e.getCode(), e.getMsg());
    }

}
