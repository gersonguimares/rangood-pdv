package br.com.rangood.pdv.rangoodpdvcustomerservice.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RestControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    public static final Logger logger = LoggerFactory.getLogger(RestErrorHandler.class);

    public static final String REQUEST_VALIDATION_ERROR = "REQUEST_VALIDATION_ERROR";
    public static final String INVALID_FORMAT_ATTRIBUTE = "INVALID_FORMAT_ATTRIBUTE";
    public static final String INVALID_FORMAT_PATH_VARIABLE = "INVALID_FORMAT_PATH_VARIABLE";
    public static final String INVALID_PAYLOAD_STRUCTURE = "INVALID_PAYLOAD_STRUCTURE";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> unhandledException(Exception ex) {

        if(ex instanceof IllegalArgumentException) {
            return ResponseEntity.badRequest().body(
                    new RestErrorResponseModel(INVALID_FORMAT_PATH_VARIABLE, "Invalid format for path variables", null)
            );
        }

        return ResponseEntity.internalServerError().body(
                new RestErrorResponseModel("SEVERAL_ERROR", "An irreversible error has occurred. The system administrator has been notified. We regret what happened :(", null)
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getParameter().getParameterName());
        return ResponseEntity.badRequest().body(
                new RestErrorResponseModel(INVALID_FORMAT_ATTRIBUTE, "Invalid format path variable", details)
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex) {

        logger.warn(ConstraintViolationException.class.getCanonicalName(), ex.getMessage());

        List<String> details = new ArrayList<>();
        for (ConstraintViolation i : ex.getConstraintViolations()) details.add(i.getPropertyPath().toString() + " " + i.getMessage());

        return ResponseEntity.badRequest().body(
                new RestErrorResponseModel(INVALID_FORMAT_PATH_VARIABLE, "Invalid format for property(ies)", details)
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.warn(MethodArgumentNotValidException.class.getCanonicalName(), ex.getMessage());
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            final HttpMessageNotReadableException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request)
    {

        if(ex.getCause() instanceof InvalidFormatException) {
            logger.warn(InvalidFormatException.class.getCanonicalName(), ex.getMessage());

            final InvalidFormatException ife = (InvalidFormatException) ex.getCause();
            List<String> details = new ArrayList<>();
            for (JsonMappingException.Reference i : ife.getPath()) details.add(i.getFieldName());

            return ResponseEntity.badRequest().body(
                    new RestErrorResponseModel(INVALID_FORMAT_ATTRIBUTE, "Invalid format for property(ies)", details)
            );
        }

        if(ex.getCause() instanceof JsonParseException || ex.getCause() instanceof JsonMappingException) {
            logger.warn(JsonParseException.class.getCanonicalName(), ex.getMessage());

            return ResponseEntity.badRequest().body(
                    new RestErrorResponseModel(INVALID_PAYLOAD_STRUCTURE, "Invalid JSON payload structure", null)
            );
        }

        return ResponseEntity.badRequest().body(
            new RestErrorResponseModel(INVALID_PAYLOAD_STRUCTURE, "Invalid payload structure", null)
        );
    }

    public static class RestErrorResponseModel {
        private String error;
        private String message;
        private List<String> details;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<String> getDetails() {
            return details;
        }

        public void setDetails(List<String> details) {
            this.details = details;
        }

        public RestErrorResponseModel(String error, String message, List<String> details) {
            this.error = error;
            this.message = message;
            if(details == null) {
                this.details = new ArrayList<>();
            } else {
                this.details = details;
            }
        }

        public static List<String> buildDetailsErrorList(String[] ...keyValuePair) {
            List<String> l = new ArrayList<>();
            for (String[] pair: keyValuePair) {
                l.add(pair[0] + " :: " + pair[1]);
            }
            return l;
        }
    }

    public static class ResponseEntityErrorBuilder {

        public static ResponseEntity conflited(String[] ...keyValuePair) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new RestErrorResponseModel(
                            "CONFLITED_REQUEST",
                            "The values provided caused conflicts in the resource.",
                            RestErrorResponseModel.buildDetailsErrorList(keyValuePair)
                    )
            );
        }

        public static ResponseEntity badRequest(String[] ...keyValuePair) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new RestErrorResponseModel(
                            "REQUEST_VALIDATION_ERROR",
                            "Values entered are invalid",
                            RestErrorResponseModel.buildDetailsErrorList(keyValuePair)
                    )
            );
        }

        public static ResponseEntity unavailableService() {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                    new RestErrorResponseModel(
                            "SERVICE_UNAVAILABLE",
                            "Service temporarily unavailable",
                            null
                    )
            );
        }

    }

}
