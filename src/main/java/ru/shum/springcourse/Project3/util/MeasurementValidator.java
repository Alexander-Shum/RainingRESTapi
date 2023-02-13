package ru.shum.springcourse.Project3.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ru.shum.springcourse.Project3.models.Measurement;
import ru.shum.springcourse.Project3.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

	private final SensorService sensorService;
	
	public MeasurementValidator(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Measurement.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Measurement measurement = (Measurement) target;
		if(measurement.getSensor() == null) {
			return;
		}
		
		if(sensorService.findByName(measurement.getSensor().getName()).isEmpty()) {
			errors.rejectValue("sensor", "", "This sensor is missing");
		}
		
	}

}
