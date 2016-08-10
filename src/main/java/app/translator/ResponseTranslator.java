package app.translator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import app.dto.FeatureManufacturerDto;
import app.dto.FeatureTypesDto;
import app.dto.ManufacturerDto;
import app.dto.ModelDto;
import app.dto.SegmentDto;
import app.dto.SubFeatureDto;
import app.entityClasses.FeatureManufacturers;
import app.entityClasses.FeatureTypes;
import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
import app.entityClasses.ModelManufacturerMapper;
import app.entityClasses.Segment;
import app.entityClasses.SubFeatures;

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

	public Map<String, FeatureTypesDto> getMappedFeatures(List<ModelManufacturerMapper> mapper) {
		Map<String, FeatureTypesDto> featureMap = new HashMap<String, FeatureTypesDto>();
		Map<String, SubFeatureDto> subFeatureMap = null;
		FeatureTypesDto featureDto = null;;
		SubFeatureDto subFeatureDto = null;
		for(ModelManufacturerMapper modelManuMap : mapper){
			FeatureTypes ft = modelManuMap.getManufacturerMapper().getSubfeature().getFeature();
			if(featureMap.get(ft.getFeature().getFeature()) == null){
				featureDto = getFeatureDto(modelManuMap.getManufacturerMapper().getSubfeature().getFeature());	
				featureMap.put(featureDto.getFeatureType(), featureDto);
			}
			featureDto = featureMap.get(ft.getFeature().getFeature());
			if(featureDto != null){
				subFeatureMap = featureDto.getSubFeatures();
				SubFeatures sft = modelManuMap.getManufacturerMapper().getSubfeature();
				if(sft != null && subFeatureMap.get(sft.getSubFeatureName()) == null){
					subFeatureDto = getSubFeatureDto(sft);
					subFeatureMap.put(subFeatureDto.getSubFeatureName(), subFeatureDto);
				}
				subFeatureDto = subFeatureMap.get(sft.getSubFeatureName());
				Set<FeatureManufacturerDto> setOfManu = subFeatureDto.getFeatureManufacturers();
				setOfManu.add(getManufacturerDto(modelManuMap.getManufacturerMapper()));
			}else{
				return null;
			}
		}
		return featureMap;
	}

	public FeatureTypesDto getFeatureDto(FeatureTypes feature){
		return new FeatureTypesDto(feature.getId(), feature.getFeature().getFeature());
	}

	public SubFeatureDto getSubFeatureDto(SubFeatures subFeature){
		return new SubFeatureDto(subFeature.getId(), subFeature.getSubFeatureName());
	}

	public FeatureManufacturerDto getManufacturerDto(FeatureManufacturers manu){
		return new FeatureManufacturerDto(manu.getId(), manu.getManufacturerName(), manu.getCost());
	}
	
}
