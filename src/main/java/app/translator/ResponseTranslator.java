package app.translator;

import org.springframework.stereotype.Component;

import app.dto.ManufacturerDto;
import app.dto.ModelDto;
import app.dto.SegmentDto;
import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
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
	 
	
}
