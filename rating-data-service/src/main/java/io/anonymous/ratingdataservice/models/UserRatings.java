package io.anonymous.ratingdataservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/***
 * @author Pawan Maurya
 * @since May 28, 2020
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRatings {
    private List<Rating> ratings;
}
