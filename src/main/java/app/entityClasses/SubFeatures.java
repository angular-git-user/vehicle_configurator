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
@Table(name = "Sub_Features")
public class SubFeatures {
	
	private int id;	
	private String subFeatureName;
	FeatureTypes feature;
	private Set<FeatureManufacturers> manufactureresOfFeature = new HashSet<FeatureManufacturers>();

	@Id
	@Column(name = "Sub_feature_ID")
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "SUB_FEATURE_NAME")
	public String getSubFeatureName() {
		return subFeatureName;
	}
	public void setSubFeatureName(String subFeatureName) {
		this.subFeatureName = subFeatureName;
	}

	@OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "subfeature")
	@Fetch(FetchMode.JOIN)
	public Set<FeatureManufacturers> getManufactureresOfFeature() {
		return manufactureresOfFeature;
	}

	public void setManufactureresOfFeature(Set<FeatureManufacturers> manufactureresOfFeature) {
		this.manufactureresOfFeature = manufactureresOfFeature;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "feature_id", nullable = false)
	public FeatureTypes getFeature() {
		return feature;
	}

	public void setFeature(FeatureTypes feature) {
		this.feature = feature;
	}
}
