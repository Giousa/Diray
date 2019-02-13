package com.zmm.diary.exception;

import com.zmm.diary.bean.ResultVO;
import com.zmm.diary.enums.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 18011618
 * @Description 全局异常拦截器
 * @Date 16:38 2018/7/5
 * @Modify By
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResultVO exception(Exception ex) {
        return ResultVO.error(ResultEnum.SERVE_EXCEPTION);

    }

}