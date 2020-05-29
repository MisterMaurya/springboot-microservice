package io.anonymous.moviecatalogservice.resource;

import io.anonymous.moviecatalogservice.models.CatalogItem;
import io.anonymous.moviecatalogservice.models.Movie;
import io.anonymous.moviecatalogservice.models.UserRatings;
import io.anonymous.moviecatalogservice.service.MovieInfo;
import io.anonymous.moviecatalogservice.service.UserRatingsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

/***
 * @author Pawan Maurya
 * @since May 28, 2020
 */

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private WebClient.Builder webClientBuilder;

    private MovieInfo movieInfo;
    private UserRatingsInfo userRatingsInfo;


    @Autowired
    public MovieCatalogResource(WebClient.Builder webClientBuilder, MovieInfo movieInfo, UserRatingsInfo userRatingsInfo) {
        this.webClientBuilder = webClientBuilder;
        this.movieInfo = movieInfo;
        this.userRatingsInfo = userRatingsInfo;
    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable String userId) {
        UserRatings userRatings = userRatingsInfo.getUserRating(userId);
        return userRatings.getRatings().stream().map(rating -> {
            Movie movie = movieInfo.getMovieInfo(rating);
            /*Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/
            return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
        }).collect(Collectors.toList());
    }

}
