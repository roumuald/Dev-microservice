package com.utilisateur_service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.utilisateur_service.enumeration.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    @NotNull(message = "le nom est obligatoire")
    private String lastName;

    @Email(message = "email invalide")
    private String email;

    @NotBlank(message = "le mot de passe est obligatoire")
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;
}
