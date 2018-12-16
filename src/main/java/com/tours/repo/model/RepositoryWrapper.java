package com.tours.repo.model;

import java.util.List;

/**
 * Created by gardiary on 10/12/18.
 */
public class RepositoryWrapper {
    /*private ToursWrapper tours;

    public ToursWrapper getTours() {
        return tours;
    }

    public void setTours(ToursWrapper tours) {
        this.tours = tours;
    }*/

    private List<Tour> tours;

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}
