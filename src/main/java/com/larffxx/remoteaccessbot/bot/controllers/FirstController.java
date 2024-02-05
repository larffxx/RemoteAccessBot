package com.larffxx.remoteaccessbot.bot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "a", required = false) int a,
                            @RequestParam(value = "b", required = false) int b,
                            @RequestParam(value = "action", required = false) String action,
                            Model model) {
        if(action == null || action.isEmpty() && a == 0 && b == 0){
            action = "add";
            a = 1;
            b = 1;
        }
        switch (action) {
            case "mult":
                model.addAttribute("action", a * b);
                break;
            case "add":
                model.addAttribute("action", a + b);
                break;
            case "sub":
                model.addAttribute("action", a - b);
                break;
            case "div":
                model.addAttribute("action", a / b);
                break;
            default:
                break;
        }
        return "hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "goodbye";
    }
}
