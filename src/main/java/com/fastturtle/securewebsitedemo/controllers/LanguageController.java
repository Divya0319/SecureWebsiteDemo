package com.fastturtle.securewebsitedemo.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LanguageController {

//    @GetMapping("/language")
//    public ModelAndView language(@RequestParam(value = "lang", defaultValue = "en") String lang) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        if(lang.equals("hi") || lang.equals("hne"))
//            modelAndView.addObject("chosenLanguage", "hi");
//        else {
//            modelAndView.addObject("chosenLanguage", "en");
//        }
//
//        return modelAndView;
//    }

    @GetMapping("/")
    public String home(@CookieValue(value = "preferredLanguage", defaultValue = "en") String lang,
                             @RequestParam(value = "lang", required = false) String langParam,
                             HttpServletResponse response, Model model) {
        // Use the `lang` parameter if present; otherwise, use the cookie value
        String language = (langParam != null) ? langParam : lang;

        // Update the cookie if the `lang` parameter is provided and different from the current value
        if (langParam != null && !langParam.equals(lang)) {
            Cookie preferredLanguageCookie = new Cookie("preferredLanguage", langParam);
            preferredLanguageCookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
            preferredLanguageCookie.setPath("/"); // Ensure the cookie applies to the whole site
            response.addCookie(preferredLanguageCookie);
        }

        // Add the chosen language to the model so it's available in the Thymeleaf template
        if(language.equals("hi") || language.equals("hne"))
            model.addAttribute("chosenLanguage", "hi");
        else {
            model.addAttribute("chosenLanguage", "en");
        }

        // Check if the `lang` parameter is already in the URL, if not, redirect with `lang`
        if (langParam == null) {
            return "redirect:/?lang=" + language;
        }

        // Otherwise, simply return the view with the current language
        return "index"; // Your template file
    }

}
