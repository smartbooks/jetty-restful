package com.github.smartbooks.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonMediaTypeFormat extends MediaTypeFormat {

    public JsonMediaTypeFormat() {
        contentType = "application/json";
    }

    @Override
    public String Render(Object content) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(content);
    }

}
