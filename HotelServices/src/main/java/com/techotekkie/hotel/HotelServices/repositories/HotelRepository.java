package com.techotekkie.hotel.HotelServices.repositories;

import com.techotekkie.hotel.HotelServices.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
