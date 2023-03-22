package com.example.spring_boot;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
public class SpringBootDemo{

    @RequestMapping
    @ResponseBody
    String home(){
        return "Hello World!";
    }
}
