package com.example.appli.repository;

// import java.io.ObjectInputStream.GetField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.appli.configuration.CustomProperties;
import com.example.appli.model.Joueur;

@Component
public class JoueurRepository {
    @Autowired
    CustomProperties properties;

     String baseUrlApi;

     public JoueurRepository(CustomProperties customProperties) {
        properties = customProperties;
        baseUrlApi = properties.getApiURL();
     }

    // Récupérer tout nos joueurs
    public Iterable<Joueur> getAllJoueurs() {
        // String baseUrlApi = properties.getApiURL();
        String getPlayerUrl = baseUrlApi + "/players";

        // L'objet de la classe RestTemplate fait des requetes HTTP et convertit le JSON retourné par la requête java.
        RestTemplate RestTemplate = new RestTemplate();
        ResponseEntity<Iterable<Joueur>> reponse = RestTemplate.exchange(
            getPlayerUrl, 
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<Iterable<Joueur>>() {}
            );

            return reponse.getBody();
    }

    // Récupérer un joueur par son id
    public Joueur getJoueurById(long id) {
        String getPlayerUrl = baseUrlApi + "/player/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Joueur> response = restTemplate.exchange(
            getPlayerUrl,
            HttpMethod.GET,
            null,
            Joueur.class
        );
        return response.getBody();
    }

    // Ajouter un joueur
    public Joueur addJoueur(Joueur e) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Joueur> request = new HttpEntity<Joueur>(e);
        ResponseEntity<Joueur> response = restTemplate.exchange(
            baseUrlApi + "/player", 
            HttpMethod.POST, 
            request, 
            Joueur.class
            );
            return response.getBody();
    }

    // Supprimer un joueur
    // public Boolean deleteJoueur(Joueur e) {

    // }

}