package com.webdiagram.web_diagram_backend.dto;

import java.util.List;

public class DiagramDTO {
    public List<ComponentDTO> components;
    public List<ConnectionDTO> connections;

    public DiagramDTO(List<ComponentDTO> components,List<ConnectionDTO> connections){
        this.components = components;
        this.connections = connections;
    }

}
