package cast.park.controller.model;

import java.util.HashSet;
import java.util.Set;

import cast.park.entity.Attractions;
import cast.park.entity.Lots;
import cast.park.entity.Performers;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttractionsData {
	
	private Long attractionId;
	
	private String attractionName;
	
	private String attractionType;
	
	private Long waitTime;
	
	private double cost;
	
	private Set<APerformersResponce> performers = new HashSet<>();
	
	public AttractionsData(Attractions attraction) {
		attractionId = attraction.getAttractionId();
		attractionName = attraction.getAttractionName();
		attractionType = attraction.getAttractionType();
		waitTime = attraction.getWaitTime();
		cost = attraction.getCost();
		
		for(Performers performer : attraction.getPerformers()) {
			performers.add(new APerformersResponce(performer));
		}
	}
	
	@Data
	@NoArgsConstructor
	static class APerformersResponce{
		private Long performerId; 
		  
		private String performerName;
		  
		private String role;
		
		public APerformersResponce(Performers performer) {
			performerId = performer.getPerformerId();
			performerName = performer.getPerformerName();
			role = performer.getRole();
		}
	}

	public Object getPerformers() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
