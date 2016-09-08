package app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dto.*;
import app.entityClasses.User;
import app.service.PersistenceService;
import app.service.VehicleConfiguratorService;


@RestController
@RequestMapping("/vehicle")
public class VehicleConfiguratorController {

	@Autowired
	PersistenceService hibernatePersistence;
	
	@Autowired
	VehicleConfiguratorService service;
	
	//TODO
	@RequestMapping(method = RequestMethod.POST,value = "/register")
	public ResponseEntity<User> createNewUser(@RequestBody User user){
		if(user != null){
			Integer id = hibernatePersistence.createUser(user);
			if(id != null){
				return new ResponseEntity<User>(HttpStatus.OK);
			}
		}
		return new ResponseEntity<User>(HttpStatus.CONFLICT);
	}
	
	//TODO
	@RequestMapping(method = RequestMethod.POST, value="/login")
	public ResponseEntity<User> login(@RequestBody User user){
		if(user != null && hibernatePersistence.login(user.getUserId(), user.getPassword())){
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/segments")
	public ResponseEntity<List<SegmentDto>> getSegments(){
		List<SegmentDto> segments = service.getSegments();
		if(segments!=null && !segments.isEmpty()){
			return new ResponseEntity<List<SegmentDto>>(segments, HttpStatus.OK);
		}
		return new ResponseEntity<List<SegmentDto>>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{segmentId}/manufacturers")
	public ResponseEntity<List<ManufacturerDto>> getManufactureres(@RequestParam int segmentId) {
		List<ManufacturerDto> manufList = service.getManufacturers(segmentId);
		if(manufList!=null && !manufList.isEmpty()){
			return new ResponseEntity<List<ManufacturerDto>>(manufList, HttpStatus.OK);
		}
		return new ResponseEntity<List<ManufacturerDto>>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{manufacturerId}/models")
	public ResponseEntity<List<ModelDto>> getModels(@RequestParam int manufacturerId) {
		List<ModelDto> list = service.getModels(manufacturerId);
		if(list != null && list.size() > 0){
			return new ResponseEntity<List<ModelDto>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<ModelDto>>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/features/{modelId}")
	public Map<String, FeatureTypesDto> getFeatures(@RequestParam int modelId){
		return service.getFeatures(modelId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="search_suggestions/model/{searchString}")
	public List<ModelDto> getSearchSuggestionsForModels(@RequestParam String searchString){
		return service.getSearchSuggestionsForModels(searchString);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="test")
    public String setupForm(@RequestParam("petId") String petId, @RequestParam("petId2") String petId1) {
        return petId + " " + petId1;
    }
}
