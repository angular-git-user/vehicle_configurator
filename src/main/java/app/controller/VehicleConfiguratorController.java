package app.controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.connection.HibernateConnectionUtil;
import app.dto.*;
import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
import app.entityClasses.Segment;
import app.entityClasses.User;
import app.service.PersistenceService;


@RestController
@RequestMapping("/vehicle")
public class VehicleConfiguratorController {

	@Autowired
	PersistenceService hibernatePersistence;
	
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
	
	@RequestMapping(method = RequestMethod.POST, value="/login")
	public ResponseEntity<User> login(@RequestBody User user){
		if(user != null && hibernatePersistence.login(user.getUserId(), user.getPassword())){
			return new ResponseEntity<User>(HttpStatus.OK);
		}
		return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/segments")
	public ResponseEntity<List<Segment>> getSegments(){
		List<Segment> segmentList = hibernatePersistence.getAllSegment();
		if(segmentList.size() > 0){
			return new ResponseEntity<List<Segment>>(segmentList, HttpStatus.OK);
		}
		return new ResponseEntity<List<Segment>>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{segmentId}/manufacturers")
	public ResponseEntity<List<Manufacturer>> getManufactureres(@RequestParam int segmentId) {
		List<Manufacturer> list = null;
		if(segmentId > 0){
			list = hibernatePersistence.getAllManufacturers(segmentId);
		}
		if(list != null && list.size() > 0){
			return new ResponseEntity<List<Manufacturer>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<Manufacturer>>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/{manufacturerId}/models")
	public ResponseEntity<List<Model>> getModels(@RequestParam int manufacturerId) {
		List<Model> list = null;
		if(manufacturerId > 0){
			list = hibernatePersistence.getAllModels(manufacturerId);
		}
		if(list != null && list.size() > 0){
			return new ResponseEntity<List<Model>>(list, HttpStatus.OK);
		}
		return new ResponseEntity<List<Model>>(HttpStatus.NO_CONTENT);
	}
	

	@RequestMapping(method = RequestMethod.GET, value="/test/models")
	public ResponseEntity<ModelDto> modelTest(){
		SessionFactory sf = HibernateConnectionUtil.getConnectionFactory();

		Session session = sf.openSession();
		session.beginTransaction();
		
		Model m = (Model)session.get(Model.class, 1);
		Manufacturer manu = m.getManufacturer();
		Segment seg = manu.getSegment();
		SegmentDto seg1 = new SegmentDto(seg.getId(), seg.getSegname());
		ManufacturerDto manu1 = new ManufacturerDto(manu.getId(), manu.getManufacturerName(), seg1);
		ModelDto m1 = new ModelDto(m.getId(), m.getModelName(), manu1);
		
		
		return new ResponseEntity<ModelDto>(m1, HttpStatus.OK);
	}
	
}
