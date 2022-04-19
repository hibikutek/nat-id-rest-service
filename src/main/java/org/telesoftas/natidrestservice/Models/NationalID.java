package org.telesoftas.natidrestservice.Models;

import org.telesoftas.validators.Utils;

import javax.persistence.*;
import java.text.ParseException;
import java.time.LocalDate;

@Entity
public class NationalID {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long nationalID;
    @Column
    private String gender;
    @Column
    private LocalDate birthDate;

    public NationalID() {

    }

    public NationalID(long nationalID) throws ParseException {
        this.nationalID = nationalID;
        this.gender = Utils.parseGender(nationalID);
        this.birthDate = Utils.parseBirthDate(nationalID);

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
