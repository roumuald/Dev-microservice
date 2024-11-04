package com.inscription_service.controler;

import com.inscription_service.entity.Inscription;
import com.inscription_service.service.Svc;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Crt {

    private Svc svc;

    @PostMapping("/enroll")
    public ResponseEntity<Inscription> enrollUserInCourse(@RequestBody Inscription inscription) {
        Inscription ins = svc.enroll(inscription.getStudentId(), inscription.getCourseId());
        return ResponseEntity.ok(ins);
    }


}
