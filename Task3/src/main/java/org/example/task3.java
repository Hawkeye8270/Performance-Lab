package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class task3 {

    public static void main(String[] args) {

        String valuesFilePath = args[0];
        String testsFilePath = args[1];
        String reportFilePath = args[2];

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode valuesNode = objectMapper.readTree(new File(valuesFilePath));
            JsonNode testsNode = objectMapper.readTree(new File(testsFilePath));

            Map<String, String> valuesMap = new HashMap<>();
            for (JsonNode valueNode : valuesNode) {

                for (int i = 0; i < valueNode.size(); i++) {
                    String id = valueNode.get(i).get("id").asText();
                    String value = valueNode.get(i).get("value").asText();
                    valuesMap.put(id, value);
                }
            }

            fillToReport(testsNode, valuesMap);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), testsNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillToReport(JsonNode testsNode, Map<String, String> valuesMap) {
        if (testsNode.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = testsNode.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                String key = field.getKey();
                JsonNode valueNode = field.getValue();

                if (key.equals("id") && valuesMap.containsKey(valueNode.asText())) {
                    String correspondingValue = valuesMap.get(valueNode.asText());
                    ((ObjectNode) testsNode).put("value", correspondingValue);
                }

                if (valueNode.isObject() || valueNode.isArray()) {
                    fillToReport(valueNode, valuesMap);
                }
            }
        } else if (testsNode.isArray()) {
            for (JsonNode arrayNode : testsNode) {
                fillToReport(arrayNode, valuesMap);
            }
        }
    }
}