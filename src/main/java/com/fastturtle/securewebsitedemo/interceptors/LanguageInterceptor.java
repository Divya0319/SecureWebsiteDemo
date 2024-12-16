package com.fastturtle.securewebsitedemo.interceptors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

public class LanguageInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LanguageInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Check for the "lang" cookie
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("lang".equals(cookie.getName())) {
                    String lang = cookie.getValue();
                    Locale locale = new Locale(lang);
                    LocaleContextHolder.setLocale(locale);

                    logger.info("Locale set from cookie: {}", locale);
                    return true;
                }
            }

        }

        LocaleContextHolder.setLocale(Locale.ENGLISH);
        logger.info("No lang cookie found, setting default locale: ENGLISH");

        return true;
    }
}
