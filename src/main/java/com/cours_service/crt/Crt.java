package com.cours_service.crt;

import com.cours_service.entity.Cours;
import com.cours_service.svc.Svc;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class Crt {

    private Svc svc;

    @PostMapping("/insertCours")
    public ResponseEntity<Cours> insertCours(@Valid @RequestBody Cours cours){
        Cours c = svc.insertCours(cours);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/selectCours")
    public ResponseEntity<List<Cours>> selectCours(){
        List<Cours> cours = svc.selectCours();
        return ResponseEntity.ok(cours);
    }

    @PutMapping("/updateCours/{id}")
    public ResponseEntity<Cours> updateCours(@Valid @RequestBody Cours cours, @PathVariable Long id){
        Cours c = svc.updateCours(cours, id);
        return ResponseEntity.ok(c);
    }

    @PostMapping("/deleteCours/{id}")
    public ResponseEntity<Void> deleteCours(@PathVariable Long id){
        svc.deleteCours(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/selectCoursByCourseName/{courseName}")
    public ResponseEntity<Cours> selectCoursByCourseName(@PathVariable String courseName){
        Cours cours = svc.selectCoursByCourseName(courseName);
        return ResponseEntity.ok(cours);
    }

    @GetMapping("/selectCoursById/{id}")
    public ResponseEntity<Cours> selectCoursById(@PathVariable Long id){
        Cours cours = svc.selectCoursById(id);
        return ResponseEntity.ok(cours);
    }

}
