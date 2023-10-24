package com.example.appli.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appli.model.Joueur;
import com.example.appli.repository.JoueurRepository;

import lombok.Data;

@Data
@Service
public class JoueurService {
    @Autowired
    JoueurRepository joueurRepository;

    // pour récupérer tout les joueurs
    public Iterable<Joueur> getJoueurs() {
        return joueurRepository.getAllJoueurs();
    }

    //Pour récupérer un seul joueur
    public Joueur getJoueur(long id) {
        return joueurRepository.getJoueurById(id);
    }

    public Joueur saveJoueur(Joueur joueur) {
        return joueurRepository.addJoueur(joueur);
    }

    // Supprime une joueur
    public boolean deleteJoueur(long id) {
        return joueurRepository.deleteJoueur(id);
    }

    // Modifie un joueur
    public Joueur updateJoueur(Joueur joueur) {
        return joueurRepository.updateJoueur(joueur);
    }
}