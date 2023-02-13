package ru.shum.springcourse.Project3.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Sensor")
public class Sensor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotEmpty(message = "Name should not be empty")
	@Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "sensor", fetch = FetchType.EAGER)
	private List<Measurement> measurement;

	public List<Measurement> getMeasurement() {
		return measurement;
	}

	public void setMeasurement(List<Measurement> measurement) {
		this.measurement = measurement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
