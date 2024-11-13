package com.utilisateur_service.repository;

import com.utilisateur_service.entity.Validation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValidationRepository extends JpaRepository<Validation, Long> {
    public Optional<Validation> findByCode(String code);
}
