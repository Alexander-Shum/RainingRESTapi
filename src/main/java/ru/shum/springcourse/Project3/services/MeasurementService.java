package ru.shum.springcourse.Project3.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.shum.springcourse.Project3.models.Measurement;
import ru.shum.springcourse.Project3.repositories.MeasurementRepository;

@Service
@Transactional
public class MeasurementService {
	
	private final MeasurementRepository measurementRepository;
	private final SensorService sencorService;
	
	public MeasurementService(MeasurementRepository measurementRepository,
			SensorService sencorService) {
		this.measurementRepository = measurementRepository;
		this.sencorService = sencorService;
	}
	
	public int countRianyDays() {
		return measurementRepository.findRainyDays();
	}
	
	public List<Measurement> findAll(){
		return measurementRepository.findAll();
	}

	public void save(Measurement measurement) {
		enrichMeasurement(measurement);
		measurementRepository.save(measurement);
	}
	
	private void enrichMeasurement(Measurement measurement) {
		measurement.setCreatedAt(LocalDateTime.now());
		measurement.setSensor(sencorService.findByName(measurement.getSensor().getName()).get());
	}
}
