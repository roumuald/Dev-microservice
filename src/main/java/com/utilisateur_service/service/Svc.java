package com.utilisateur_service.service;

import com.utilisateur_service.entity.Utilisateur;
import com.utilisateur_service.entity.Validation;
import com.utilisateur_service.enumeration.DEL_YN;
import com.utilisateur_service.enumeration.Role;
import com.utilisateur_service.repository.UtilisateurRepository;
import com.utilisateur_service.repository.ValidationRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Gérer les informations des Utilisateur
 * (ajouter, modifier, supprimer, afficher).
 * @author nnr
 */

@Service
@AllArgsConstructor
public class Svc {

    private UtilisateurRepository utilisateurRepository;
    private ValidationRepository validationRepository;
    private PasswordEncoder passwordEncoder;
    private JavaMailSender javaMailSender;

    /**
     * Methode pour enregistrer un utilisateur
     * @param utilisateur
     */
    public void insertUtilisateur(Utilisateur utilisateur){
        if (!utilisateur.getEmail().contains("@")) throw new RuntimeException("addresse email invalide");
        if (!utilisateur.getEmail().contains(".")) throw new RuntimeException("addresse email invalide");

        Utilisateur u = utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (u!=null) throw new RuntimeException("L'addresse email>>>>> " + u.getEmail() + ">>>>>>>existe deja");

        utilisateur.setDel_yn(DEL_YN.N);
        utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
        this.validationCompte(utilisateurRepository.save(utilisateur));
    }

    /**
     * Methode avoir le liste d'etudiant
     * @param
     */
    public List<Utilisateur> selectEtudiant(){
        List<Utilisateur> users = new ArrayList<>();
        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();

        if (utilisateurList.isEmpty()){
            throw new RuntimeException("Pas d'utilisateur disponible en base de donnees");
        }else {
            for (Utilisateur utilisateur: utilisateurList){
                if (utilisateur.getRole().equals(Role.ETUDIANT)&&utilisateur.getDel_yn().equals(DEL_YN.N)){
                    users.add(utilisateur);
                }
            }
        }
        return users;
    }

    /**
     * Methode avoir le liste d'enseignant
     * @param
     */
    public List<Utilisateur> selectEnseignant(){
        List<Utilisateur> users = new ArrayList<>();

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        if (utilisateurList.isEmpty()){
            throw new RuntimeException("Pas d'utilisateur disponible en base de donnees");
        }else {
            for (Utilisateur utilisateur: utilisateurList){
                if (utilisateur.getRole().equals(Role.ENSEIGNANT)&&utilisateur.getDel_yn().equals(DEL_YN.N)){
                    users.add(utilisateur);
                }
            }
        }
        return users;
    }

    /**
     * Methode avoir le liste de directeur
     * @param
     */
    public List<Utilisateur> selectDirecteur(){
        List<Utilisateur> users = new ArrayList<>();

        List<Utilisateur> utilisateurList = utilisateurRepository.findAll();
        if (utilisateurList.isEmpty()){
            throw new RuntimeException("Pas d'utilisateur disponible en base de donnees");
        }else {
            for (Utilisateur utilisateur: utilisateurList){
                if (utilisateur.getRole().equals(Role.DIRECTEUR)&&utilisateur.getDel_yn().equals(DEL_YN.N)){
                    users.add(utilisateur);
                }
            }
        }
        return users;
    }

    /**
     * Methode de mise a jour d'un utilisateur
     * @param utilisateur
     * @param id
     */
    public Utilisateur updateUtilisateur(Utilisateur utilisateur, Long id){
        Optional<Utilisateur> u = utilisateurRepository.findById(id);
        if (u.isEmpty()){
            throw new RuntimeException("Cet utilisateur n'existe pas !!!");
        }else {
            u.get().setId(utilisateur.getId());
            u.get().setEmail(utilisateur.getEmail());
            u.get().setRole(utilisateur.getRole());
            //u.get().setPassword(utilisateur.getPassword());
            u.get().setLastName(utilisateur.getLastName());
            u.get().setBirthDate(utilisateur.getBirthDate());
            u.get().setDel_yn(DEL_YN.N);
        }
        Utilisateur user = utilisateurRepository.findByEmail(u.get().getEmail());
        if (user!=null) throw new RuntimeException("L'email "+ u.get().getEmail()+ "existe deja !!!");
        user = utilisateurRepository.save(u.get());
        return user ;
    }

    /**
     * Methode de suppression d'un utilisateur
     * @param id
     */
    public void deleteUtilisateur(Long id){
        Optional<Utilisateur> u = utilisateurRepository.findById(id);
        if (u.isEmpty()){
            throw new RuntimeException("Cet utilisateur n'existe pas !!!");
        }else {
            u.get().setDel_yn(DEL_YN.Y);
            utilisateurRepository.save(u.get());
        }
    }

    /**
     * Detail sur un utilisateur
     * @param email
     */
    public Utilisateur selectOneUtilisateur(String email){
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if (utilisateur==null) throw new RuntimeException("Aucun utilisateur avec l'email "+ email);
        return utilisateur;
    }

    /**
     * Detail sur un utilisateur
     * @param id
     */
    public Utilisateur selectUtilisateurById(Long id){
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        if (utilisateur.isEmpty()) throw new RuntimeException("Aucun utilisateur avec l'indentifiant>>>>>> "+ id);
        return utilisateur.orElse(null);
    }

    public Utilisateur selectEtudiantById(Long id){
        List<Utilisateur> utilisateurs =this.selectEtudiant();
        for (Utilisateur u:utilisateurs){
            if (id.equals(u.getId())){
                return u;
            }
        }
        return null;
    }

    /**
     * methode de validation de compte
     * @param utilisateur
     * @autho nnr
     */

    public void validationCompte(Utilisateur utilisateur){
        Validation validation = new Validation();

        validation.setUtilisateur(utilisateur);
        Instant creation = Instant.now();
        validation.setCreation(creation);
        Instant expiration = creation.plus(10, ChronoUnit.MINUTES);
        validation.setExpiration(expiration);

        Random random = new Random();
        int number= random.nextInt(9999);
        String code = String.format("%06d",number);
        validation.setCode(code);
        this.envoidMail(validationRepository.save(validation));
    }

    /**
     * methode envoi de mail
     * @param validation
     *
     */
    public void envoidMail(Validation validation){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nnr@gmail.com");
        message.setTo(validation.getUtilisateur().getEmail());
        message.setSubject("votre code d'activation");

        String msg = String.format("bonjour , %s <br> votre code d'activation est. %s A bientot", validation.getUtilisateur().getLastName(), validation.getCode());
        message.setText(msg);

        javaMailSender.send(message);

    }
}
