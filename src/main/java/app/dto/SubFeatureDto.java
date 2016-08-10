package app.dto;

import java.util.HashSet;
import java.util.Set;

public class SubFeatureDto {
	
	private int id;	
	private String subFeatureName;
	private Set<FeatureManufacturerDto> featureManufacturers = new HashSet<FeatureManufacturerDto>();
	private FeatureTypesDto fetureType;
	
	public FeatureTypesDto getFetureType() {
		return fetureType;
	}
	public void setFetureType(FeatureTypesDto fetureType) {
		this.fetureType = fetureType;
	}
	public int getId() {
		return id;
	} 
	public void setId(int id) {   
		this.id = id;
	}
	public String getSubFeatureName() {
		return subFeatureName;
	}
	public void setSubFeatureName(String subFeatureName) {
		this.subFeatureName = subFeatureName;
	}
	
	public SubFeatureDto(int id, String subFeatureName) {
		super();
		this.id = id;
		this.subFeatureName = subFeatureName;
	}
	
	public Set<FeatureManufacturerDto> getFeatureManufacturers() {
		return featureManufacturers;
	}
	public void setFeatureManufacturers(Set<FeatureManufacturerDto> featureManufacturers) {
		this.featureManufacturers = featureManufacturers;
	}
	public SubFeatureDto() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subFeatureName == null) ? 0 : subFeatureName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubFeatureDto other = (SubFeatureDto) obj;
		if (subFeatureName == null) {
			if (other.subFeatureName != null)
				return false;
		} else if (!subFeatureName.equals(other.subFeatureName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SubFeatureDto [id=" + id + ", subFeatureName=" + subFeatureName + "]";
	}
	
}
