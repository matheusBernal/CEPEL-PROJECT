package com.webdiagram.web_diagram_backend.controller;

import javax.print.attribute.standard.Media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.webdiagram.web_diagram_backend.dto.DiagramDTO;
import com.webdiagram.web_diagram_backend.service.DiagramService;


@RestController
@RequestMapping("/diagram")

public class PostController {

    @Autowired
    private DiagramService diagramService;

    @PostMapping(value="/upload",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DiagramDTO> uploadXml(@RequestParam("file") MultipartFile file) {
        //TODO: process POST request
        try {
            DiagramDTO diagram = diagramService.parseXmlToJson(file);
            return ResponseEntity.ok(diagram);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
    
    
}
