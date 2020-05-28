package io.anonymous.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/***
 * @author Pawan Maurya
 * @since May 28, 2020
 */

@Getter
@Setter
@AllArgsConstructor
public class CatalogItem {
    private String name;
    private String desc;
    private Integer rating;
}
