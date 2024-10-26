package com.cours_service.repository;

import com.cours_service.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
    public Cours findBycourseName(String courseName);
}
