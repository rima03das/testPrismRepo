package com.cee.userrole.validator;


import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cee.userrole.exception.BadRequestException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RequestValidatorInterceptor implements HandlerInterceptor {

    static final String BASE_URL = "/api/v2/userrole/";
    static final String TOKEN_KEYWORD = "token";
    static final String TOKEN_MISSING_MESSAGE = "Token missing in the requestken";

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
            throws Exception {

        String url = request.getRequestURI();

        if (StringUtils.equals(url, BASE_URL)) {
            return true;
        }

        if (url.indexOf(BASE_URL) < 0) {
            return true;
        }
        final String token = request.getHeader(TOKEN_KEYWORD);
        if (StringUtils.isBlank(token)) {
            throw new BadRequestException(TOKEN_MISSING_MESSAGE);
        }

        return true;
    }
}
