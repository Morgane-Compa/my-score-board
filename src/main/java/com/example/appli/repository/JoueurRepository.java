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
            getPlayerUrl, //url de la requete
            HttpMethod.GET, //la methode
            null, //on envoie les donnée correspondant a un joueur
            new ParameterizedTypeReference<Iterable<Joueur>>() {} //la réponse
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
  public Boolean deleteJoueur(long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(
            baseUrlApi + "/player/" + id, 
            HttpMethod.DELETE, 
            null, 
            boolean.class
            );
            return response.getBody();
    }

    // Modifier un joueur
    public Joueur updateJoueur(Joueur j){
        String url = this.baseUrlApi + "/player/" + j.getId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Joueur> response = restTemplate.exchange(
            url,
            HttpMethod.PUT,
            new HttpEntity<Joueur>(j),
            Joueur.class
        );
        return response.getBody();
    }

}