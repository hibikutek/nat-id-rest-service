package org.telesoftas.natidrestservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telesoftas.NatIDValidator;
import org.telesoftas.natidrestservice.Models.NationalID;
import org.telesoftas.natidrestservice.Models.ValidationError;
import org.telesoftas.natidrestservice.Repo.NationalIDRepo;
import org.telesoftas.natidrestservice.Repo.ValidationErrorRepo;

import java.text.ParseException;
import java.util.List;

@RestController
public class IDController {
    @Autowired
    private NationalIDRepo nationalIDRepo;
    @Autowired
    private ValidationErrorRepo validationErrorRepo;


    @GetMapping(value = "/ids")
    public List<NationalID> getNationalIDs() {
        return nationalIDRepo.findAll();
    }

    @PostMapping(value = "/id")
    public String saveNationalID(@RequestBody Long nationalID) {
        if (NatIDValidator.validateID(nationalID)) {
            try {
                nationalIDRepo.save(new NationalID(nationalID));
                return "Saved...";
            } catch (ParseException e) {
                e.printStackTrace();
                validationErrorRepo.save(new ValidationError(nationalID));
                return "Error: ID could not be parsed";
            }
        } else {
            validationErrorRepo.save(new ValidationError(nationalID));
            return "Error: ID is not valid";
        }
    }
}
