package com.project.help.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
class IndexController {

    @RequestMapping("/")
    public String index() {
        return "/user/login";
    }
}