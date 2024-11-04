package com.inscription_service.UtilisateurProxy;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurProxy {

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

    private String role; // Utiliser String si vous ne pouvez pas importer l'énumération directement

    private String delYn; // Utiliser String ou une autre méthode pour gérer l'énumération DEL_YN

}
