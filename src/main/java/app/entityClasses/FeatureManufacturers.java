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
import javax.persistence.Table;

import org.hibernate.annotations.*;

@Entity
@Table(name = "Feature_Manufacturers")
public class FeatureManufacturers {
	
	private int id;
	private String manufacturerName;
	private SubFeatures subfeature;
	private Long cost;
	private Set<ModelManufacturerMapper> modelManufMapper = new HashSet<ModelManufacturerMapper>();
	
	@Id
	@Column(name = "Manufacturer_id")
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "Manufacturer_name")
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "Sub_feature_ID", nullable = false)
	public SubFeatures getSubfeature() {
		return subfeature;
	}
	public void setSubfeature(SubFeatures subfeature) {
		this.subfeature = subfeature;
	}

	@Column(name = "Feature_cost")
	public Long getCost() {
		return cost;
	}
	public void setCost(Long cost) {
		this.cost = cost;
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manufacturerMapper")
	@Fetch(FetchMode.JOIN)
	public Set<ModelManufacturerMapper> getModelManufMapper() {
		return modelManufMapper;
	}
	public void setModelManufMapper(Set<ModelManufacturerMapper> modelManufMapper) {
		this.modelManufMapper = modelManufMapper;
	}
	@Override
	public String toString() {
		return "FeatureManufacturers [id=" + id + ", manufacturerName=" + manufacturerName + ", cost=" + cost + "]";
	}
}
