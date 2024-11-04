package com.inscription_service.UtilisateurProxy;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursProxy {

    private Long id;

    @NotNull(message = "Le nom du cours est obligatoire")
    private String courseName;

    private String description;

    @Min(value = 1, message = "Le nombre de crédits doit être au moins de 1")
    private int credits;

    @Min(value = 1, message = "Le nombre d'heures doit être au moins de 1")
    private int nbreHeure;

    private String semestre;

    private String del_yn; //Suppression O/N
}
