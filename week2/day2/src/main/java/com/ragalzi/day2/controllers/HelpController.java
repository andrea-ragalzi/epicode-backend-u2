package com.ragalzi.day2.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ragalzi.day2.services.HelpService;

@Controller
public class HelpController {

    @Autowired
    HelpService helpService;

    @GetMapping("/help/{lang}")
    public String help(@PathVariable String lang, Model model) {
        // Carica il testo della pagina di aiuto dalla lingua specificata
        String helpText;
        try {
            helpText = helpService.getHelpText(lang);
        } catch (IOException e) {
            // In caso di errore, restituisci un messaggio di errore
            helpText = "Language " + lang + " not supported";
        }
        // Aggiungi il testo della pagina di aiuto al modello
        model.addAttribute("helpText", helpText);
        return "help";
    }

}
