package com.aa.handler;

import com.aa.dto.Response;
import com.aa.excetion.CustomerException;
import com.aa.excetion.ParamsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @descriotion 全局统一异常处理
 * @author ghx
 * @date 2019/7/12
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * @description exception 异常
     * @author ghx
     * @param exception
     * @exception
     * @return
     * @date 2019/7/12
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Response handle(Exception exception) {
        Response response = new Response();
        try {
            log.error("exception异常：" + exception.getMessage());
            return response.setFaile("系统错误!");
        } catch (Exception e) {
            log.error("exception异常：" + exception.getMessage(), e);
        }
        return response.setFaile("系统错误");
    }
    /**
     * @description ParamsException 异常 用于自己controller  进行手动校验controller层前端传入参数 抛出 ParamsException
     * @author ghx
     * @param exception
     * @exception
     * @return
     * @date 2019/7/12
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ParamsException.class)
    public Response paramsExceptionHandler(Exception exception) {
        Response response = new Response();
        ParamsException paramsException = (ParamsException) exception;
        try {
            log.error("exception异常：" + exception.getMessage());
            return response.setFaile(paramsException.getMsg());
        } catch (Exception e) {
            log.error("exception异常：" + exception.getMessage(), e);
        }
        return response.setFaile("系统错误");
    }

    /**
     * @description CustomerException 异常  用于业务不合法 抛出异常 CustomerException
     * @author ghx
     * @param exception
     * @exception
     * @return
     * @date 2019/7/12
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomerException.class)
    public Response customerExceptionHandler(Exception exception) {
        Response response = new Response();
        CustomerException customerException = (CustomerException) exception;
        try {
            log.error("exception异常：" + exception.getMessage());
            return response.setFaile(customerException.getMsg());
        } catch (Exception e) {
            log.error("exception异常：" + exception.getMessage(), e);
        }
        return response.setFaile("系统错误");
    }


    /**
     * @description validate方法级参数校验
     * @author ghx
     * @param exception
     * @exception
     * @return
     * @date 2019/7/12
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Response bindhandle(Exception exception) {
        Response response = new Response();
        try {
            BindException bindException = (BindException) exception;
            List<ObjectError> allErrors = bindException.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            return response.setFaile(objectError.getDefaultMessage());
        } catch (Exception e) {
            log.error("bingHandler错误:" + e.getMessage(), e);
        }
        return response.setFaile("系统错误");
    }

    /**
     * @description validate方法级参数校验
     * @author ghx
     * @param exception
     * @exception
     * @return
     * @date 2019/7/12
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Response constraintViolationhandle(Exception exception) {
        Response response = new Response();
        try {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) exception;
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            String message = constraintViolations.stream().map(cons -> cons.getMessageTemplate()).collect(Collectors.joining(",", "参数错误:", ""));
            return response.setFaile(message);
        } catch (Exception e) {
            log.error("bingHandler错误:" + e.getMessage(), e);
        }
        return response.setFaile("系统错误");
    }


    /**
     * @description validate实体内(bean)中参数校验
     * @author ghx
     * @param exception
     * @exception
     * @return
     * @date 2019/7/12
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response bodyhandler(Exception exception) {
        Response response = new Response();
        try {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) exception;
            BindingResult bindingResult = me.getBindingResult();
            String message = Optional.ofNullable(bindingResult)
                    .map(result -> result.getAllErrors()).orElseGet(() -> new ArrayList<ObjectError>())
                    .stream()
                    .map(e -> e.getDefaultMessage()).findFirst().orElseGet(() -> "unknow");
            //.collect(Collectors.joining(",", "参数错误:", ""));
            return response.setFaile("参数错误:" + message);
        } catch (Exception e) {
            log.error("MethodArgumentNotValidException:" + e.getMessage(), e);
        }
        return response.setFaile("系统错误");
    }
}
