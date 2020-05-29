package io.anonymous.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.anonymous.moviecatalogservice.models.Movie;
import io.anonymous.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/***
 * @author Pawan Maurya
 * @since May 29, 2020
 */
@Service
public class MovieInfo {

    private RestTemplate restTemplate;

    @Autowired
    public MovieInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackMovieInfo")
    public Movie getMovieInfo(Rating rating) {
        return restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
    }

    public Movie getFallbackMovieInfo(Rating rating) {
        System.out.println("Here");
        return new Movie(rating.getMovieId(), "No Movie Found", "No Description Found");
    }
}
