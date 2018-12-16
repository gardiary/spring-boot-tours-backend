package com.tours.repo;

import com.tours.model.Tour;
import com.tours.repo.model.RepositoryWrapper;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gardiary on 10/12/18.
 */
public class ToursRepository {
    private final static String repositoryUrl = "https://s3-eu-west-1.amazonaws.com/pocketguide/_test/store_en.v2.gz";

    public static List<Tour> getTours() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
                new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);

        try {
            RepositoryWrapper wrapper = restTemplate.getForObject(repositoryUrl, RepositoryWrapper.class);

            return wrapper.getTours().stream()
                    .map(repoTour -> new Tour(repoTour.getId(), repoTour.getName(), repoTour.getLongDesc()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error:" + e.getMessage());
        }

        return null;
    }

    public static List<Tour> getTours(String filter) {
        List<Tour> tours = getTours();

        if(filter == null) {
            return tours;
        } else {
            return tours != null ?
                    tours.stream().filter(tour -> tour.getName().toLowerCase().contains(filter.toLowerCase())).collect(Collectors.toList()) :
                    null;
        }
    }

}
