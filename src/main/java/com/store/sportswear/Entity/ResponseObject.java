package com.store.sportswear.Entity;

import java.util.Map;

public class ResponseObject {
    private Object object;
    private Map<String, String >errorMessages=null;
    private String status;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseObject [status="+status+"]";
    }
}
