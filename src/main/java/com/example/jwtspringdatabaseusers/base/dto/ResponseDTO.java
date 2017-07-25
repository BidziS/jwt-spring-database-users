package com.example.jwtspringdatabaseusers.base.dto;

import io.swagger.annotations.ApiModel;

/**
 * Created by Bidzis on 7/18/2017.
 */
@ApiModel
public class ResponseDTO {

    private String response;

    public ResponseDTO() {
    }

    public ResponseDTO(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
