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
public class Individual extends Client {

    @Column(unique = true)
    private String cpf;

    @OneToMany(mappedBy = "individual", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IndividualAddress> addresses  = new ArrayList<>();
}
