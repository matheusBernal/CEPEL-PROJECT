package com.webdiagram.web_diagram_backend.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.*;
import com.webdiagram.web_diagram_backend.dto.ComponentDTO;
import com.webdiagram.web_diagram_backend.dto.ConnectionDTO;
import com.webdiagram.web_diagram_backend.dto.DiagramDTO;

@Service
public class DiagramService {

    public DiagramDTO parseXmlToJson(MultipartFile file) throws Exception {
        List<ComponentDTO> components = new ArrayList<>();
        List<ConnectionDTO> connections = new ArrayList<>();
    
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Cria o parser XML
        DocumentBuilder builder = factory.newDocumentBuilder(); 
        Document doc = builder.parse(file.getInputStream()); // Lê o conteúdo do arquivo e transforma em documento XML
    

        NodeList componentItems = doc.getElementsByTagName("components").item(0).getChildNodes();  // Pega todos os <component> do XML
        for (int i = 0; i < componentItems.getLength(); i++) {
            if (!(componentItems.item(i) instanceof Element)) continue;
            Element item = (Element) componentItems.item(i);
    
            String id = getText(item, "id");
            String type = getText(item, "type");
    
            if (!isComponent(type) || !isVisible(item)) continue;
    

            Element data = (Element) item.getElementsByTagName("data").item(0);
            String title = getText(data, "title");
            Integer kv = tryParseInt(getText(data, "kv"));
    
            Element position = (Element) item.getElementsByTagName("position").item(0);
            int x = Integer.parseInt(position.getElementsByTagName("x").item(0).getTextContent());
            int y = Integer.parseInt(position.getElementsByTagName("y").item(0).getTextContent());
    
            components.add(new ComponentDTO(id, type, title, kv, x, y));
        }


        NodeList connectionItems = doc.getElementsByTagName("connections").item(0).getChildNodes();
        for (int i = 0; i < connectionItems.getLength(); i++) {
            if (!(connectionItems.item(i) instanceof Element)) continue;
            Element item = (Element) connectionItems.item(i);
    
            if (!isVisible(item)) continue;
    
            String id = getText(item, "id");
            String source = getText(item, "source");
            String target = getText(item, "target");
    
            connections.add(new ConnectionDTO(id, source, target));
        }
    
        return new DiagramDTO(components, connections);
    }

    private boolean isComponent(String type) {
        return List.of("bus", "machine", "load").contains(type);
    }

    private boolean isVisible(Element item) {
        NodeList hiddenTag = item.getElementsByTagName("hidden");
        return hiddenTag.getLength() == 0 || "false".equals(hiddenTag.item(0).getTextContent());
    }

    private String getText(Element element, String tagName) {
        NodeList nodes = element.getElementsByTagName(tagName);
        if (nodes.getLength() == 0) return null;
        return nodes.item(0).getTextContent();
    }

    private Integer tryParseInt(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException | NullPointerException e) {
            return null;
        }
    }
    
}
