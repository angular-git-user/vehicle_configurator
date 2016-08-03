package app.entityClasses;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.*;

@Entity
public class Model {
	
	private int id;
	private String modelName;
	private Manufacturer manufacturer;
	private Set<ModelManufacturerMapper> modelManufMapper = new HashSet<ModelManufacturerMapper>();
	
	public Model(String modelName) {
		super();
		this.modelName = modelName;
	}

	public Model() {
		super();
	}

	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(nullable = false, name = "MANUF_ID")
	//@Fetch(FetchMode.JOIN)
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name = "MODEL_ID")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	@Column(name = "model")
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "modelMapper")
	//@Fetch(FetchMode.JOIN)
	public Set<ModelManufacturerMapper> getModelManufMapper() {
		return modelManufMapper;
	}

	public void setModelManufMapper(Set<ModelManufacturerMapper> modelManufMapper) {
		this.modelManufMapper = modelManufMapper;
	}
	
	
}
