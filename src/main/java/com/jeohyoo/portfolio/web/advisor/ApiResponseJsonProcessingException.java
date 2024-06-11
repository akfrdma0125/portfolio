package com.jeohyoo.portfolio.web.advisor;


import com.jeohyoo.portfolio.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

import static com.jeohyoo.portfolio.common.domain.BusinessCode.API_RESPONSE_ERROR;


public class ApiResponseJsonProcessingException extends BusinessException {

    public ApiResponseJsonProcessingException(Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return API_RESPONSE_ERROR.getHttpStatus();
    }

    @Override
    public String getErrorCode() {
        return API_RESPONSE_ERROR.getCode();
    }

    @Override
    public boolean isNecessaryToLog() {
        return true;
    }
}
