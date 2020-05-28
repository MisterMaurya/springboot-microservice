package io.anonymous.ratingdataservice.resourse;

import io.anonymous.ratingdataservice.models.Rating;
import io.anonymous.ratingdataservice.models.UserRatings;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/***
 * @author Pawan Maurya
 * @since May 28, 2020
 */

@RestController
@RequestMapping("/rating")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable String movieId) {
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    UserRatings getUserRatings(@PathVariable String userId) {
        return new UserRatings(Arrays.asList(
                new Rating("1234", 4),
                new Rating("3456", 4)));
    }
}
