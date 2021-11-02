package edu.episen.si.ing1.pds.client.socket;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class ResponseSocket {
    private String request ;
    private Object data ;

    @JsonGetter("request")
    public String getRequest() {
        return request;
    }

    public Object getData() {
        return data;
    }

    @JsonSetter("request")
    public void setRequest(String request) {
        this.request = request;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseSocket{" +
                "request='" + request + '\'' +
                ", data=" + data +
                '}';
    }
}
