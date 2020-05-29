package io.anonymous.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.anonymous.moviecatalogservice.models.UserRatings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/***
 * @author Pawan Maurya
 * @since May 29, 2020
 */

@Service
public class UserRatingsInfo {

    private RestTemplate restTemplate;

    @Autowired
    public UserRatingsInfo(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "user")
    public UserRatings getUserRating(@PathVariable String userId) {
        return restTemplate.getForObject("http://rating-data-service/rating/users/" + userId, UserRatings.class);
    }


    public UserRatings user(@PathVariable String userId) {
        return new UserRatings(Collections.emptyList());
    }


}
