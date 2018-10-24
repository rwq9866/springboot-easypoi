package com.muyou.easypoi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EasypoiHTMLController {

    @RequestMapping("e")
    public String easypoi(){
        return "easypoi";
    }
}
