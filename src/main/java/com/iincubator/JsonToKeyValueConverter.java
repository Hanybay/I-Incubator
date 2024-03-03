package com.iincubator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iincubator.ParserRestaurant.Contenu;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JsonToKeyValueConverter {
public List<Map.Entry<String, Object>> convertJsonToKeyValue(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            return flatten(jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Map.Entry<String, Object>> flatten(JsonNode node) {
        List<Map.Entry<String, Object>> keyValueList = new ArrayList<>();
        flatten("", node, keyValueList);
        return keyValueList;
    }

    private void flatten(String prefix, JsonNode node, List<Map.Entry<String, Object>> flatMap) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                flatten(prefix + entry.getKey() + ".", entry.getValue(), flatMap);
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                flatten(prefix + "OBJECT.", node.get(i), flatMap);
            }
        } else if (node.isValueNode()) {
            flatMap.add(new AbstractMap.SimpleEntry<>(prefix, node.asText()));
        }
    }
    
     public Contenu convertJsonToObject(String jsonString) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, Contenu.class);
    }
    
}
