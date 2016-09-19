package app.service;

import java.util.List;

import app.entityClasses.Manufacturer;
import app.entityClasses.Model;
import app.entityClasses.ModelManufacturerMapper;
import app.entityClasses.Segment;
import app.entityClasses.User;

public interface PersistenceService {

	Integer createUser(User user);
	
	List<Segment> getAllSegment();
	
	List<Manufacturer> getAllManufacturers(int segmentId);
	
	List<Model> getAllModels(int manufacturerId);

	List<ModelManufacturerMapper> getAllFeatures(int modelId);

	List<Model> getSearchSuggestionsForModels(String searchString);

	User getUserWithLoginId(String loginId);

	void updateUser(User u);
}
