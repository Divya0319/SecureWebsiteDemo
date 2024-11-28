package com.fastturtle.securewebsitedemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class ServerTimeController {

    @GetMapping("/time")
    public String serverTimePage(Model model) {
        ZonedDateTime serverTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));  // GMT + 5:30
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss 'GMT + 5:30'");
        String formattedTime = serverTime.format(formatter);

        model.addAttribute("serverTime", formattedTime);
        return "serverTimePage";
    }
}
