package ru.shum.springcourse.Project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.shum.springcourse.Project3.models.Measurement;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

	@Query("SELECT COUNT(raining) FROM Measurement WHERE raining=true")
    int findRainyDays();

}
