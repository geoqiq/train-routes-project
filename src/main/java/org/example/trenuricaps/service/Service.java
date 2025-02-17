package org.example.trenuricaps.service;

import org.example.trenuricaps.domain.Searches;
import org.example.trenuricaps.domain.City;
import org.example.trenuricaps.domain.TrainStation;
import org.example.trenuricaps.repo.CityRepository;
import org.example.trenuricaps.repo.TrainStationRepository;
import org.example.trenuricaps.utils.observer.ImplObservable;

import java.util.ArrayList;
import java.util.List;

public class Service extends ImplObservable {
    private CityRepository cityRepository;
    private TrainStationRepository trainStationRepository;
    private List<Searches> searches = new ArrayList<>();

    public Service(CityRepository cityRepository, TrainStationRepository trainStationRepository) {
        this.cityRepository = cityRepository;
        this.trainStationRepository = trainStationRepository;
    }

    public List<City> getCities() {
        return cityRepository.getAll();
    }
    public List<TrainStation> getTrainStations() {
        return trainStationRepository.getAll();
    }

    public String getNameById(String cityId){
        for(City c: cityRepository.getAll()){
            if(c.getId().equals(cityId))
                return c.getName();
        }
        return null;
    }

    public void addSearch(String userId, String departureId, String destinationId){
        List<Searches> finalSearches = new ArrayList<>();
        for(Searches search: searches){
            if(!search.getUserId().equals(userId))
                finalSearches.add(search);
        }

        Searches c=new Searches(userId,departureId,destinationId);
        searches.clear();
        searches.addAll(finalSearches);
        searches.add(c);

        for(Searches ca: searches){
            System.out.println(ca);
        }
    }

    public Integer nrOfUsers(String from, String to){
        Integer count = 0;
        for(Searches c: searches)
            if(c.getDepartureId().equals(from) && c.getDestinationId().equals(to)) {
                count++;
            }
        return count - 1;
    }
}
