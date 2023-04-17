package com.techotekkie.hotel.HotelServices.services.impl;

import com.techotekkie.hotel.HotelServices.entities.Hotel;
import com.techotekkie.hotel.HotelServices.exceptions.ResourceNotFoundException;
import com.techotekkie.hotel.HotelServices.repositories.HotelRepository;
import com.techotekkie.hotel.HotelServices.services.HotelServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelServices {
   @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given id not found!!"));
    }
}
