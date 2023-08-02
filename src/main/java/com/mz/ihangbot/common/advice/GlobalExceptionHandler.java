package com.mz.ihangbot.common.advice;

import com.mz.ihangbot.common.dto.BasicResponse;
import com.mz.ihangbot.common.exception.BusinessException;
import com.mz.ihangbot.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BasicResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);
        BasicResponse response = BasicResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<BasicResponse> handleBusinessException(BusinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        BasicResponse response = BasicResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BasicResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BasicResponse response = BasicResponse.of(ErrorCode.INVALID_INPUT_VALUE,
                e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<BasicResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        BasicResponse response = BasicResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    protected ResponseEntity<BasicResponse> handleIllegalStateException(IllegalStateException e) {
        log.error(e.getMessage(), e);
        BasicResponse response = BasicResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
