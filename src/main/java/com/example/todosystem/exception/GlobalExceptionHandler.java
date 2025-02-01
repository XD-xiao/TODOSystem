package com.example.todosystem.exception;

import com.example.todosystem.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleGenericException(Exception e){
        e.printStackTrace();
        return Result.error("服务器异常");
    }

    // 处理未找到处理器的异常
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleNoHandlerFoundException(NoHandlerFoundException nhfe) {
        nhfe.printStackTrace();
        return Result.error("请求路径不存在，请检查URL后重试");
    }

    // 处理空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Result handleNullPointerException(NullPointerException npe) {
        npe.printStackTrace();
        return Result.error("发生空指针异常，请联系管理员");
    }

    // 处理非法参数异常
    @ExceptionHandler(IllegalArgumentException.class)
    public Result handleIllegalArgumentException(IllegalArgumentException iae) {
        iae.printStackTrace();
        return Result.error("非法参数：" + iae.getMessage());
    }

    // 处理 NoResourceFoundException 异常
    @ExceptionHandler(NoResourceFoundException.class)
    public Result handleNoResourceFoundException(NoResourceFoundException nrfe) {
        nrfe.printStackTrace();
        System.out.println("NoResourceFoundException");
        return Result.error("未找到请求的静态资源，请检查路径后重试");
    }

    // 处理 DuplicateKeyException 异常
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException dke) {
        // 提取错误信息，这里可以根据实际情况进行调整
        String errorMessage = "数据冲突: 可能存在重复的键值，请检查输入的数据";
        if (dke.getCause() instanceof java.sql.SQLIntegrityConstraintViolationException) {
            errorMessage = "数据冲突: 该邮箱已被注册";
        }
        return Result.error(errorMessage);
    }

    // 你可以根据需要添加更多的异常处理器
}