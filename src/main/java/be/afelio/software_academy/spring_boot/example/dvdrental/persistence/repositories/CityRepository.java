package be.afelio.software_academy.spring_boot.example.dvdrental.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.afelio.software_academy.spring_boot.example.dvdrental.persistence.entities.CityEntity;

public interface CityRepository extends JpaRepository<CityEntity, Integer>{

	CityEntity findOneByNameAndCountryName(String cityName, String countryName);
}
