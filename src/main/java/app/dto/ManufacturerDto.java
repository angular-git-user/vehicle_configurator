package app.dto;

public class ManufacturerDto {

	private int manufacturerId;
	private String manufacturerName;
	private SegmentDto segment;
	
	
	public ManufacturerDto(int manufacturerId, String manufacturerName, SegmentDto segment) {
		super();
		this.manufacturerId = manufacturerId;
		this.manufacturerName = manufacturerName;
		this.segment = segment;
	}
	
	
	public ManufacturerDto() {
		super();
	}

	public int getManufacturerId() {
		return manufacturerId;
	}
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}
	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public SegmentDto getSegment() {
		return segment;
	}
	public void setSegment(SegmentDto segment) {
		this.segment = segment;
	}
	
	
}
