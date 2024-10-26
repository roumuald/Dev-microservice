package com.cours_service.svc;

import com.cours_service.entity.Cours;
import com.cours_service.enumeration.DEL_YN;
import com.cours_service.repository.CoursRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Svc {

    private CoursRepository coursRepository;

    /**
     * Methode pour enregistrer un cours
     * @param cours
     */
    public Cours insertCours(Cours cours){
        Cours cours1 = coursRepository.findBycourseName(cours.getCourseName());
        if (cours1!=null) {
            throw new RuntimeException("Le cours: " + cours1.getCourseName() + "existe deja");
        }else {
            cours.setDel_yn(DEL_YN.N);
            cours1= coursRepository.save(cours);
        }
        return cours1;
    }

    /**
     * Methode avoir la liste de cours
     * @param
     */
    public List<Cours> selectCours(){
        List<Cours> cours = new ArrayList<>();
        List<Cours> coursList = coursRepository.findAll();
        if (coursList.isEmpty()) throw new RuntimeException("Pas de cours disponible en base de donnees");
        for (Cours c: coursList){
            if (c.getDel_yn().equals(DEL_YN.N)){
                cours.add(c);
            }
        }
        return cours;
    }

    /**
     * Methode de mise a jour d'un cours
     * @param cours
     * @param id
     */
    public Cours updateCours(Cours cours, Long id){
        Optional<Cours> coursOptional = coursRepository.findById(id);
        if (coursOptional.isEmpty()){
            throw new RuntimeException("Ce cours n'existe pas !!!");
        }else {
            coursOptional.get().setId(cours.getId());
            coursOptional.get().setCourseName(cours.getCourseName());
            coursOptional.get().setDescription(cours.getDescription());
            coursOptional.get().setCredits(cours.getCredits());
            coursOptional.get().setNbreHeure(cours.getNbreHeure());
            coursOptional.get().setSemestre(cours.getSemestre());
        }
        Cours c = coursRepository.findBycourseName(coursOptional.get().getCourseName());
        if (c!=null) throw new RuntimeException("Le cours "+ coursOptional.get().getCourseName()+ "existe deja !!!");
        c = coursRepository.save(coursOptional.get());
        return c ;
    }

    /**
     * Methode de suppression d'un cours
     * @param id
     */
    public void deleteCours(Long id){
        Optional<Cours> u = coursRepository.findById(id);
        if (u.isEmpty()){
            throw new RuntimeException("Ce cours n'existe pas !!!");
        }else {
            u.get().setDel_yn(DEL_YN.Y);
            coursRepository.save(u.get());
        }
    }

    /**
     * Detail sur un cours
     * @param courseName
     */
    public Cours selectCoursByCourseName(String courseName){
        Cours cours = coursRepository.findBycourseName(courseName);
        if (cours==null) throw new RuntimeException("Aucun cours avec le nom "+ courseName);
        return cours;
    }

    /**
     * Detail sur un cours
     * @param id
     */
    public Cours selectCoursById(Long id){
        Optional<Cours> cours = coursRepository.findById(id);
        if (cours.isEmpty()) throw new RuntimeException("Aucun cours avec l'identifiant "+ id);
        return cours.orElse(null);
    }
}
