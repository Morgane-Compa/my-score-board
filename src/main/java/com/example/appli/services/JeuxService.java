package com.example.appli.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appli.model.Jeux;
import com.example.appli.repository.JeuxRepository;

import lombok.Data;

@Data
@Service
public class JeuxService {
    @Autowired
    JeuxRepository jeuxRepository;

    // Pour récupérer tout les jeux
    public Iterable<Jeux> getJeux() {
        return jeuxRepository.getAllJeux();
    }

    // Pour récupérer un seul jeu
    public Jeux getJeu(long id) {
        return jeuxRepository.getJeuById(id);
    }

    // Pour ajouter un jeu
    public Jeux saveJeu(Jeux jeux) {
        return jeuxRepository.addJeu(jeux);
    }

    // Pour supprimer un jeu
    public boolean deleteJeu(long id) {
        return jeuxRepository.deleteJeu(id);
    }

    // Pour modifier un jeu
    public Jeux updateJeu(Jeux j) {
        return jeuxRepository.updateJeu(j);
    }

    // Pour chercher un jeu
    public Iterable<Jeux> searchJeu(String word) {
        return jeuxRepository.searchJeu(word);
    }
}