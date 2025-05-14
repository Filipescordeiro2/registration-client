package com.fscordeiro.registrationClient.repository;

import com.fscordeiro.registrationClient.model.LegalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegalEntityRepository extends JpaRepository<LegalEntity, Long> {
    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);

}
