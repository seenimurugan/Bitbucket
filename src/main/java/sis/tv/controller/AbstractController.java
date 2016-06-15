package sis.tv.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sis.tv.util.JsonResponse;

public abstract class AbstractController {

    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private final static String ERRORS = "errors";
    
    protected JsonResponse getExceptionJsonResponse(Exception ex, String controllerErrorMessage) {
        JsonResponse jsonResponse = getFailJsonResponse(controllerErrorMessage);
        jsonResponse.getResult().put(ERRORS, ex.getMessage());
        return jsonResponse;
    }
    
    protected JsonResponse getFailJsonResponse(String failMessage) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setSuccess(false);
        jsonResponse.setMessage(failMessage);
        return jsonResponse;
    }

    protected JsonResponse getSuccessJsonResponse(String successMessage) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setSuccess(true);
        jsonResponse.setMessage(successMessage);
        return jsonResponse;
    }

}
