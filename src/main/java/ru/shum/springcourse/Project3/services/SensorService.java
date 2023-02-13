package ru.shum.springcourse.Project3.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ru.shum.springcourse.Project3.models.Sensor;
import ru.shum.springcourse.Project3.repositories.SensorRepository;

@Service
@Transactional
public class SensorService {
	
	private final SensorRepository sencorRepository;
	
	@Autowired
	public SensorService(SensorRepository sencorRepository) {
		this.sencorRepository = sencorRepository;
	}
	
	public void save(Sensor sencor) {
		sencorRepository.save(sencor);
	}
	
	public Optional<Sensor> findByName(String name) {
		return sencorRepository.findByName(name);
	}
	
	//check exists sensor in db
	public boolean findSencor(int id){
		return sencorRepository.existsSencorById(id);
	}
}
