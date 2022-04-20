package org.telesoftas.natidrestservice.Models;

import org.springframework.http.HttpStatus;

import javax.persistence.*;

@Entity
public class ValidationError extends NationalIDIndexedEntity{
    @Id
    private Long nationalID;
    @Column
    private String errorMessage;
    @Column
    private HttpStatus errorCode;

    public ValidationError() {

    }

    public ValidationError(Long nationalID) {
        this.nationalID = nationalID;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ValidationError setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }

    public ValidationError setErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    @Override
    public long getNationalID() {
        return nationalID;
    }

    @Override
    public void setNationalID(long nationalID) {
        this.nationalID = nationalID;
    }
}
