package app.translator;

import java.util.List;

import org.springframework.stereotype.Component;

import app.dto.FeatureManufacturerDto;
import app.dto.ManufacturerDto;
import app.dto.ModelDto;
import app.dto.SegmentDto;
import app.dto.SubFeatureDto;
import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
import app.entityClasses.ModelManufacturerMapper;
import app.entityClasses.Segment;

@Component
public class ResponseTranslator {

	public SegmentDto getSegment(Segment segment){
		SegmentDto segmentDto = null;
		if(segment != null){
			segmentDto = new SegmentDto(segment.getId(), segment.getSegname());
		}
		return segmentDto;
	}
	
	public ManufacturerDto getManufacturer(Manufacturer manufacturer){
		ManufacturerDto manufacturerDto = null;
		if(manufacturer!=null && manufacturer.getSegment() != null){
			manufacturerDto = new ManufacturerDto(manufacturer.getId(), manufacturer.getManufacturerName(), getSegment(manufacturer.getSegment()));
		}
		return manufacturerDto;
	}
	
	public ModelDto getModel(Model model){
		ModelDto modelDto = null;
		if(model != null){
			modelDto = new ModelDto(model.getId(), model.getModelName(), getManufacturer(model.getManufacturer()));
		}
		return modelDto;
	}
	 
	public void translateMapper(List<ModelManufacturerMapper> mappers){
		for(ModelManufacturerMapper mapper : mappers){
			FeatureManufacturerDto manu = new FeatureManufacturerDto(mapper.getManufacturerMapper().getId(), 
					mapper.getManufacturerMapper().getManufacturerName(), 
					mapper.getManufacturerMapper().getCost());
			SubFeatureDto sub = new SubFeatureDto(mapper.getManufacturerMapper().getSubfeature().getId(), 
					mapper.getManufacturerMapper().getSubfeature().getSubFeatureName());
			
		}
	}
	
}
