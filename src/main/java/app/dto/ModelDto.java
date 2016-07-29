package app.dto;

public class ModelDto {
	
	private int modelId;
	private String modelName;
	private ManufacturerDto manufacturer;
	
	public ModelDto(int modelId, String modelName, ManufacturerDto manufacturer) {
		super();
		this.modelId = modelId;
		this.modelName = modelName;
		this.manufacturer = manufacturer;
	}
	
	public ModelDto() {
		super();
	}

	public int getModelId() {
		return modelId;
	}
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public ManufacturerDto getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(ManufacturerDto manufacturer) {
		this.manufacturer = manufacturer;
	}
}
