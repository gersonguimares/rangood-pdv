package br.com.rangood.pdv.rangoodpdvproductservice.controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationErrorHandler {

    public static RestErrorHandler.RestErrorResponseModel fromBindingErrors(BindingResult bindingResult) {

        final List<String> m = bindingResult.getFieldErrors().stream()
                .map(x -> MessageFormat.format("`{0}` {1}",
                        x.getField(), x.getDefaultMessage()))
                .collect(Collectors.toList());

        return new RestErrorHandler.RestErrorResponseModel(RestErrorHandler.REQUEST_VALIDATION_ERROR,"Values entered are invalid",m);

    }
}
