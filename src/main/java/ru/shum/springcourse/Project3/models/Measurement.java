package ru.shum.springcourse.Project3.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "Measurement")
public class Measurement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "value")
	@NotNull
	@Min(value = -100, message = "value should be more them -100")
	@Max(value = 100, message = "value should be less them 100")
	private Double value;
	
	@Column(name = "raining")
	@NotNull
	private Boolean raining;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "sensor_id", referencedColumnName = "id")
	private Sensor sensor;
	
	@Column(name = "time")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private LocalDateTime createdAt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sencor) {
		this.sensor = sencor;
	}
}
