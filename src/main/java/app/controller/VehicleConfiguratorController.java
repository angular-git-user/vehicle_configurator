package app.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.dto.*;
import app.service.VehicleConfiguratorService;


@RestController
@RequestMapping("/vehicle")
public class VehicleConfiguratorController {
	
	@Autowired
	VehicleConfiguratorService service;
	
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
	
	@RequestMapping(method = RequestMethod.GET, value="/sms")
	public String sms(){
		 try {
             String recipient = "441234567";
             String message = "Hello World";
             String username = "admin";
             String password = "password";
             String originator = "441234567";

             String requestUrl  = "http://127.0.0.1:9501/api?action=sendmessage&" +
				 "username=" + URLEncoder.encode(username, "UTF-8") +
				 "&password=" + URLEncoder.encode(password, "UTF-8") +
				 "&recipient=" + URLEncoder.encode(recipient, "UTF-8") +
				 "&messagetype=SMS:TEXT" +
				 "&messagedata=" + URLEncoder.encode(message, "UTF-8") +
				 "&originator=" + URLEncoder.encode(originator, "UTF-8") +
				 "&serviceprovider=HTTPServer3" +
				 "&responseformat=html";



             URL url = new URL(requestUrl);
             HttpURLConnection uc = (HttpURLConnection)url.openConnection();

             System.out.println(uc.getResponseMessage());

             uc.disconnect();

     } catch(Exception ex) {
             System.out.println(ex.getMessage());

     }
		return "OK";
	}
}
