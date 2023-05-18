package com.inow.csp.output.client;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientResponseBean {
	
	@JsonProperty("jwtToken")
	private String jwtToken;

    public void setJWTToken(String jwtToken) {
    	this.jwtToken = jwtToken;
	}


	public String getJWTToken() {
        return jwtToken;
    }


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getJWTToken();
	}
    
    

}
