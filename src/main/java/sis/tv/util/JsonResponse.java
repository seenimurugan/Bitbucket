package sis.tv.util;

import java.util.HashMap;
import java.util.Map;

public class JsonResponse {

    // All custom response parameters can be put into customParameters
    private Map result;
    // Message will have either success message or error message
    private String message;
    // Setting success true or false decide to call either onSuccess or onError on the caller
    private boolean success;

    public Map<Object, Object> getResult() {
        if (result == null) {
            result = new HashMap<>();
        }
        return result;
    }

    public void setResult(Map result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
