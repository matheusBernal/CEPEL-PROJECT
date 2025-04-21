package com.webdiagram.web_diagram_backend.service;

import org.springframework.stereotype.Service;

@Service // Diz ao Spring que essa classe pode ser injetada com @Autowired

public class HelloService {
    public String getMessage() {
        return "Mensagem vinda do service";
    }
}
