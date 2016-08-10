package app.service;

import java.util.List;

import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
import app.entityClasses.ModelManufacturerMapper;
import app.entityClasses.Segment;
import app.entityClasses.User;

public interface PersistenceService {

	public Integer createUser(User user);
	
	public boolean login(String loginId, String password);
	
	public List<Segment> getAllSegment();
	
	public List<Manufacturer> getAllManufacturers(int segmentId);
	
	public List<Model> getAllModels(int manufacturerId);

	public List<ModelManufacturerMapper> getAllFeatures(int modelId);
}
