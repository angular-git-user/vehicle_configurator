package app.entityClasses;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.*;

@Entity
@Table(name= "Mapper_Model_Manufacturer")
public class ModelManufacturerMapper {

	private int id;
	private Model modelMapper;
	private FeatureManufacturers manufacturerMapper;
	private Boolean isDefault;
	
	public ModelManufacturerMapper(Model modelMapper, FeatureManufacturers manufacturerMapper,
			Boolean isDefault) {
		super();
		this.modelMapper = modelMapper;
		this.manufacturerMapper = manufacturerMapper;
		this.isDefault = isDefault;
	}
	
	public ModelManufacturerMapper() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name = "MAPPER_ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "MODEL_ID")
	public Model getModelMapper() {
		return modelMapper;
	}
	public void setModelMapper(Model modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "Manufacturer_id")
	public FeatureManufacturers getManufacturerMapper() {
		return manufacturerMapper;
	}
	public void setManufacturerMapper(FeatureManufacturers manufacturerMapper) {
		this.manufacturerMapper = manufacturerMapper;
	}
	
	@Column(name = "Default_Feature")
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
}
