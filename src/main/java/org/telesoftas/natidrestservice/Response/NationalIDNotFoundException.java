package org.telesoftas.natidrestservice.Response;

public class NationalIDNotFoundException extends RuntimeException {
    public NationalIDNotFoundException(Long nationalID) {
        super("Could not find National ID: " + nationalID);
    }
}
