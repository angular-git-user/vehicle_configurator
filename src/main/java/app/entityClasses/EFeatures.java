package app.entityClasses;

public enum EFeatures {
	
	/* Feature types*/
	STANDARD_FEATURE("Standard Feature"),
	INTERIOR_FEATURE("Interior"),
	EXTERIOR_FEATURE("Exterior"),
	
	/*Standard Feature*/
	SEAT_BELT("Seat Belt"),
	GPS("GPS"),
	AIR_BAGS("Air Bags"), 
	TIRE_PRESSURE_MONITORING_SYSTEM("Tire Pressure Monitoring System"),
	
	/*Exterior*/
	WHEELS("Wheels"), 
	HEAD_LIGHTS("Rear/Side Lights"), 
	CONVINIENCE_FEATURES("Convenience Features"), 
	STYLIZED_ACCENTS("Stylized Accents"),
	
	/*Interior*/
	MUSIC("Sound System"),
	SEAT_TYPE("Seat types"),
	FRESHNER("Freshner"),	
	;
	
	private String feature;
	
	EFeatures(String feat){
		this.feature = feat;
	}

	public String getFeature() {
		return feature;
	}	
}
