package com.example.spoontacular.services;

import com.example.spoontacular.model.Gif;
import com.example.spoontacular.model.Wine;
import com.example.spoontacular.model.WineRecommendation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpoontacularService {

    @Value("${spoontacular.api.url}")
    private String apiURL;
    @Value("${spoontacular.api.key}")
    private String key;

    public WineRecommendation getWinePairing(String food) {

        WineRecommendation wineRec = new WineRecommendation();

        String url = this.apiURL + this.key + "&food=" + food;

        HttpEntity<String> httpEntity = new HttpEntity<>("");
        RestTemplate restTemplate = new RestTemplate();

        // These two are new! We'll see how they work.
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        // Code start:


        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            System.out.println(response.getBody());

            jsonNode = objectMapper.readTree(response.getBody());

            // Extract paired wines from the API response and add them to WineRecommendation object
            JsonNode pairedWinesNode = jsonNode.get("pairedWines");
            if (pairedWinesNode.isArray()) {
                List<String> wines = new ArrayList<>();
                for (JsonNode wineNode : pairedWinesNode) {
                    wines.add(wineNode.asText());
                }
                wineRec.setWines(wines);
            }

            // Extract pairing text and set it in WineRecommendation object
            JsonNode pairingTextNode = jsonNode.get("pairingText");
            if (pairingTextNode != null) {
                wineRec.setRecBlurb(pairingTextNode.asText());
            }

            // Extract product matches information and set it in WineRecommendation object
            JsonNode productMatchesNode = jsonNode.get("productMatches");
            if (productMatchesNode.isArray() && productMatchesNode.size() > 0) {
                JsonNode firstProductNode = productMatchesNode.get(0);
                Wine specificRec = objectMapper.treeToValue(firstProductNode, Wine.class);
                wineRec.setSpecificRec(specificRec);
            }



        } catch(JsonProcessingException e) {
            System.out.println("Error: " + e);
        }
        return wineRec;
    }
}

//for(int i=0; i < root.size(); i++) {
//String title = root.path(i).path("title").asText();
//String username = root.path(i).path("username").asText();
//String gifUrl = root.path(i).path("images").path("original").path("url").asText();
//
//Gif gif = new Gif(gifUrl, username, title);
//                gifList.add(gif);
//            }