package demo.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import demo.constant.ErrorCode;
import demo.exception.BizException;
import demo.model.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
@Repository
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * 处理业务异常
     *
     * @param request httpServletRequest
     * @param e       bizException
     * @return 返回前端
     */
    @ExceptionHandler(value = BizException.class)
    public ResponseResult<Void> bizExceptionHandler(HttpServletRequest request, BizException e) {
        log.error("fail to handler request:{}", request.toString(), e);
        return ResponseResult.error(e.getErrorCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseResult<Void> exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.error("fail to handler request{}", request.toString(), e);
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return ResponseResult.error(ErrorCode.SERVICE_INTERNAL_ERROR);
    }
}
