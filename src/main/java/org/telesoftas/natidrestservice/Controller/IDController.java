package org.telesoftas.natidrestservice.Controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.telesoftas.NatIDValidator;
import org.telesoftas.Result;
import org.telesoftas.natidrestservice.Models.NationalID;
import org.telesoftas.natidrestservice.Models.NationalIDIndexedEntity;
import org.telesoftas.natidrestservice.Models.ValidationError;
import org.telesoftas.natidrestservice.Repo.NationalIDRepo;
import org.telesoftas.natidrestservice.Repo.ValidationErrorRepo;
import org.telesoftas.natidrestservice.Response.NationalIDNotFoundException;
import org.telesoftas.natidrestservice.Response.MalformedNationalIDException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class IDController {
    @Autowired
    private NationalIDRepo nationalIDRepo;
    @Autowired
    private ValidationErrorRepo validationErrorRepo;


    @GetMapping(value = "id/{nationalID}")
    public NationalID getOne(@PathVariable Long nationalID) {
        return nationalIDRepo.findById(nationalID)
                .orElseThrow(() -> new NationalIDNotFoundException(nationalID));
    }

    @GetMapping(value = "/ids")
    public List<NationalID> getAll() {
        return nationalIDRepo.findAll();
    }

    @PostMapping(value = "/id")
    public NationalID saveOne(@RequestBody Long nationalID) {
        Result result = NatIDValidator.validateID(nationalID);
        if (result.isValid()) {
            return nationalIDRepo.save(new NationalID(nationalID));
        } else {
            ValidationError validationError = new ValidationError(nationalID)
                    .setErrorMessage(result.getMessage())
                    .setErrorCode(HttpStatus.BAD_REQUEST.value());
            validationErrorRepo.save(validationError);
            throw new MalformedNationalIDException(validationError);
        }
    }

    @PostMapping(value = "/ids")
    public Map<Result, NationalIDIndexedEntity> saveAll(@RequestBody @NotNull List<Long> nationalIDList) {
        Map<Result, NationalIDIndexedEntity> results = new HashMap<>();
        for (Long nationalID : nationalIDList) {
            Result result = NatIDValidator.validateID(nationalID);
            if (result.isValid()) {
                results.put(result, nationalIDRepo.save(new NationalID(nationalID)));

            } else {
                results.put(result, validationErrorRepo.save(new ValidationError(nationalID)
                        .setErrorMessage(result.getMessage())
                        .setErrorCode(HttpStatus.BAD_REQUEST.value())));
            }
        }
        return results;
    }
}
