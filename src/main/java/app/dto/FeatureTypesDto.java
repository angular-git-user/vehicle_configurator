package app.dto;

import java.util.HashSet;
import java.util.Set;

public class FeatureTypesDto {
	
	private int typeId;
	private String featureType;
	Set<SubFeatureDto> subFeatures = new HashSet<SubFeatureDto>();
	
	public Set<SubFeatureDto> getSubFeatures() {
		return subFeatures;
	}
	public void setSubFeatures(Set<SubFeatureDto> subFeatures) {
		this.subFeatures = subFeatures;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getFeatureType() {
		return featureType;
	}
	public void setFeatureType(String featureType) {
		this.featureType = featureType;
	}
	
	public FeatureTypesDto(int typeId, String featureType) {
		super();
		this.typeId = typeId;
		this.featureType = featureType;
	}
	
	public FeatureTypesDto(int typeId, String featureType, Set<SubFeatureDto> subFeatures) {
		super();
		this.typeId = typeId;
		this.featureType = featureType;
		this.subFeatures = subFeatures;
	}
	public FeatureTypesDto() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((featureType == null) ? 0 : featureType.hashCode());
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
		FeatureTypesDto other = (FeatureTypesDto) obj;
		if (featureType == null) {
			if (other.featureType != null)
				return false;
		} else if (!featureType.equals(other.featureType))
			return false;
		return true;
	}
	
	
}
