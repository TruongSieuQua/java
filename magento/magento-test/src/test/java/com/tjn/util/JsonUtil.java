package com.tjn.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T getTestCase(String path, Class<T> type){
        try(InputStream stream = ResourceLoader.getResource(path)){
            return mapper.readValue(stream, type);
        }catch (Exception e){
            log.error("unable to read test data {}", path, e);
        }
        return null;
    }

    public static <T> List<T> getTestCases(String path, final TypeReference<List<T>> listType) {
        try (InputStream stream = ResourceLoader.getResource(path)) {
            return mapper.readValue(stream, listType);
        } catch (Exception e) {
            log.error("Unable to read test cases list from {}", path, e);
        }
        return null;
    }

}
