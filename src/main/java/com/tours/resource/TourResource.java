package com.tours.resource;

import com.tours.model.GenericMessage;
import com.tours.model.Tour;
import com.tours.persistence.service.TourService;
import com.tours.repo.ToursRepository;
import com.tours.model.TourFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gardiary on 10/12/18.
 */
@RestController
@RequestMapping("/tours")
public class TourResource {

    @Autowired
    private TourService tourService;

    @RequestMapping(value = "/refresh", method = {RequestMethod.POST})
    public ResponseEntity<GenericMessage> refresh(@RequestBody(required = false) TourFilter filter) {
        List<Tour> tours = filter == null ? ToursRepository.getTours() : ToursRepository.getTours(filter.getFilter());

        if(tours != null) {
            for(Tour tour : tours) {
                Tour existingTour = tourService.getByExternalId(tour.getExternalId());

                if(existingTour == null) {
                    tourService.save(tour);
                }
            }
        }

        return new ResponseEntity<GenericMessage>(new GenericMessage("Tours are refreshed"), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.GET})
    public List<String> tours(@RequestParam(value = "filter", required = false) String filter) {
        //List<Tour> tours = filter == null ? ToursRepository.getTours() : ToursRepository.getTours(filter);
        List<Tour> tours = filter == null ? tourService.findAll() : tourService.findAll(filter);

        return tours != null ? tours.stream().map(Tour::getName).collect(Collectors.toList()) : null;
    }

    @RequestMapping("/{externalId}")
    public Tour getTour(@PathParam("externalId") Long externalId) {
        return tourService.getByExternalId(externalId);
    }

}
