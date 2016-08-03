package app.dto;

public class FeatureManufacturerDto {

	private int id;
	private String manufacturerName;
	private Long cost;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public Long getCost() {
		return cost;
	}
	public void setCost(Long cost) {
		this.cost = cost;
	}
	
	public FeatureManufacturerDto(int id, String manufacturerName, Long cost) {
		super();
		this.id = id;
		this.manufacturerName = manufacturerName;
		this.cost = cost;
	}
	public FeatureManufacturerDto() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((manufacturerName == null) ? 0 : manufacturerName.hashCode());
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
		FeatureManufacturerDto other = (FeatureManufacturerDto) obj;
		if (manufacturerName == null) {
			if (other.manufacturerName != null)
				return false;
		} else if (!manufacturerName.equals(other.manufacturerName))
			return false;
		return true;
	}
	
}
