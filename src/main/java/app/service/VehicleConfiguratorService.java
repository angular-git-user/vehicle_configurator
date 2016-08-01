package app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.dto.ManufacturerDto;
import app.dto.ModelDto;
import app.dto.SegmentDto;
import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
import app.entityClasses.Segment;
import app.translator.ResponseTranslator;

@Component
public class VehicleConfiguratorService {
	
	@Autowired
	private ResponseTranslator responseTranslator;
	
	@Autowired
	PersistenceService hibernatePersistence;
	
	public List<SegmentDto> getSegments(){
		List<SegmentDto> segmentsDto = null;
		List<Segment> segments =  hibernatePersistence.getAllSegment();
		
		if(segments!= null && !segments.isEmpty()){
			segmentsDto = new ArrayList<SegmentDto>();
			
			for(Segment segment : segments){
				segmentsDto.add(responseTranslator.getSegment(segment));
			}
		}
		return segmentsDto;
	}
	
	public List<ManufacturerDto> getManufacturers(int segmentId){
		List<ManufacturerDto> manufsDto = null;
		List<Manufacturer> manufs =  hibernatePersistence.getAllManufacturers(segmentId);
		
		if(manufs!= null && !manufs.isEmpty()){
			manufsDto = new ArrayList<ManufacturerDto>();
			
			for(Manufacturer manuf : manufs){
				manufsDto.add(responseTranslator.getManufacturer(manuf));
			}
		}
		return manufsDto;
	}
	
	public List<ModelDto> getModels(int manufacturerId){
		List<ModelDto> modelsDto = null;
		List<Model> models = hibernatePersistence.getAllModels(manufacturerId);
		
		if(models !=null && !models.isEmpty()){
			modelsDto = new ArrayList<ModelDto>();
			
			for(Model model : models){
				modelsDto.add(responseTranslator.getModel(model));
			}
		}
		return modelsDto;
	}
}
