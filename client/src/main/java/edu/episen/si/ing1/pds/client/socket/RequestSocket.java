package edu.episen.si.ing1.pds.client.socket;

public class RequestSocket {
    private String request;
    private Object data;

    //this class is use to do the request
    public void setRequest(String request){this.request = request;}
    public void setData(Object data){ this.data = data;}
    public String getRequest(){return request;}
    public Object getData(){return  data;}

    @Override
    public String toString(){
        return "RequestSocket{" +
                "request = '" + request + '\''+
                "data= " + data + '}';

    }
}
