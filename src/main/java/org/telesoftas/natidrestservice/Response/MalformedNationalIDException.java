package org.telesoftas.natidrestservice.Response;

import org.jetbrains.annotations.NotNull;
import org.telesoftas.natidrestservice.Models.ValidationError;

public class MalformedNationalIDException extends RuntimeException {
    public MalformedNationalIDException(@NotNull ValidationError validationError) {
        super(validationError.getErrorMessage());
    }
}
