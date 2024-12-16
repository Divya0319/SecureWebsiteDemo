package com.fastturtle.securewebsitedemo.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class LanguageController {

    private static final Logger logger = LoggerFactory.getLogger(LanguageController.class);

    @RequestMapping("/setLanguage")
    public String setLanguage(@RequestParam("lang") String lang, HttpServletResponse response) {
        Locale locale = new Locale(lang);
        LocaleContextHolder.setLocale(locale);

        logger.info("Current Locale: {}", LocaleContextHolder.getLocale());

        // Create a cookie to store the selected language
        Cookie langCookie = new Cookie("lang", lang);
        langCookie.setMaxAge(7 * 24 * 60 * 60); // Valid for 7 days
        langCookie.setPath("/"); // Available site-wide
        response.addCookie(langCookie);

        logger.info("Language cookie set: {}", lang);

        return "redirect:/";
    }

}
