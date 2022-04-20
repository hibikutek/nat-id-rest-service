package org.telesoftas.natidrestservice.Response;

import org.telesoftas.natidrestservice.Models.ValidationError;

public class InternalNationalIDParseException extends RuntimeException {
    public InternalNationalIDParseException(ValidationError validationError) {
        super(validationError.getErrorMessage());
    }
}
