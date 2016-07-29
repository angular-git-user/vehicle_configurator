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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Segment")
public class Segment {

	private int id;	
	private String segname;
	private Set<Manufacturer> manufacturers = new HashSet<Manufacturer>();


	public Segment(String segname, Set<Manufacturer> manufacturers) {
		super();
		this.segname = segname;
		this.manufacturers = manufacturers;
	}

	public Segment() {
		super();
	}
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="segment")
	@Fetch(FetchMode.JOIN)
	public Set<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(Set<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	@Id
	@Column(name = "SEGMENT_ID")
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSegname() {
		return segname;
	}

	public void setSegname(String segname) {
		this.segname = segname;
	}
	
}
