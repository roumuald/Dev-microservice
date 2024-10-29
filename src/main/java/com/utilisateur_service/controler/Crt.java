package com.utilisateur_service.controler;

import com.utilisateur_service.entity.Utilisateur;
import com.utilisateur_service.service.Svc;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/utilisateur-service")
public class Crt {

    private Svc svc;

    @PostMapping("/insertUtilisateur")
    public ResponseEntity<Utilisateur> insertUtilisateur(@Valid @RequestBody Utilisateur utilisateur){
        Utilisateur u = svc.insertUtilisateur(utilisateur);
        return ResponseEntity.ok(u);
    }

    @GetMapping("/selectEtudiant")
    public ResponseEntity<List<Utilisateur>> selectEtudiant(){
        List<Utilisateur> utilisateurList = svc.selectEtudiant();

        return ResponseEntity.ok(utilisateurList);
    }

    @GetMapping("/selectEnseignant")
    public ResponseEntity<List<Utilisateur>> selectEnseignant(){
        List<Utilisateur> utilisateurList = svc.selectEnseignant();
        return ResponseEntity.ok(utilisateurList);
    }

    @GetMapping("/selectDirecteur")
    public ResponseEntity<List<Utilisateur>> selectDirecteur(){
        List<Utilisateur> utilisateurList = svc.selectDirecteur();
        return ResponseEntity.ok(utilisateurList);
    }

    @GetMapping("/selectOneUtilisateur/{email}")
    public ResponseEntity<Utilisateur> selectOneUtilisateur(@PathVariable String email){
        Utilisateur utilisateur = svc.selectOneUtilisateur(email);
        return ResponseEntity.ok(utilisateur);
    }

    @GetMapping("/selectUtilisateurById/{id}")
    public ResponseEntity<Utilisateur> selectUtilisateurById(@PathVariable Long id){
        Utilisateur utilisateur = svc.selectUtilisateurById(id);
        return ResponseEntity.ok(utilisateur);
    }

    @PutMapping("/updateUtilisateur/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@Valid @RequestBody Utilisateur utilisateur, @PathVariable Long id){
       Utilisateur u = svc.updateUtilisateur(utilisateur, id);
       return ResponseEntity.ok(u);
    }

    @PostMapping("/deleteUtilisateur/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        svc.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
