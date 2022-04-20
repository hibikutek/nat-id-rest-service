package org.telesoftas.natidrestservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.InternalParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.telesoftas.NatIDValidator;
import org.telesoftas.Result;
import org.telesoftas.natidrestservice.Models.NationalID;
import org.telesoftas.natidrestservice.Models.NationalIDIndexedEntity;
import org.telesoftas.natidrestservice.Models.ValidationError;
import org.telesoftas.natidrestservice.Repo.NationalIDRepo;
import org.telesoftas.natidrestservice.Repo.ValidationErrorRepo;
import org.telesoftas.natidrestservice.Response.InternalNationalIDParseException;
import org.telesoftas.natidrestservice.Response.NationalIDNotFoundException;
import org.telesoftas.natidrestservice.Response.MalformedNationalIDException;

import java.text.ParseException;
import java.util.List;

@RestController
public class IDController {
    @Autowired
    private NationalIDRepo nationalIDRepo;
    @Autowired
    private ValidationErrorRepo validationErrorRepo;


    @GetMapping(value = "ids/{nationalID}")
    public NationalID getOne(@PathVariable Long nationalID) {
        return nationalIDRepo.findById(nationalID)
                .orElseThrow(() -> new NationalIDNotFoundException(nationalID));
    }

    @GetMapping(value = "/ids")
    public List<NationalID> getAll() {
        return nationalIDRepo.findAll();
    }

    @PostMapping(value = "/ids")
    public NationalIDIndexedEntity saveOne(@RequestBody Long nationalID) {
        Result result = NatIDValidator.validateID(nationalID);
        if (result.isValid()) {
            try {
                return nationalIDRepo.save(new NationalID(nationalID));
            } catch (ParseException e) {
                e.printStackTrace();
                ValidationError validationError = new ValidationError(nationalID)
                        .setErrorMessage("ID could not be parsed")
                        .setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
                validationErrorRepo.save(validationError);
                throw new InternalNationalIDParseException(validationError);

            }
        } else {
            ValidationError validationError = new ValidationError(nationalID)
                    .setErrorMessage(result.getMessage())
                    .setErrorCode(HttpStatus.BAD_REQUEST);
            validationErrorRepo.save(validationError);
            throw new MalformedNationalIDException(validationError);
        }
    }
}
