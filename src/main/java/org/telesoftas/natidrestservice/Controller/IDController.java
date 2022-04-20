package org.telesoftas.natidrestservice.Controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.telesoftas.NatIDValidator;
import org.telesoftas.Result;
import org.telesoftas.natidrestservice.Models.NationalID;
import org.telesoftas.natidrestservice.Models.NationalIDIndexedEntity;
import org.telesoftas.natidrestservice.Models.ValidationError;
import org.telesoftas.natidrestservice.Repo.NationalIDRepo;
import org.telesoftas.natidrestservice.Repo.ValidationErrorRepo;
import org.telesoftas.natidrestservice.Response.NationalIDFileIOException;
import org.telesoftas.natidrestservice.Response.NationalIDNotFoundException;
import org.telesoftas.natidrestservice.Response.MalformedNationalIDException;

import java.io.*;
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
        return saveEntityMap(nationalIDList);
    }

    @PostMapping(value = "idfile")
    public Map<Result, NationalIDIndexedEntity> saveAllFile(@RequestParam("file") @NotNull MultipartFile idFile) {
        try (BufferedReader stream = new BufferedReader(new InputStreamReader(idFile.getInputStream()))) {
            List<Long> nationalIDs = null;
            for (String line: stream.lines().toList()) {
                try{
                    nationalIDs.add(Long.parseLong(line));
                } catch (NumberFormatException ignored) {
                }
            }
            return saveEntityMap(nationalIDs);
        } catch (IOException e) {
            e.printStackTrace();
            throw new NationalIDFileIOException();
        } catch (NumberFormatException ignored) {
        }
        return null;
    }


    @NotNull
    private Map<Result, NationalIDIndexedEntity> saveEntityMap(@NotNull List<Long> nationalIDList) {

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
