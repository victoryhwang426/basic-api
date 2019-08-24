package gg.rest.dto;

import lombok.Data;

@Data
public class ResultMessage {
    public enum ResponseMessage {
        SUCCESS, NOT_FOUND, NO_RESULT
    }

    private ResponseMessage responseMessage;
    private Object content;

    public ResultMessage(ResponseMessage responseMessage, Object content){
        this.responseMessage = responseMessage;
        this.content = content;
    }

    public ResultMessage(ResponseMessage responseMessage){
        this.responseMessage = responseMessage;
    }
}
