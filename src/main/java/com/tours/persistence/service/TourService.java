package com.tours.persistence.service;

import com.tours.model.Tour;
import com.tours.persistence.dao.TourDao;
import com.tours.persistence.entity.TourEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gardiary on 10/12/18.
 */
@Service
public class TourService {

    @Autowired
    private TourDao tourDao;

    public void save(Tour tour) {
        tourDao.save(toTour(tour));
    }

    public Tour get(Long id) {
        return toTour(tourDao.get(id));
    }

    public Tour getByExternalId(Long externalId) {
        return toTour(tourDao.getByExternalId(externalId));
    }

    public List<Tour> findAll() {
        List<TourEntity> entities = tourDao.findAll();

        return entities != null ? entities.stream().map(this::toTour).collect(Collectors.toList()) : null;
    }

    public List<Tour> findAll(String filterName) {
        List<TourEntity> entities = tourDao.findAll(filterName);

        return entities != null ? entities.stream().map(this::toTour).collect(Collectors.toList()) : null;
    }

    private Tour toTour(TourEntity entity) {
        if(entity == null) {
            return null;
        }

        Tour tour = new Tour();
        tour.setId(entity.getId());
        tour.setExternalId(entity.getExternalId());
        tour.setName(entity.getName());
        tour.setLongDesc(entity.getLongDesc());

        return tour;
    }

    private TourEntity toTour(Tour tour) {
        if(tour == null) {
            return null;
        }

        TourEntity entity = new TourEntity();
        entity.setId(tour.getId());
        entity.setExternalId(tour.getExternalId());
        entity.setName(tour.getName());
        entity.setLongDesc(tour.getLongDesc());

        return entity;
    }


}
