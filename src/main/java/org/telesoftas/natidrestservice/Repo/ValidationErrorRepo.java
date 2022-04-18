package org.telesoftas.natidrestservice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telesoftas.natidrestservice.Models.ValidationError;

public interface ValidationErrorRepo extends JpaRepository<ValidationError, Long> {
}
