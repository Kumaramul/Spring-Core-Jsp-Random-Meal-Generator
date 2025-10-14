package com.example.controller;


import com.example.model.Meal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import com.example.model.MealApiResponse;
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
          
// 
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
            
            
            

          Meal meal = new Meal();
         
          RestTemplate restTemplate =  new RestTemplate();
          MealApiResponse response = restTemplate.getForObject(apiUrl, MealApiResponse.class);
          if (response != null && response.getMeals() != null && !response.getMeals().isEmpty()) {
              model.addAttribute("meal", response.getMeals().get(0));
          }
            


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "jsp/index"; 
    }
}