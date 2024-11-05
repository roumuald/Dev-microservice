package com.utilisateur_service;

import com.utilisateur_service.entity.Utilisateur;
import com.utilisateur_service.enumeration.DEL_YN;
import com.utilisateur_service.enumeration.Role;
import com.utilisateur_service.repository.UtilisateurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class UtilisateurServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtilisateurServiceApplication.class, args);
	}

	//@Bean
	CommandLineRunner start(UtilisateurRepository utilisateurRepository){
		return args -> {
			Utilisateur u1 = utilisateurRepository.save(Utilisateur.builder()
					.lastName("nnr").role(Role.ETUDIANT).email("nnr@gmail.com")
					.birthDate(LocalDate.now()).del_yn(DEL_YN.N).password("nnr").build());
		};
	}
}
