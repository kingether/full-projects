package com.example.service;

import java.util.List;

import com.example.model.City;

public interface ICityService {

    List<City> findAllOrderByPopulationAsc();
    List<City> findAllOrderByNameAsc();
}