package app.entityClasses;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.*;

@Entity
@Table(name = "Feature_Types")
public class FeatureTypes {

	private int id;
	private EFeatures feature;
	private Set<SubFeatures> subFeatures = new HashSet<SubFeatures>();
	
	public FeatureTypes(){
		
	}
	
	public FeatureTypes(EFeatures stdFeat) {
		this.feature = stdFeat;
	}
	
	@Id
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	@Column(name = "feature_id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(nullable = false, name = "feature_name")
	@Enumerated(EnumType.STRING)
	public EFeatures getFeature() {
		return feature;	
	}
	public void setFeature(EFeatures feature) {
		this.feature = feature;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "feature")
	public Set<SubFeatures> getSubFeatures() {
		return subFeatures;
	}

	public void setSubFeatures(Set<SubFeatures> subFeatures) {
		this.subFeatures = subFeatures;
	}

	@Override
	public String toString() {
		return "FeatureTypes [id=" + id + ", feature=" + feature + "]";
	}
}
