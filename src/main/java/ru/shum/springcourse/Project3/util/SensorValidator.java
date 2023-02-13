package ru.shum.springcourse.Project3.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.shum.springcourse.Project3.models.Sensor;
import ru.shum.springcourse.Project3.services.SensorService;

@Component
public class SensorValidator implements Validator {

	private final SensorService sencorService;
	
	public SensorValidator(SensorService sencorService) {
		this.sencorService = sencorService; 
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Sensor.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Sensor sencor = (Sensor) target;
		if(sencorService.findByName(sencor.getName()).isPresent()) 
			errors.rejectValue("name", "", "Sensor with this name already exists");
		
	}

}
