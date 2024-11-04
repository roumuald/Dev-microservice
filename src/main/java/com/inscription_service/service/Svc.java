package com.inscription_service.service;

import com.inscription_service.UtilisateurProxy.CoursProxy;
import com.inscription_service.UtilisateurProxy.UtilisateurProxy;
import com.inscription_service.entity.Inscription;
import com.inscription_service.repository.InscriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
//import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class Svc {

    private InscriptionRepository inscriptionRepository;
    private final RestTemplate restTemplate;

    public Inscription enroll(Long userId, Long courseId) {
        ResponseEntity<UtilisateurProxy> userResponse = restTemplate.getForEntity("http://utilisateur-service/selectEtudiantById/" + userId, UtilisateurProxy.class);
        if (userResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Utilisateur non trouvé");
        }

        ResponseEntity<CoursProxy> courseResponse = restTemplate.getForEntity("http://cours-service/selectCoursById/" + courseId, CoursProxy.class);
        if (courseResponse.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Cours non trouvé");
        }

        // Crée l'inscription
        Inscription inscription = new Inscription();
        inscription.setStudentId(userId);
        inscription.setCourseId(courseId);
        inscription.setDateInscription(LocalDateTime.now());
        return inscriptionRepository.save(inscription);
    }


}
