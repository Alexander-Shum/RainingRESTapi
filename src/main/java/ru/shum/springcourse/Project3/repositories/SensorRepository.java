package ru.shum.springcourse.Project3.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.shum.springcourse.Project3.models.Sensor;


@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

	Optional<Sensor> findByName(String name);
	
	boolean existsSencorById(int id);

}
