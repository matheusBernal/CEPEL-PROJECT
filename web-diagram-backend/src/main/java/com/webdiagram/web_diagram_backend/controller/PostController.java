package com.webdiagram.web_diagram_backend.controller;

import javax.print.attribute.standard.Media;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/diagram")

public class PostController {

    @PostMapping(value="/upload",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String>uploadXml(@RequestParam("file") MultipartFile file) {
        //TODO: process POST request
        if (file.isEmpty()){
            return ResponseEntity.badRequest().body("Nenhum arquivo enviado");
        }
        return ResponseEntity.ok("Recebido: " + file.getOriginalFilename());
    }
    
    
}
