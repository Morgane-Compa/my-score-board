package com.example.appli.repository;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.client.RestTemplate;

import com.example.appli.configuration.CustomProperties;
import com.example.appli.model.Jeux;


@Component
public class JeuxRepository extends Repository{

    public JeuxRepository(CustomProperties customProperties) {
        properties = customProperties;
        baseUrlApi = properties.getApiURL();
    }

    // Récupérer tout les jeux
    public Iterable<Jeux> getAllJeux() {
        String getGameUrl = baseUrlApi + "/games";

        RestTemplate RestTemplate = new RestTemplate();
        ResponseEntity<Iterable<Jeux>> reponse = RestTemplate.exchange(
                getGameUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Jeux>>() {
                });

        return reponse.getBody();
    }

    // Récupérer un seul jeu
    public Jeux getJeuById(long id) {
        String getGameUrl = baseUrlApi + "/game/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Jeux> reponse = restTemplate.exchange(
                getGameUrl,
                HttpMethod.GET,
                null,
                Jeux.class);
        return reponse.getBody();
    }

    // Ajouter un jeu
    public Jeux addJeu(Jeux j) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Jeux> request = new HttpEntity<Jeux>(j);
        ResponseEntity<Jeux> response = restTemplate.exchange(
                baseUrlApi + "/game",
                HttpMethod.POST,
                request,
                Jeux.class);

        return response.getBody();
    }

    // Supprimer un jeu
    public Boolean deleteJeu(long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response = restTemplate.exchange(
                baseUrlApi + "/game/" + id,
                HttpMethod.DELETE,
                null,
                boolean.class);
        return response.getBody();
    }

    // Modifier un jeu
    public Jeux updateJeu(Jeux j) {
        String url = this.baseUrlApi + "/game/" + j.getId();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Jeux> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<Jeux>(j),
                Jeux.class);
        return response.getBody();
    }

    // chercher un jeu
    public Iterable<Jeux> searchJeu(String word) {
        RestTemplate restTemplate = new RestTemplate();
        // <List> et <Iterable> sont toutes les deux des listes donc on peut mettre les
        // deux dans la ligne du dessous (la seule différence c'est que <Iterable ne
        // peut pas être modifié)
        ResponseEntity<Iterable<Jeux>> response = restTemplate.exchange(
                baseUrlApi + "game/search?word=" + word,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Jeux>>() {}
                );
        return response.getBody();
    }



}