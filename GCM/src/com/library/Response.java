package com.library;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Response {
	
	
	@JsonProperty("error")
	private String error;
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
	public String toJson() throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper(); 
		return mapper.writeValueAsString(this);
	}
		
	public Response() {
		
    }
	
	public Response(String error) {
		super();
		this.error = error;
	}
}
