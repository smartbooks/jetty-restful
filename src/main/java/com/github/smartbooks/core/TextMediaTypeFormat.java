package com.github.smartbooks.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TextMediaTypeFormat extends MediaTypeFormat {

	public TextMediaTypeFormat(){
		contentType = "text/html";
	}
	
	@Override
	public String Render(Object content) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();

		return mapper.writeValueAsString(content);
	}

}
