package org.telesoftas.natidrestservice.Response;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NationalIDAdvice {

    @ResponseBody
    @ExceptionHandler(NationalIDNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String nationalIDNotFoundHandler(@NotNull NationalIDNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MalformedNationalIDException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String malformedNationalIDHandler(@NotNull MalformedNationalIDException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InternalNationalIDParseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String internalNationalIDParseHandler(@NotNull InternalNationalIDParseException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NationalIDFileIOException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String nationalIDFileIOHandler(@NotNull NationalIDFileIOException e) {
        return e.getMessage();
    }
}
