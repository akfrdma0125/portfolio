package com.jeohyoo.portfolio.web.advisor;

import com.jeohyoo.portfolio.common.util.JsonUtils;
import com.jeohyoo.portfolio.web.dto.ApiResponse;
import com.jeohyoo.portfolio.web.generator.ApiResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice(basePackages = "com.jeohyoo.portfolio")
public class ApiResponseWrappingAdvisor implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return !converterType.isAssignableFrom(ByteArrayHttpMessageConverter.class) &&
                !converterType.isAssignableFrom(ResourceHttpMessageConverter.class);
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  @NonNull MethodParameter returnType,
                                  @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        // 요청 uri
        String path = request.getURI().getPath();

        // http status 확인
        HttpStatus responseStatus = HttpStatus.valueOf(
                ((ServletServerHttpResponse) response).getServletResponse().getStatus()
        );

        //데이터 받아오기
        ApiResponse<?> apiResponse = generateApiResponse(path, body, response, responseStatus);

        // 만약 string 타입이라면 json 으로 변환
        if (selectedConverterType.isAssignableFrom(StringHttpMessageConverter.class)) {
            return convertToJson(response, apiResponse);
        }

        return apiResponse;
    }

    private static String convertToJson(ServerHttpResponse response, ApiResponse<?> apiResponse) {
        try {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return JsonUtils.toJson(apiResponse);
        } catch (JsonUtils.JsonEncodeException jpe) {
            log.warn("JSON 처리 중 오류 발생", jpe);
            throw new ApiResponseJsonProcessingException(jpe);
        }
    }

    private ApiResponse<?> generateApiResponse(String path, Object body, ServerHttpResponse response, HttpStatus responseStatus) {
        if (responseStatus.isError()) {
            return handleError(path, body);
        }
        if (Objects.isNull(body)) {
            response.setStatusCode(HttpStatus.CREATED);
            return ApiResponseGenerator.created(path);
        }
        return ApiResponseGenerator.success(path, body);
    }

    private ApiResponse<?> handleError(String path, Object body){
        // 예외 처리 핸들러에서 처리한 경우
        if (body instanceof ApiResponse) {
            ApiResponse<?> errorResponse = (ApiResponse<?>) body;
            errorResponse.setPath(path);
            return errorResponse;
        }
        // 처리되지 않은 경우, 내용 부재
        return ApiResponseGenerator.fail(path, body);
    }
}
