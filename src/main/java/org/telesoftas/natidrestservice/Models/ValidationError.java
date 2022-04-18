package org.telesoftas.natidrestservice.Models;

import javax.persistence.*;

@Entity
public class ValidationError {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long nationalID;
    @Column
    private String errorMessage;
    @Column
    private String errorCode;

    public ValidationError(Long nationalID) {
        this.nationalID = nationalID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNationalID() {
        return nationalID;
    }

    public void setNationalID(long nationalID) {
        this.nationalID = nationalID;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
