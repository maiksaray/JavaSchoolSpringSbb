package com.springapp.mvc.model.interaction;

import java.io.Serializable;

/**
 * Created by maiks_000 on 3/18/14.
 */
public class Request implements Serializable{
    private RequestType type;
    private Object content;

    public Request(RequestType type, Object content){
        this.type = type;
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public RequestType getType() {
        return type;
    }
}
