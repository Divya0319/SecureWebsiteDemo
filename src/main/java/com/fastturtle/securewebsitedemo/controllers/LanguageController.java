package com.fastturtle.securewebsitedemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LanguageController {

    @GetMapping("/language")
    public ModelAndView language(@RequestParam(value = "lang", defaultValue = "en") String lang) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        if(lang.equals("hi") || lang.equals("hne"))
            modelAndView.addObject("chosenLanguage", "hi");
        else {
            modelAndView.addObject("chosenLanguage", "en");
        }

        return modelAndView;
    }

}
