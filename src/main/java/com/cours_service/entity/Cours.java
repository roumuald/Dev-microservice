package com.cours_service.entity;

import com.cours_service.enumeration.DEL_YN;
import com.cours_service.enumeration.SEMESTRE;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le nom du cours est obligatoire")
    private String courseName;

    private String description;

    private int credits;

    private int nbreHeure;

    @Enumerated(EnumType.STRING)
    private SEMESTRE semestre;

    @Enumerated(EnumType.STRING)
    private DEL_YN del_yn; //Suppression O/N
}
