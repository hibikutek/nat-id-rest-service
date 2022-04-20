package org.telesoftas.natidrestservice.Models;

import javax.persistence.*;

@Entity
public class ValidationError implements NationalIDIndexedEntity{
    @Id
    private Long nationalID;
    @Column
    private String errorMessage;
    @Column
    private int errorCode;

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

    public int getErrorCode() {
        return errorCode;
    }

    public ValidationError setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public long getNationalID() {
        return nationalID;
    }

    public void setNationalID(long nationalID) {
        this.nationalID = nationalID;
    }
}
