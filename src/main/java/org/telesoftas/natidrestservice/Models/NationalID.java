package org.telesoftas.natidrestservice.Models;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Date birthDate;

    public NationalID() {

    }

    public NationalID(long nationalID) throws ParseException {
        this.nationalID = nationalID;
        this.gender = parseGender(nationalID);
        this.birthDate = parseBirthDate(nationalID);

    }

    private Date parseBirthDate(long nationalID) throws ParseException {
        String strDate = String.valueOf(nationalID).substring(1, 7);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        dateFormat.setLenient(false);
        return dateFormat.parse(strDate);
    }

    private @NotNull String parseGender(long nationalID) {
        int genderDigit = Character.getNumericValue(String.valueOf(nationalID).charAt(0));
        if (genderDigit == 3 || genderDigit == 5) {
            return "male";
        }
        return "female";
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
