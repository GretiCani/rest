package com.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class HelloWorldControllerI18N {

    private final ResourceBundleMessageSource messageSource;

    @GetMapping("/helloI18N")
    public String getHello(@RequestHeader(name = "Accept-Language",required = false)String locale){

        return  messageSource.getMessage("label.helloworld",null,new Locale(locale));
    }
}
