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
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Manufacturer")
public class Manufacturer {

	public Manufacturer() {
		super();
	}

	public Manufacturer(String manufacturerName, Set<Model> models) {
		super();
		this.manufacturerName = manufacturerName;
		this.models = models;
	}

	private int id;
	private String manufacturerName;
	private Segment segment;
	private Set<Model> models = new HashSet<Model>();
	
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "SEGMENT_ID", nullable = false)
	 @Fetch(FetchMode.JOIN)
	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "manufacturer")
	// @Fetch(FetchMode.JOIN)
	public Set<Model> getModels() {
		return models;
	}

	public void setModels(Set<Model> models) {
		this.models = models;
	}

	@Id
	@Column(name = "MANUF_ID")
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	public int getId() {
		return id;
	}
		
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "manuf_name")
	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	
}
