package ru.shum.springcourse.Project3.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class MeasurementDTO {

	@NotNull
	@Min(value = -100, message = "value should be more them -100")
	@Max(value = 100, message = "value should be less them 100")
	private Double value;
	
	@NotNull
	private Boolean raining;
	
	@NotNull
	private SensorDTO sensor;

	public SensorDTO getSensor() {
		return sensor;
	}

	public void setSensor(SensorDTO sensor) {
		this.sensor = sensor;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Boolean getRaining() {
		return raining;
	}

	public void setRaining(Boolean raining) {
		this.raining = raining;
	}

	public boolean isRaining() {
		return raining;
	}

	public void setRaining(boolean raining) {
		this.raining = raining;
	}
	
	
}
