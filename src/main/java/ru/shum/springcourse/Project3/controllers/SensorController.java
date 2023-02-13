package ru.shum.springcourse.Project3.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.shum.springcourse.Project3.dto.SensorDTO;
import ru.shum.springcourse.Project3.models.Sensor;
import ru.shum.springcourse.Project3.services.SensorService;
import ru.shum.springcourse.Project3.util.ErrorResponse;
import ru.shum.springcourse.Project3.util.ErrorUtil;
import ru.shum.springcourse.Project3.util.SensorValidator;

@RestController
@RequestMapping("/sensors")
public class SensorController {
	

	private final SensorService sensorService;
	private final ModelMapper modelMapper;
	private final SensorValidator sensorValidator;

	public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
		super();
		this.sensorService = sensorService;
		this.modelMapper = modelMapper;
		this.sensorValidator = sensorValidator;
	}

	//Add new sensors
	@PostMapping("/registration")
	public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sencorDTO,
			BindingResult bindingResult){
		
		Sensor sensor = convertToSensor(sencorDTO);
		sensorValidator.validate(sensor, bindingResult); //check that name was unique
		
		//check other errors
		if(bindingResult.hasErrors()) {
			ErrorUtil.returnErrors(bindingResult);
		}
		
		sensorService.save(sensor);
		
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	//Sensor errors
	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handleException(Exception e){
		ErrorResponse response = new ErrorResponse(e.getMessage(),
				System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	private Sensor convertToSensor(@Valid SensorDTO sensorDTO) {
		return modelMapper.map(sensorDTO, Sensor.class);
	}
}
