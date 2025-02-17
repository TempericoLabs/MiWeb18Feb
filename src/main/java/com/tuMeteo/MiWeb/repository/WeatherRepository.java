package com.tuMeteo.MiWeb.repository;

import com.tuMeteo.MiWeb.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    // Búsqueda por ciudad (ignorando mayúsculas/minúsculas)
    List<Weather> findByCityContainingIgnoreCase(String city);

    // Búsqueda por rango de temperatura
    List<Weather> findByTemperatureBetween(Double minTemp, Double maxTemp);

    // Búsqueda combinada
    @Query("SELECT w FROM Weather w WHERE (:city is null or lower(w.city) like lower(concat('%',:city,'%'))) " +
            "AND (:minTemp is null or w.temperature >= :minTemp) " +
            "AND (:maxTemp is null or w.temperature <= :maxTemp)")
    List<Weather> searchWeather(
            @Param("city") String city,
            @Param("minTemp") Double minTemp,
            @Param("maxTemp") Double maxTemp);
}