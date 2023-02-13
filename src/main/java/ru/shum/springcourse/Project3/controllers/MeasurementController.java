package ru.shum.springcourse.Project3.controllers;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.shum.springcourse.Project3.dto.MeasurementDTO;
import ru.shum.springcourse.Project3.dto.MeasurementsResponse;
import ru.shum.springcourse.Project3.models.Measurement;
import ru.shum.springcourse.Project3.services.MeasurementService;
import ru.shum.springcourse.Project3.util.ErrorResponse;
import ru.shum.springcourse.Project3.util.ErrorUtil;
import ru.shum.springcourse.Project3.util.Exception;
import ru.shum.springcourse.Project3.util.MeasurementValidator;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
	
	private final MeasurementValidator measurementValidator;
	private final MeasurementService measurementService;
	private final ModelMapper modelMapper;
	
	public MeasurementController(MeasurementValidator measurementValidator, MeasurementService measurementService,
			ModelMapper modelMapper) {
		super();
		this.measurementValidator = measurementValidator;
		this.measurementService = measurementService;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping()
	public MeasurementsResponse getMeasurements(){		
		return new MeasurementsResponse(measurementService.findAll().stream()
				.map(this::convertToMeasurementDTO)
				.collect(Collectors.toList()));
	}
	
	@GetMapping("/rainyDaysCount")
	public String getRainyDays() {
		
		return "Number of rainy days: " + measurementService.countRianyDays();
	}

	//Add new measurements
	@PostMapping("/add")
	public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
			BindingResult bindingResult){
		
		Measurement measurement = convertToMeasurement(measurementDTO);
		measurementValidator.validate(measurement, bindingResult);
		
		if(bindingResult.hasErrors()) {
			ErrorUtil.returnErrors(bindingResult);
		}
		
		measurementService.save(measurement);
		
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	//Measurement errors
	@ExceptionHandler
	private ResponseEntity<ErrorResponse> handleException(Exception e){
		ErrorResponse response = new ErrorResponse(e.getMessage(),
				System.currentTimeMillis());
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
		return modelMapper.map(measurement, MeasurementDTO.class);
	}

	private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
		return modelMapper.map(measurementDTO, Measurement.class);
	}
}
