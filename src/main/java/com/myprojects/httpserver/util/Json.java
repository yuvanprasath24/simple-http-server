package com.myprojects.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {

    private static ObjectMapper mapper = getMapper();

    private static ObjectMapper getMapper() {
            mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }

    public static JsonNode parse(String json) throws JsonProcessingException {
        return mapper.readTree(json);
    }

    public <A> A fromJson(JsonNode jsonNode, Class<A> clazz) throws JsonProcessingException {
        return mapper.treeToValue(jsonNode, clazz);
    }

    public static JsonNode toJson(Object obj) throws JsonProcessingException {
        return mapper.valueToTree(obj);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }

    public static String generateJson(JsonNode node, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = mapper.writer();
        if(pretty) {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(node);
    }

}
