package com.blibliproject.product.security;

import com.blibliproject.product.exception.ApiKeyNotFoundException;
import com.blibliproject.product.exception.ApiKeyNotIdentifiedException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class ApiKeyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return ApiKey.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String value = "API-KEY";

        String apiKey = webRequest.getHeader(value);

        if (apiKey == null) {
            throw new ApiKeyNotFoundException("Error");
        } else if (!apiKey.equals("products")){
            throw new ApiKeyNotIdentifiedException("Error");
        } else {
            return new ApiKey(value);
        }
    }
}
