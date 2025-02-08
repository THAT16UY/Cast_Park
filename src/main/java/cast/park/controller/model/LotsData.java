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
public class LotsData {
	private Long lotsId;
	private String lotName;
	
	private Set<AttractionsResponce> attractions = new HashSet<>();
	private Set<LPerformersResponce> performers = new HashSet<>();
	
	public LotsData(Lots lot) {
		lotsId = lot.getLotId();
		lotName = lot.getLotName();
		
		for(Attractions attraction : lot.getAttractions()) {
			attractions.add(new AttractionsResponce(attraction));
		}
		
		for(Performers performer : lot.getPerformers()) {
			performers.add(new LPerformersResponce(performer));
		}
		
	}
	@Data
	@NoArgsConstructor
	static class AttractionsResponce{
		private Long attractionId;
		
		private String attractionName;
		
		private String attractionType;
		
		private Long waitTime;
		
		private double cost;
		
		public AttractionsResponce(Attractions attraction) {
			attractionId = attraction.getAttractionId();
			attractionName = attraction.getAttractionName();
			attractionType = attraction.getAttractionType();
			waitTime = attraction.getWaitTime();
			cost = attraction.getCost();		
		}
	}
	
	@Data
	@NoArgsConstructor
	static class LPerformersResponce{
		private Long performerId; 
		  
		private String performerName;
		  
		private String role;
		
		public LPerformersResponce(Performers performer) {
			performerId = performer.getPerformerId();
			performerName = performer.getPerformerName();
			role = performer.getRole();
		}
	}
}
