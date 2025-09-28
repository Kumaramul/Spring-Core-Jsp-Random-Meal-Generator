package com.example.controller;


import com.example.model.Meal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.client.RestTemplate;
//import com.example.model.MealApiResponse;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;




@Controller
public class MealController {

    @GetMapping("/")
    public String home() {
        return "jsp/index"; 
    }

    @GetMapping("/getMeal")
    public String getMeal(Model model) {
        try {
            String apiUrl = "https://www.themealdb.com/api/json/v1/1/random.php";
          
// 1
            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");
            
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder json = new StringBuilder();
            while (scanner.hasNext()) {
                json.append(scanner.nextLine());
            }
            scanner.close();
            
//          String json = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
//
            
// 2
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json.toString());
            
//         MealApiResponse response = mapper.readValue(json, MealApiResponse.class);
            
            JsonNode mealNode = root.path("meals").get(0);

            Meal meal = new Meal();
            meal.setStrMeal(mealNode.path("strMeal").asText());
            meal.setStrMealThumb(mealNode.path("strMealThumb").asText());
            meal.setStrSource(mealNode.path("strSource").asText(null));
            meal.setStrYoutube(mealNode.path("strYoutube").asText(null));

            model.addAttribute("meal", meal);
            
//          RestTemplate restTemplate =  new RestTemplate();
//          MealApiResponse response = restTemplate.getForObject(apiUrl, MealApiResponse.class);
//          if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
//              model.addAttribute("meal", response.getMeals().get(0));
//          }
            
 //

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "jsp/index"; 
    }
}