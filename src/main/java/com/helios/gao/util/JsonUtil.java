package com.helios.gao.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @author : gaozhiwen
 * @date : 2019/7/3
 */
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToJSONString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T stringToObject(String jsonString, Class<T> tClass) throws IOException {
        return objectMapper.readValue(jsonString, tClass);
    }

    public static <T> T mapToObject(Map<?, ?> map, Class<T> tClass) {
        try {
            return stringToObject(objectToJSONString(map), tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
