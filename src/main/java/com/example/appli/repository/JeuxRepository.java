package com.example.appli.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.appli.configuration.CustomProperties;
import com.example.appli.model.Jeux;
import com.example.appli.model.Joueur;

@Component
public class JeuxRepository {
    @Autowired
    CustomProperties properties;

    String baseUrlApi;

    public JeuxRepository(CustomProperties customProperties) {
        properties = customProperties;
        baseUrlApi = properties.getApiURL();
     }

     //Récupérer tout les jeux
     public Iterable<Jeux> getAllJeux() {
        String getGameUrl = baseUrlApi + "/games";

        RestTemplate RestTemplate = new RestTemplate();
        ResponseEntity<Iterable<Jeux>> reponse = RestTemplate.exchange(
            getGameUrl, 
            HttpMethod.GET, 
            null, 
            new ParameterizedTypeReference<Iterable<Jeux>> () {}
            );

            return reponse.getBody();
     }

     //Récupérer un seul jeu
     public Jeux getJeuById(long id) {
        String getGameUrl = baseUrlApi + "/game/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Jeux> reponse = restTemplate.exchange(
            getGameUrl, 
            HttpMethod.GET, 
            null, 
            Jeux.class
            );
            return reponse.getBody();
     }

     //Ajouter un jeux
     public Jeux addJeu(Jeux j) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Jeux> request = new HttpEntity<Jeux>(j);
        ResponseEntity<Jeux> response = restTemplate.exchange(
            baseUrlApi + "/game",
            HttpMethod.POST, 
            request,
            Jeux.class
            );

            return response.getBody();
     }

     //Supprimer un jeux


}