package com.techotekkie.hotel.HotelServices.services;

import com.techotekkie.hotel.HotelServices.entities.Hotel;

import java.util.List;

public interface HotelServices {

    Hotel create(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String id);
}
