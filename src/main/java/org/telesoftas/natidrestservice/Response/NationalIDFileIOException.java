package org.telesoftas.natidrestservice.Response;

public class NationalIDFileIOException extends RuntimeException {
    public NationalIDFileIOException() {
        super("File upload was unsuccessful");
    }
}
