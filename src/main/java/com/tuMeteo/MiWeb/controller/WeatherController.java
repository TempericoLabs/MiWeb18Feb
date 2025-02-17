package com.tuMeteo.MiWeb.controller;

import com.tuMeteo.MiWeb.model.Weather;
import com.tuMeteo.MiWeb.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller  // Cambiamos @RestController por @Controller
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("weather", new Weather());
        return "form";
    }

    @GetMapping("/list")
    public String listWeather(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Double minTemp,
            @RequestParam(required = false) Double maxTemp,
            Model model) {

        List<Weather> weatherList;
        if (city != null || minTemp != null || maxTemp != null) {
            weatherList = weatherRepository.searchWeather(city, minTemp, maxTemp);
        } else {
            weatherList = weatherRepository.findAll();
        }

        model.addAttribute("weatherList", weatherList);
        model.addAttribute("city", city);
        model.addAttribute("minTemp", minTemp);
        model.addAttribute("maxTemp", maxTemp);
        return "list";
    }

    @GetMapping("/delete/{id}")
    public String deleteWeather(@PathVariable Long id) {
        weatherRepository.deleteById(id);
        return "redirect:/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid weather Id:" + id));
        model.addAttribute("weather", weather);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String updateWeather(@PathVariable Long id, Weather weather) {
        weather.setId(id);
        weatherRepository.save(weather);
        return "redirect:/list";
    }

    @PostMapping("/save")
    public String saveWeather(Weather weather) {
        weatherRepository.save(weather);
        return "redirect:/list";
    }
}