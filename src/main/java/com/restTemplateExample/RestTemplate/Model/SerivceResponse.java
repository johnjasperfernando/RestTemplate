package com.restTemplateExample.RestTemplate.Model;

public class SerivceResponse {
    private int resposeCode;
    private String responseMessage;
    private Object response;

    public int getResposeCode() {
        return resposeCode;
    }

    public void setResposeCode(int resposeCode) {
        this.resposeCode = resposeCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}
