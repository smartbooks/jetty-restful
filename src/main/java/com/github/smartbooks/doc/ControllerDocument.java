package com.github.smartbooks.doc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class ControllerDocument {

	public String packageName = "";

	public String controllerName = "";

	public List<ControllerActionDocument> methodList = new ArrayList<ControllerActionDocument>();

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return "";
	}

}
