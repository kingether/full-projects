package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.City;
import com.example.repository.CityRepository;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository repository;

    
    public List<City> findAll(){
    	return (List<City>)this.repository.findAll();
    }
    @Override
    public List<City> findAllOrderByPopulationAsc() {
        return repository.findAllOrderByPopulationAsc();
    }

    @Override
    public List<City> findAllOrderByNameAsc() {

        var sort = Sort.by(Sort.Direction.ASC, "name");
        return repository.findAllOrderByNameAsc(sort);
    }
}
