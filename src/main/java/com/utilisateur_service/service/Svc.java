package com.utilisateur_service.service;

import com.utilisateur_service.entity.Utilisateur;
import com.utilisateur_service.enumeration.DEL_YN;
import com.utilisateur_service.enumeration.Role;
import com.utilisateur_service.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Gérer les informations des Utilisateur
 * (ajouter, modifier, supprimer, afficher).
 * @author nnr
 */

@Service
@AllArgsConstructor
public class Svc {

    private UtilisateurRepository utilisateurRepository;

    /**
     * Methode pour enregistrer un utilisateur
     * @param utilisateur
     */
    public Utilisateur insertUtilisateur(Utilisateur utilisateur){
        Utilisateur u = utilisateurRepository.findByEmail(utilisateur.getEmail());
        if (u!=null) {
            throw new RuntimeException("L'addresse email>>>>> " + u.getEmail() + ">>>>>>>existe deja");
        }else {
            utilisateur.setDel_yn(DEL_YN.N);
            u = utilisateurRepository.save(utilisateur);
        }
        return u;
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
            u.get().setPassword(utilisateur.getPassword());
            u.get().setFirstName(utilisateur.getFirstName());
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
}
