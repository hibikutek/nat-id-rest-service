package org.telesoftas.natidrestservice.Response;

import org.jetbrains.annotations.NotNull;
import org.telesoftas.natidrestservice.Models.ValidationError;

public class InternalNationalIDParseException extends RuntimeException {
    public InternalNationalIDParseException(@NotNull ValidationError validationError) {
        super(validationError.getErrorMessage());
    }
}
