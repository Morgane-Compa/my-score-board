package com.example.appli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // Ajouter un jeu et renvoyer sur une autre page
    // Affiche le formulaire pour ajouter un jeu
    @GetMapping("/jeu/ajouter")
    public String ajouter(Model model) {
        Jeux jeu = new Jeux();
        model.addAttribute("jeu", jeu);
        return "jeux/form";
    }

    // envoie le jeu à la base de donnée 
    @PostMapping("/jeu/ajouter")
    public ModelAndView sauvegarder(@ModelAttribute Jeux jeux) {
        jeuxService.saveJeu(jeux);
        return new ModelAndView("redirect:/jeux");
    }

    // Supprimer un jeu et revoyer sur une autre page
    @GetMapping("/jeu/{id}/supprimer")
    public String supprimer(@PathVariable("id") long id, Model model) {
        Jeux j = jeuxService.getJeu(id);
        model.addAttribute("jeu", j);
        return "jeu/supprimer";
    }

    @PostMapping("/jeu/{id}/supprimer")
    public ModelAndView validerSuprimer(@PathVariable("id") long id) {
        jeuxService.deleteJeu(id);
        return new ModelAndView("redirect:/jeux");
    }

    // Modifier un jeux et renvoyer sur une autre page
    @GetMapping("/jeu/{id}/modifier")
    public String modifier(@PathVariable("id") long id, Model model) {
        Jeux j = jeuxService.getJeu(id);
        model.addAttribute("jeu", j);
        return "jeux/form";
    }

    @PostMapping(value = "/jeu/{id}/modifier")
    public ModelAndView validerModification(@PathVariable Long id, @ModelAttribute Jeux j) {
        j.setId(id);
        jeuxService.updateJeu(j);
        return new ModelAndView("redirect:/jeux");
    }

    // Chercher un jeu
    @Controller
    @RequestMapping("/recherche")
    public class rechercheController {
        @Autowired
        JeuxService jeuxService;

        @GetMapping("/jeu")
        public String rechercheJeu(@RequestParam("search") String word, Model model) {

            /*
             * Dans l'annotation RequestParam, le 1er argument est le nom de la valeur
             * récupérer dans la requête HTTP (par exemple, le 'name' d'un input).
             * Si la variable liée (ici, word) a le même nom que la valeur récupérée (ici,
             * search),
             * on n'a pas besoin de le préciser dans l'annotation.
             */
            Iterable<Jeux> jeux = jeuxService.searchJeu(word);
            model.addAttribute("jeux", jeux);
            return "recherche/jeu";
        }
    }

}