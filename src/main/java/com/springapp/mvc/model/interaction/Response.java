package com.springapp.mvc.model.interaction;

import java.io.Serializable;

/**
 * Created by maiks_000 on 3/19/14.
 */
public class Response implements Serializable {
    private ResponseType type;
    private Object content;

    public Response(ResponseType type) {
        this(type, null);
    }

    public Response(ResponseType type, Object content) {
        this.type = type;
        this.content = content;
    }

    public ResponseType getType() {
        return type;
    }

    public Object getContent() {
        return content;
    }
}
