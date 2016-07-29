package app.dto;

public class SegmentDto {

	private int segmentId;
	private String segmentName;
	
	
	public SegmentDto(int segmentId, String segmentName) {
		super();
		this.segmentId = segmentId;
		this.segmentName = segmentName;
	}
		
	public SegmentDto() {
		super();
	}

	public int getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}
	public String getSegmentName() {
		return segmentName;
	}
	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	
	
}
