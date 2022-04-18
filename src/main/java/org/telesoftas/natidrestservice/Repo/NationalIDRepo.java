package org.telesoftas.natidrestservice.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.telesoftas.natidrestservice.Models.NationalID;

public interface NationalIDRepo extends JpaRepository<NationalID, Long> {
}
