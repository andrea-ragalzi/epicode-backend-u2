package com.ragalzi.day2.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HelpService {
    public String getHelpText(String lang) throws IOException {
        return loadHelpText(lang);
    }

    private String loadHelpText(String lang) throws IOException {
        // Carica il file JSON per la lingua specificata dal classpath
        String filename = "json/help/help_" + lang + ".json";
        try (InputStream inputStream = new ClassPathResource(filename).getInputStream();
                Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A")) {
            String json = scanner.next();

            // Parsa il contenuto JSON in un oggetto JsonNode
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);

            // Estrai il titolo e le istruzioni dal JsonNode
            String title = jsonNode.get("title").asText();
            String[] instructions = objectMapper.treeToValue(jsonNode.get(
                    "instructions"), String[].class);

            // Costruisci il testo della pagina di aiuto
            StringBuilder sb = new StringBuilder();
            sb.append("<h1>").append(title).append("</h1>");
            sb.append("<ol>");
            for (String instruction : instructions) {
                sb.append("<li>").append(instruction).append("</li>");
            }
            sb.append("</ol>");

            return sb.toString();
        }
    }
}
