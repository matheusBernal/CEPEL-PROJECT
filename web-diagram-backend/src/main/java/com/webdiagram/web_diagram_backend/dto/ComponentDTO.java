package com.webdiagram.web_diagram_backend.dto;

public class ComponentDTO {
    public String id;
    public String type;
    public String title;
    public Integer baseKv;
    public int x;
    public int y;

    public ComponentDTO(String id, String type, String title, Integer  baseKv, int x, int y){
        this.id = id;
        this.type = type;
        this.title = title;
        this.baseKv = baseKv;
        this.x = x;
        this.y = y;
    }
}
