package ru.athena.test.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

/**
 * Test mapping util
 */
public final class TestMappingUtil {

    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    private TestMappingUtil() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT, false);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

    /**
     * Convert any object in byte array using {@link ObjectMapper}
     *
     * @param object - object to converting
     * @return array of object byte
     */
    public static byte[] convertObjectToJsonBytes(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert any object in JSON string using com.fasterxml.jackson.databind.ObjectMapper
     *
     * @param object - object to converting
     * @return object like json
     */
    public static String convertObjectToJsonString(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves body from MvcResult and converts it to the specified class
     *
     * @param mvcResult - controller response
     * @param dtoClass  - class to which you want to convert
     * @return object of specified class
     */
    public static <T> T convertMvcResultToDTO(MvcResult mvcResult, Class<T> dtoClass) {
        try {
            String jsonResult = mvcResult.getResponse().getContentAsString();
            return OBJECT_MAPPER.readValue(jsonResult, dtoClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves body from MvcResult and converts it to the list of specified class
     *
     * @param mvcResult - controller response
     * @param dtoClass  - class to which you want to convert
     * @return list of object of specified class
     */
    public static <T> List<T> convertMvcResultToDTOList(MvcResult mvcResult, Class<T> dtoClass) {
        try {
            String jsonResult = mvcResult.getResponse().getContentAsString();
            return OBJECT_MAPPER.readValue(jsonResult, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, dtoClass));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
