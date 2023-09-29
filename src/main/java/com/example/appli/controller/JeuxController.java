package com.example.appli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.appli.model.Jeux;
import com.example.appli.services.JeuxService;


@Controller
public class JeuxController {
    @Autowired
    JeuxService jeuxService;

    // Récupérer les jeux
    @GetMapping("/jeux")
    public String jeux(Model model) {
        Iterable<Jeux> jeux = jeuxService.getJeux();
        model.addAttribute("jeux", jeux);
        return "jeux/games";
    }

    // Récupérer un seul jeux
    @GetMapping("/jeu/{id}/fiche")
    public String fiche(@PathVariable("id") long id, Model model) {
        Jeux j = jeuxService.getJeu(id);
        model.addAttribute("jeu", j);
        return "jeux/fiche";
    }

    //Ajouter un jeu et renvoyer sur une autre page
        @GetMapping("/jeu/ajouter")
    public String ajouter(Model model) {
        Jeux jeu = new Jeux();
        model.addAttribute("jeu", jeu);
        return "jeux/form";
    }

    @PostMapping("/jeu/ajouter")
    public ModelAndView sauvegarder(@ModelAttribute Jeux jeux) {
        jeuxService.saveJeu(jeux);
        return new ModelAndView("redirect:/jeux");
    }

    
}