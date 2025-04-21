package com.webdiagram.web_diagram_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdiagram.web_diagram_backend.service.HelloService;


@RestController
public class HelloControler {
    @Autowired // Diz ao Spring: "injeta a classe HelloService aqui"
    private HelloService helloService;
    @GetMapping("/hello")
    public String hello() {
        return helloService.getMessage(); // Agora usa o service!
    }
    
    
}
