package com.fscordeiro.registrationClient.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class LegalEntity extends Client {

    @Column(unique = true)
    private String cnpj;

    private String responsibleCpf;

    @OneToMany(mappedBy = "legalEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LegalEntityAddress> addresses  = new ArrayList<>();

}
