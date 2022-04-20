package org.telesoftas.natidrestservice.Models;

import org.telesoftas.validators.Utils;

import javax.persistence.*;
import java.text.ParseException;
import java.time.LocalDate;

@Entity
public class NationalID extends NationalIDIndexedEntity {
    @Id
    private Long nationalID;
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

    @Override
    public long getNationalID() {
        return nationalID;
    }

    @Override
    public void setNationalID(long nationalID) {
        this.nationalID = nationalID;
    }
}
