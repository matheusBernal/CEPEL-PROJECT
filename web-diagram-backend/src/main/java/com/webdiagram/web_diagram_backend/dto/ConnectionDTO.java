package com.webdiagram.web_diagram_backend.dto;

public class ConnectionDTO {
    public String id;
    public String source;
    public String target;

    public ConnectionDTO(String id, String source, String target) {
        this.id = id;
        this.source = source;
        this.target = target;
    }
}
