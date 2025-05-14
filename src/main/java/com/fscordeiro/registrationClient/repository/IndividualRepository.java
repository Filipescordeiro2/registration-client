package com.fscordeiro.registrationClient.repository;

import com.fscordeiro.registrationClient.model.Individual;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualRepository extends JpaRepository<Individual, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}
