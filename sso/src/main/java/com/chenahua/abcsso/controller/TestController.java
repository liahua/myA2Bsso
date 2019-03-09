package com.chenahua.abcsso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/doTestNotADomain")
    @ResponseBody
    public String doTestNotADomain(String test){
        System.out.println("test = " + test);
        System.out.println("TestController.doTestNotADomain");
        return null;
    }
}
